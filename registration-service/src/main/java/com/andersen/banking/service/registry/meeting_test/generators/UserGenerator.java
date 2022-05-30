package com.andersen.banking.service.registry.meeting_test.generators;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Component
public class UserGenerator {

    private static final AtomicLong counter = new AtomicLong(1L);
    private Faker faker;

    public User generateUser() {
        var user = new User();
        user.setId(counter.getAndIncrement());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPatronymic(faker.elderScrolls().region());
        user.setPhone(faker.phoneNumber().phoneNumber());

        return user;
    }

    @PostConstruct
    private void init() {
        faker = new Faker();
    }
}
