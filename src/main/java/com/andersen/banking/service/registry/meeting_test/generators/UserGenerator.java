package com.andersen.banking.service.registry.meeting_test.generators;

import com.andersen.banking.service.registry.meeting_db.entities.Users;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
public class UserGenerator {
    private Faker faker;
    private Random random;

    public Users generateUser() {
        var user = new Users();
        user.setId(random.nextLong(1_000_000));
        user.setName(faker.name().fullName());

        return user;
    }

    @PostConstruct
    private void init() {
        faker = new Faker();
        random = new Random();
    }
}
