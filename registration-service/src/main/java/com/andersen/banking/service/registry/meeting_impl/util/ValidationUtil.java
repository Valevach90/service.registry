package com.andersen.banking.service.registry.meeting_impl.util;

import com.andersen.banking.service.registry.meeting_impl.exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private ValidationUtil() {}

    private static final String PASSWORD_REGEX = "^(?=.*[0-9]*)(?=.*[a-z]*)(?=.*[A-Z]*)(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]*)\\S{6,20}$";
    private static final String CYRILLIC_CHARACTERS_REGEX = "\\p{IsCyrillic}";

    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern cyrillicCharacterPattern = Pattern.compile(CYRILLIC_CHARACTERS_REGEX);

    public static boolean isPasswordValid(String password) {

        Matcher passwordMatcher = passwordPattern.matcher(password);
        Matcher cyrillicCharacterMatcher = cyrillicCharacterPattern.matcher(password);

        if (cyrillicCharacterMatcher.matches()){
            throw new ValidationException(String.format("Password contains not allowed cyrillic characters: password: {}", password));
        }
        if (!passwordMatcher.matches()) {
            throw new ValidationException(String.format("Password does not meet policy requirements: password: {}", password));
        }

        return true;
    }
}
