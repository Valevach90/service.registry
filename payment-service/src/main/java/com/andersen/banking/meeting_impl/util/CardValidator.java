
package com.andersen.banking.meeting_impl.util;

public class CardValidator {

    //Luhn algorithm
    public static boolean checkCardNumber(String cardNumber){
        int nDigits = cardNumber.length();
        boolean isEven = (nDigits % 2 == 0);
        int sum = 0;

        for (int i = 0; i < nDigits; i++) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));

            if (isEven){
                digit = digit * 2;
                digit = digit % 10 + digit / 10;
            }

            sum+= digit;
            isEven = !isEven;
        }

        return sum % 10 == 0;
    }
}