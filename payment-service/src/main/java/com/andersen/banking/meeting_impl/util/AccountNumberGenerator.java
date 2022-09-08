package com.andersen.banking.meeting_impl.util;

import com.andersen.banking.meeting_impl.exception.PaymentServiceException;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountNumberGenerator {

    private static final Map<String, String> currencyNumberMap = Map.of(
            "RUB", "01",
            "EUR", "02",
            "USD", "03"
    );

    public static Map<String, String> getCurrencyNumberMap(){
        return currencyNumberMap;
    }

    public static String generateAccountNumber(String bankName, String currency, UUID ownerId){
        Random random = new Random(System.currentTimeMillis());
        String accountNumber = "";
        StringBuilder builder = new StringBuilder(accountNumber);

        String bankHash = String.valueOf(Math.abs(bankName.hashCode()));
        String uuidHash = String.valueOf(Math.abs(ownerId.toString().hashCode()));


        //make firs 8 numbers
        if (bankHash.length() > 8){
            bankHash = bankHash.substring(0, 8);
            builder.append(bankHash);
        }
        else {
            builder.append(bankHash);
            for (int i = 0; i < 8 - bankHash.length(); i++) {
                builder.append(random.nextInt(10));
            }
        }



        String currencyNumber = currencyNumberMap.get(currency);
        if (currencyNumber != null)
            builder.append(currencyNumber);
        else {
            log.error("Currency [{}] not found. Couldn't generate account number", currency);
            throw new PaymentServiceException("Wrong currency");
        }

        builder.append(uuidHash);

        int needToBeAddedTimes = 20 - builder.length();
        for (int i = 0; i < needToBeAddedTimes ; i++) {
            builder.append(random.nextInt(10));
        }

        return builder.toString();
    }
}