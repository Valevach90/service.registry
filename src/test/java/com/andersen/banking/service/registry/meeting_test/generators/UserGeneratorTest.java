package com.andersen.banking.service.registry.meeting_test.generators;

import com.andersen.banking.service.registry.meeting_db.entities.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = UserGenerator.class)
class UserGeneratorTest {

    @Autowired
    private UserGenerator userGenerator;

    @Test
    void whenCreateUser_allFieldCreated() {
        Users user = userGenerator.generateUser();

        assertNotNull(user.getId());
        assertNotNull(user.getName());
    }

    @Test
    void whenCreateTwoUsers_theyAreDifferent() {
        Users user1 = userGenerator.generateUser();
        Users user2 = userGenerator.generateUser();

        assertNotEquals(user1, user2);
    }

}