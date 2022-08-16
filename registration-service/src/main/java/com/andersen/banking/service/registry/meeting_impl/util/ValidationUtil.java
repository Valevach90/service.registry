package com.andersen.banking.service.registry.meeting_impl.util;

import com.andersen.banking.service.registry.meeting_impl.exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private ValidationUtil() {}

    private static final String PASSWORD_REGEX = "^(?=.*[0-9]*)(?=.*[a-z]*)(?=.*[A-Z]*)(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]*)\\S{6,20}$";
    private static final String CYRILLIC_CHARACTERS_REGEX = ".*[А-Яа-я]+.*";

    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern cyrillicCharacterPattern = Pattern.compile(CYRILLIC_CHARACTERS_REGEX, Pattern.UNICODE_CHARACTER_CLASS);

    public static boolean isPasswordValid(String password) {

        Matcher passwordMatcher = passwordPattern.matcher(password);
        Matcher cyrillicCharacterMatcher = cyrillicCharacterPattern.matcher(password);

        if (!cyrillicCharacterMatcher.matches() && passwordMatcher.matches()) {
            return true;
        }
        return false;
    }
}
