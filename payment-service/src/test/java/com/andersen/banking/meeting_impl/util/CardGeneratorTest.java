package com.andersen.banking.meeting_impl.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardGeneratorTest {

    @Test
    void generateCardNumber() {
      String paymentSystem = "VISA";
      String typeName = "GOLD";
      String currency = "USD";
      UUID uuid = UUID.randomUUID();

      String cardNumber = CardGenerator.generateCardNumber(paymentSystem, typeName, currency, uuid);

      Assertions.assertAll(() -> {
        assertTrue(cardNumber.startsWith("4"));
        assertEquals(16, cardNumber.length());
      });
    }
}