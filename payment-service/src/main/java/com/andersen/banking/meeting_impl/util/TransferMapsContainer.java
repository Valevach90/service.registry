package com.andersen.banking.meeting_impl.util;

import com.andersen.banking.meeting_impl.feign.TransferClient;
import com.andersen.banking.meeting_impl.feign.dto.CurrencyDto;
import com.andersen.banking.meeting_impl.feign.dto.PaymentTypeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class TransferMapsContainer {

    private final TransferClient transferClient;

    private static Map<String, UUID> currencyMap;

    private static Map<String, UUID> paymentTypeMap;

    public static Map<String, UUID> getCurrencyMap() {
        return currencyMap;
    }

    public static Map<String, UUID> getPaymentTypeMap() {
        return paymentTypeMap;
    }

    public void setUpMaps() {
        if (checkIfMapsWereInit()) {
            log.info("Maps were already initialized");
        } else {
            setUpCurrencyMap();
            setUpPaymentTypeMap();
        }
    }

    public boolean checkIfMapsWereInit() {
        if (currencyMap != null && paymentTypeMap != null)
            return true;
        else
            return false;
    }

    private void setUpCurrencyMap() {
        log.info("Setting up currency map from transfer service");

        currencyMap = transferClient.getAllCurrencies().stream()
                .collect(Collectors.toMap(CurrencyDto::getName, CurrencyDto::getId));

        log.info("Currency map was initialized: {}", currencyMap);
    }
    private void setUpPaymentTypeMap() {
        log.info("Setting up currency map from transfer service");

        paymentTypeMap = transferClient.getAllPaymentTypes().stream()
                .collect(Collectors.toMap(PaymentTypeDto::getName, PaymentTypeDto::getId));

        log.info("Currency map was initialized: {}", paymentTypeMap);
    }
}
