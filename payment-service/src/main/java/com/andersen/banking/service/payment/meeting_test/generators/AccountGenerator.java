package com.andersen.banking.service.payment.meeting_test.generators;

import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class AccountGenerator {

    private static final AtomicLong counter = new AtomicLong(1L);
    private Faker faker;

    public Account generateAccount() {
        Account account = new Account();
        account.setId(counter.getAndIncrement());
        account.setAccountNumber(faker.regexify("[0-9]{10}"));

        return account;
    }

    @PostConstruct
    private void init() {
        faker = new Faker();
    }
}
