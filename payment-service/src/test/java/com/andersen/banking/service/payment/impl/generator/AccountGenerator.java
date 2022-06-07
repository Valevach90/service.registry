package com.andersen.banking.service.payment.impl.generator;

import com.andersen.banking.service.payment.meeting_api.dto.AccountDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class AccountGenerator {

    private static final AtomicLong counter = new AtomicLong(1L);
    private Faker faker;

    public Account generate() {
        Account account = new Account();

        account.setId(counter.getAndIncrement());
        account.setUserId(faker.number().numberBetween(1L, 123456L));
        account.setAccountNumber(faker.regexify("[0-9]{8}"));
        account.setBankName(faker.company().name());
        account.setIssueDate(new Date(faker.number().numberBetween(1,99999)));
        account.setTerminationDate(new Date(faker.number().numberBetween(1,99999)));
        account.setCurrency(faker.currency().name());

        return account;
    }

    @PostConstruct
    private void init() {
        faker = new Faker();
    }
}

