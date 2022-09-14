package com.andersen.banking.meeting_impl.util;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountNumberGeneratorTest {

    @Test
    void generateAccountNumber() {
      String bankName = "tinkoff";
      String currency = "USD";
      UUID owner_id = UUID.randomUUID();

      String accountNumber = AccountNumberGenerator.generateAccountNumber(bankName, currency, owner_id);

      Assertions.assertEquals(20, accountNumber.length());
    }
}