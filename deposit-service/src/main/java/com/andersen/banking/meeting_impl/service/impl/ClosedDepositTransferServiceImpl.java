package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_db.entities.LinkedCard;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.feign.MoneyTransfer;
import com.andersen.banking.meeting_impl.feign.dto.CurrencyDto;
import com.andersen.banking.meeting_impl.feign.dto.PaymentTypeDto;
import com.andersen.banking.meeting_impl.feign.dto.TransferRequestDto;
import com.andersen.banking.meeting_impl.service.ClosedDepositTransferService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class ClosedDepositTransferServiceImpl implements ClosedDepositTransferService {

    private Map<String, UUID> currencyMap;

    private Map<String, UUID> paymentTypeMap;

    private final MoneyTransfer moneyTransfer;

    @Override
    public void transferToAccount(List<Deposit> deposits) {

        List<TransferRequestDto> list = deposits.stream()
                .map(this::createTransfer).toList();

        list.forEach(moneyTransfer::createTransfer);

        resetAmountAfterTransferToCard(deposits);
    }

    private TransferRequestDto createTransfer(Deposit deposit) {
        setUpCurrencyMap();
        setUpPaymentTypeMap();

        return TransferRequestDto.builder()
                .amount(deposit.getAmount())
                .currencyId(currencyMap.get("EURO"))
                .userId(deposit.getUserId())
                .sourcePaymentTypeId(paymentTypeMap.get("DEPOSIT"))
                .sourceNumber(deposit.getDepositNumber())
                .destinationPaymentTypeId(paymentTypeMap.get("CARD"))
                .destinationNumber(getCardNumber(deposit))
                .build();
    }

    private void setUpCurrencyMap() {
        currencyMap = moneyTransfer.getAllCurrencies().stream()
                .collect(Collectors.toMap(CurrencyDto::getName, CurrencyDto::getId));
    }

    private void setUpPaymentTypeMap() {
        paymentTypeMap = moneyTransfer.getAllPaymentTypes().stream()
                .collect(Collectors.toMap(PaymentTypeDto::getName, PaymentTypeDto::getId));
    }

    private String getCardNumber(Deposit deposit) {
        LinkedCard card = deposit.getLinkedCards().stream().findFirst()
                .orElseThrow(() -> new NotFoundException(Deposit.class, deposit.getDepositNumber(), deposit.getUserId()));

        return card.getFirstTwelveNumbers() + card.getLastFourNumbers();
    }

    private void resetAmountAfterTransferToCard(List<Deposit> list) {
        list.forEach(x -> x.setAmount(0L));
    }
}
