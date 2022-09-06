package com.andersen.banking.service.registry.meeting_test.generators;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.github.javafaker.Faker;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class UserGenerator {

    private Faker faker;

    public User generateUser() {
        var user = new User();
        user.setId(UUID.randomUUID());
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
