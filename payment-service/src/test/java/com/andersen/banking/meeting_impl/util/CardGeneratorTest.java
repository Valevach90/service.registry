package com.andersen.banking.meeting_impl.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardGeneratorTest {

    @Test
    void generateCardNumber() {
      String paymentSystem = "VISA";
      String typeName = "GOLD";
      String currency = "USD";
      long count = 21212;

      String cardNumber = CardGenerator.generateCardNumber(paymentSystem, typeName, currency, count);

      Assertions.assertAll(() -> {
        assertTrue(cardNumber.startsWith("4"));
        assertEquals(16, cardNumber.length());
      });
    }
}