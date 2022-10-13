package com.andersen.banking.meeting_impl.util;

import com.andersen.banking.meeting_impl.feign.MoneyTransfer;
import com.andersen.banking.meeting_impl.feign.dto.CurrencyDto;
import com.andersen.banking.meeting_impl.feign.dto.PaymentTypeDto;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferMapContainer {

    private Map<String, UUID> currencyMap = new HashMap<>();

    private Map<String, UUID> paymentTypeMap = new HashMap<>();

    private final MoneyTransfer moneyTransfer;

    public Map<String, UUID> getCurrencyMap() {
        checkCurrencyMap();
        return currencyMap;
    }

    public Map<String, UUID> getPaymentTypeMap() {
        checkPaymentTypeMap();
        return paymentTypeMap;
    }

    public void checkCurrencyMap() {
        if(currencyMap.isEmpty()) {
            currencyMap = moneyTransfer.getAllCurrencies().stream()
                    .collect(Collectors.toMap(CurrencyDto::getName, CurrencyDto::getId));
        }
    }

    public void checkPaymentTypeMap() {
        if(paymentTypeMap.isEmpty()) {
            paymentTypeMap = moneyTransfer.getAllPaymentTypes().stream()
                    .collect(Collectors.toMap(PaymentTypeDto::getName, PaymentTypeDto::getId));
        }
    }
}
