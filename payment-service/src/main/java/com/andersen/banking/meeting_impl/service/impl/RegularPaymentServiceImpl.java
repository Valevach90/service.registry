package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.RegularPayment;
import com.andersen.banking.meeting_db.repository.RegularPaymentRepository;
import com.andersen.banking.meeting_impl.aop.LogAnnotation;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.feign.TransferClient;
import com.andersen.banking.meeting_impl.feign.dto.TransferRequestDto;
import com.andersen.banking.meeting_impl.service.CardService;
import com.andersen.banking.meeting_impl.service.RegularPaymentService;
import com.andersen.banking.meeting_impl.util.RegularPaymentUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.andersen.banking.meeting_impl.util.RegularPaymentUtil.setUpNextDate;
import static com.andersen.banking.meeting_impl.util.TransferMapsContainer.getCurrencyMap;
import static com.andersen.banking.meeting_impl.util.TransferMapsContainer.getPaymentTypeMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegularPaymentServiceImpl implements RegularPaymentService {

    private final RegularPaymentRepository regularPaymentRepository;
    private final CardService cardService;
    private final TransferClient transferClient;

    @Override
    @LogAnnotation(before = true, after = true)
    @Transactional
    public RegularPayment create(RegularPayment regularPayment) {
        validateStartDate(regularPayment);

        regularPayment.setSourceCard(cardService.findById(regularPayment.getSourceCard().getId()));
        regularPayment.setRecipientCard(cardService.findById(regularPayment.getRecipientCard().getId()));

        regularPayment.setNextDate(regularPayment.getStartDate());
        regularPayment.setIsActive(true);

        return regularPaymentRepository.save(regularPayment);
    }

    private void validateStartDate(RegularPayment regularPayment) {
        if (regularPayment.getStartDate().isBefore(LocalDate.now()))
            throw new DateTimeException("Incorrect date input");
    }

    @Override
    @LogAnnotation(before = true, after = true)
    @Transactional
    public void deleteById(UUID id) {
        regularPaymentRepository.deleteById(id);
    }


    @Override
    @LogAnnotation(before = true, after = true)
    @Transactional
    public RegularPayment update(RegularPayment regularPaymentToUpdate) {
         regularPaymentRepository.findById(regularPaymentToUpdate.getId())
                .orElseThrow(() -> new NotFoundException(RegularPayment.class, regularPaymentToUpdate.getId()));

        return regularPaymentRepository.save(regularPaymentToUpdate);
    }

    @Override
    @LogAnnotation(before = true, after = true)
    public RegularPayment findById(UUID id) {
        return regularPaymentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(RegularPayment.class, id));
    }

    @Override
    @LogAnnotation(before = true, after = true)
    public Page<RegularPayment> findAll(Pageable pageable) {
        return regularPaymentRepository.findAll(pageable);
    }

    @Transactional
    public boolean executeSomeAmountOfRegularPayments() {
        List<RegularPayment> regularPaymentsToExecute = regularPaymentRepository.findRegularPaymentsToExecute();

        if (!regularPaymentsToExecute.isEmpty()) {
            regularPaymentsToExecute.forEach(this::executeRegularPayment);

            return true;
        } else {
            return false;
        }
    }

    private void executeRegularPayment(RegularPayment regularPayment) {
        log.info("Creating transfer by regular payment id: {}", regularPayment.getId());

        transferClient.createTransfer(getTransferRequestDto(regularPayment));
        setUpNextDate(regularPayment);
        regularPaymentRepository.save(regularPayment);
    }

    private TransferRequestDto getTransferRequestDto(RegularPayment regularPayment) {
        return TransferRequestDto.builder()
                .regularId(regularPayment.getId())
                .userId(regularPayment.getSourceCard().getAccount().getOwnerId())
                .sourceNumber(regularPayment.getSourceCard().getFirstTwelveNumbers() + regularPayment.getSourceCard().getLastFourNumbers())
                .sourcePaymentTypeId(getPaymentTypeMap().get("CARD"))
                .destinationNumber(regularPayment.getRecipientCard().getFirstTwelveNumbers() + regularPayment.getRecipientCard().getLastFourNumbers())
                .destinationPaymentTypeId(getPaymentTypeMap().get("CARD"))
                .amount(regularPayment.getAmount())
                .currencyId(getCurrencyMap().get(regularPayment.getSourceCard().getAccount().getCurrency()))
                .comment("Regular payment")
                .build();
    }


}
