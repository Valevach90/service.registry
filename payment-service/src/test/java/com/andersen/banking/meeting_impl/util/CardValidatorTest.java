package com.andersen.banking.meeting_impl.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardValidatorTest {

    @Test
    void checkCardNumber() {
        String wrongCardNumber = "1234567899991234";
        String rightNumber = "5536913909604981";

        boolean wrongNumberBool = CardValidator.checkCardNumber(wrongCardNumber);
        boolean rightNumberBool = CardValidator.checkCardNumber(rightNumber);

        Assertions.assertAll(() -> {
            assertFalse(wrongNumberBool);
            assertTrue(rightNumberBool);
        });
    }
}