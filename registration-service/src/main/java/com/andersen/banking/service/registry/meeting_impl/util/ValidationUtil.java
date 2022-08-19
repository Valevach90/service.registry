package com.andersen.banking.service.registry.meeting_impl.util;

import com.andersen.banking.service.registry.meeting_impl.exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private ValidationUtil() {}

    private static final String PASSWORD_REGEX = "^[0-9a-zA-Z!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]{6,20}$";

    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

    public static boolean isPasswordValid(String password) {

        Matcher passwordMatcher = passwordPattern.matcher(password);

        if (passwordMatcher.matches()) {
            return true;
        }
        return false;
    }
}
