package com.andersen.banking.meeting_impl.util;

import com.andersen.banking.meeting_impl.exception.PaymentServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Slf4j
public class AccountNumberGenerator {

    private static final Map<String, String> currencyNumberMap = Map.of(
            "RUB", "0",
            "EUR", "1",
            "USD", "2"
    );

    public static Map<String, String> getCurrencyNumberMap(){
        return currencyNumberMap;
    }

    public static String generateAccountNumber(String bankName, String currency, UUID ownerId){
        Random random = new Random(System.currentTimeMillis());
        StringBuilder builder = new StringBuilder();

        String bankHash = String.valueOf(Math.abs(bankName.hashCode()));
        String uuidHash = String.valueOf(Math.abs(ownerId.toString().hashCode()));
        String currencyNumber = currencyNumberMap.get(currency);

        //append currNumber
        if (currencyNumber != null)
            builder.append(currencyNumber);
        else {
            log.error("Currency [{}] not found. Couldn't generate account number", currency);
            throw new PaymentServiceException("Wrong currency");
        }

        //append to make first 10 numbers
        if (bankHash.length() > 10 - currencyNumber.length()){
            bankHash = bankHash.substring(0, 10 - currencyNumber.length());
            builder.append(bankHash);
        }
        else {
            builder.append(bankHash);
            for (int i = 0; i < 10 - bankHash.length(); i++) {
                builder.append(random.nextInt(10));
            }
        }

        builder.append(uuidHash);

        //to make 20 numbers
        int needToBeAddedTimes = 20 - builder.length();
        for (int i = 0; i < needToBeAddedTimes ; i++) {
            builder.append(random.nextInt(10));
        }

        return builder.toString();
    }
}