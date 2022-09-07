package com.andersen.banking.meeting_impl.util;

import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_impl.exception.PaymentServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Slf4j
public class CardGenerator {

    private static final List<String> startsWith_VISA = List.of("4");
    private static final List<String> startsWith_MASTERCARD = List.of("51", "52", "53", "54", "55");
    private static final Map<String, List<String>> startNumbersMap = Map.of(
            "VISA", startsWith_VISA,
            "MASTERCARD", startsWith_MASTERCARD
    );

    private static final Map<String, String> typeNumberMap = Map.of(
            "SILVER", "01",
            "GOLD", "02",
            "PLATINUM", "03"
    );

    public static void generateExpirationTime(Card card){
        //need to add some logic;
        card.setExpireDate(card.getValidFromDate().plusYears(3));
    }

    public static String generateCardNumber(String cardPaymentSystem, String typeName, String currency, UUID uuid){
        String uuidHash = String.valueOf(Math.abs(uuid.toString().hashCode()));
        Random random = new Random(System.currentTimeMillis());
        List<String> startNumbersList = startNumbersMap.get(cardPaymentSystem);
        if (startNumbersList == null){
            log.error("Payment System [{}] not found. Couldn't generate card", cardPaymentSystem);
            throw new PaymentServiceException("Wrong card payment system");
        }


        String startNumbers = startNumbersList.get(random.nextInt(startNumbersList.size()));
        StringBuilder builder = new StringBuilder(startNumbers);

        //First 4 numbers refer to bank info
        for (int i = 0; i < 4 - startNumbers.length(); i++) {
            builder.append(random.nextInt(10));
        }

        //append currencyNumber
        String currencyNumber = AccountNumberGenerator.getCurrencyNumberMap().get(currency);
        if (currencyNumber != null)
            builder.append(currencyNumber);
        else {
            log.error("Currency [{}] not found. Couldn't generate card", currency);
            throw new PaymentServiceException("Wrong currency");
        }

        //append typeNumber
        String typeNumber = typeNumberMap.get(typeName);
        if (typeNumber != null)
            builder.append(typeNumber);
        else {
            log.error("Type [{}] not found. Couldn't generate card", typeName);
            throw new PaymentServiceException("Wrong type of card");
        }

        //append unique number(UUID_hash)
        if (uuidHash.length() > 15 - builder.length()){
            uuidHash = uuidHash.substring(0, 15 - builder.length());
            builder.append(uuidHash);
        }
        else {
            builder.append(uuidHash);
            int countToMake15 = 15 - builder.length();
            for (int i = 0; i < countToMake15; i++) {
                builder.append(random.nextInt(10));
            }
        }

        //append check number
        builder.append(getCheckDigit(builder.toString()));

        return builder.toString();
    }


    //required to make sum % 10 == 0
    private static int getCheckDigit(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }
}
