package com.andersen.banking.meeting_test.generators;

import com.andersen.banking.meeting_db.entities.Account;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountUnitTestGenerator {

    final static UUID OWNER_ID = UUID.randomUUID();

    public static Account populateAccount(Account account) {
        account.setAccountNumber("123454326");
        account.setOpenDate(LocalDate.of(2021, 10, 23));
        account.setCloseDate(LocalDate.of(2024, 10, 23));
        account.setOwnerId(OWNER_ID);
        account.setCurrency("BEL");
        account.setBankName("Tinkoff");
        account.setBalance(0);

        return account;
    }
}
