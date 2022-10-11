package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_db.entities.LinkedCard;
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

@AllArgsConstructor
@Slf4j
public class ClosedDepositTransferServiceImpl implements ClosedDepositTransferService {

    private Map<String, UUID> currencyMap;

    private Map<String, UUID> paymentTypeMap;

    private final MoneyTransfer moneyTransfer;

    @Override
    public void transferToAccount(List<Deposit> deposits) {

        deposits.stream()
                .map(this::createTransfer)
                .forEach(moneyTransfer::createTransfer);

        resetAmountAfterTransferToCard(deposits);
    }

    private TransferRequestDto createTransfer(Deposit deposit) {
        setUpCurrencyMap();
        setUpPaymentTypeMap();

        return TransferRequestDto.builder()
                .amount(deposit.getAmount())
                .currencyId(currencyMap.get(deposit.getCurrency().getName()))
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
        currencyMap = moneyTransfer.getAllPaymentTypes().stream()
                .collect(Collectors.toMap(PaymentTypeDto::getName, PaymentTypeDto::getId));
    }

    private String getCardNumber(Deposit deposit) {
        LinkedCard card = deposit.getLinkedCards().stream().findFirst().get();

        return card.getFirstTwelveNumbers() + card.getLastFourNumbers();
    }

    private void resetAmountAfterTransferToCard(List<Deposit> list) {
        list.stream().peek(x -> x.setAmount(0L));
    }
}
