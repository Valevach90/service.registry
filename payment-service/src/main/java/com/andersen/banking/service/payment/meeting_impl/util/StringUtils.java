package com.andersen.banking.service.payment.meeting_impl.util;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean checkIfStringContainsOnlyDigits(final String string) {
        return string.matches("[0-9]+");
    }
}
