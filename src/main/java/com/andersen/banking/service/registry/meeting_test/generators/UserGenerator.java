package com.andersen.banking.service.registry.meeting_test.generators;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
public class UserGenerator {

    private Faker faker;
    private Random random;

    public User generateUser() {
        var user = new User();
        user.setId(random.nextLong(1_000_000));
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
        random = new Random();
    }
}

