package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.CurrencyDto;
import com.andersen.banking.meeting_api.dto.FrequencyDto;
import com.andersen.banking.meeting_db.entities.RegularPayment;
import com.andersen.banking.meeting_db.repository.RegularPaymentRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.feign.TransferClient;
import com.andersen.banking.meeting_impl.feign.dto.TransferRequestDto;
import com.andersen.banking.meeting_impl.feign.dto.TransferResponseDto;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.service.CardProductService;
import com.andersen.banking.meeting_impl.service.CardService;
import com.andersen.banking.meeting_impl.service.RegularPaymentService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import com.andersen.banking.meeting_impl.service.impl.internal.TransferInternalMoneyServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.TransactionAbortedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Status;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegularPaymentServiceImpl implements RegularPaymentService {

    private final RegularPaymentRepository regularPaymentRepository;
    private final CardService cardService;
    private final TransferClient transferClient;
    private final CardProductService cardProductService;

    private final TransferInternalMoneyServiceImpl transferInternalMoneyService;

    private Map<String, UUID> currencyMap;

    @Transactional
    @Override
    public RegularPayment create(RegularPayment regularPayment) {
        log.info("Creating regular payment: {}", regularPayment);

        regularPayment.setSourceCard(cardService.findById(regularPayment.getSourceCard().getId()));
        regularPayment.setRecipientCard(cardService.findById(regularPayment.getRecipientCard().getId()));
        regularPayment.setNextDate(regularPayment.getStartDate());

        RegularPayment savedRegularPayment = regularPaymentRepository.save(regularPayment);

        log.info("Created regular payment: {}", savedRegularPayment);
        return savedRegularPayment;
    }

    @Transactional
    public RegularPayment update(RegularPayment regularPaymentToUpdate) {
        log.info("Updating regular payment: {}", regularPaymentToUpdate);

        regularPaymentRepository.findById(regularPaymentToUpdate.getId())
                        .orElseThrow(() -> new NotFoundException(RegularPayment.class, regularPaymentToUpdate.getId()));

        RegularPayment updatedRegularPayment = regularPaymentRepository.save(regularPaymentToUpdate);

        log.info("Updated regular payment: {}", updatedRegularPayment);
        return updatedRegularPayment;
    }

    public void setUpCurrencyMap() {
        currencyMap = transferClient.getAllCurrencies().stream()
                .collect(Collectors.toMap(CurrencyDto::getName, CurrencyDto::getId));
    }

    @Transactional(propagation = Propagation.NESTED)
    public boolean executeSomeAmountOfRegularPayments() {
        setUpCurrencyMap();
        List<RegularPayment> regularPaymentsToExecute = regularPaymentRepository.findRegularPaymentsToExecute();

        if (!regularPaymentsToExecute.isEmpty()) {
            regularPaymentsToExecute.forEach(this::executeRegularPayment);

            return true;
        } else {
            return false;
        }

    }

    @Transactional
    public void executeRegularPayment(RegularPayment regularPayment) {
        TransferResponseDto transferResponseDto = transferClient.createTransfer(getTransferRequestDto(regularPayment));

        if (transferInternalMoneyService.executeTransfer(getTransferRequestForExecution(transferResponseDto, regularPayment))
                .getStatus() == Status.STATUS_COMMITTED) {
            setUpNextDate(regularPayment);
            regularPaymentRepository.save(regularPayment);
        } else {
            throw new TransactionAbortedException("Transaction with rolled back");
        }
    }

    private TransferRequestDto getTransferRequestDto(RegularPayment regularPayment) {
        return TransferRequestDto.builder()
                .userId(regularPayment.getSourceCard().getAccount().getOwnerId())
                .sourceNumber(regularPayment.getSourceCard().getFirstTwelveNumbers() + regularPayment.getSourceCard().getLastFourNumbers())
                .sourcePaymentTypeId(cardProductService.findById(regularPayment.getSourceCard().getCardProduct().getId())
                        .getTypeCard().getId())
                .destinationNumber(regularPayment.getRecipientCard().getFirstTwelveNumbers() + regularPayment.getRecipientCard().getLastFourNumbers())
                .amount(regularPayment.getAmount())
                .currencyId(currencyMap.get(regularPayment.getSourceCard().getAccount().getCurrency()))
                .comment("Regular payment")
                .build();
    }

    private RequestTransferMessage getTransferRequestForExecution(TransferResponseDto response, RegularPayment regularPayment) {
        return RequestTransferMessage.builder()
                .transferId(response.getId())
                .userId(regularPayment.getSourceCard().getAccount().getOwnerId())
                .sourceNumber(response.getSourceNumber())
                .sourceType(response.getSourcePaymentTypeName())
                .destinationNumber(response.getDestinationNumber())
                .destinationType(response.getDestinationPaymentTypeName())
                .amount(response.getAmount())
                .status(response.getStatus())
                .currencyName(response.getCurrencyName())
                .build();
    }


    private void setUpNextDate(RegularPayment regularPayment) {
        FrequencyDto frequencyDto = parseFrequency(regularPayment.getFrequency());

        regularPayment.setNextDate(regularPayment.getNextDate()
                .plusYears(frequencyDto.getYears())
                .plusMonths(frequencyDto.getMounts())
                .plusMonths(frequencyDto.getWeeks())
                .plusMonths(frequencyDto.getDays())
        );
    }

    private FrequencyDto parseFrequency(String frequency) {
        String[] frequencies = frequency.split("_");
        Long years = Long.parseLong(frequencies[0].substring(0, frequencies[0].length() -1));
        Long mounts = Long.parseLong(frequencies[1].substring(0, frequencies[1].length() -1));
        Long weeks = Long.parseLong(frequencies[2].substring(0, frequencies[2].length() -1));
        Long days = Long.parseLong(frequencies[3].substring(0, frequencies[3].length() -1));

        return new FrequencyDto(years, mounts, weeks, days);
    }


}
