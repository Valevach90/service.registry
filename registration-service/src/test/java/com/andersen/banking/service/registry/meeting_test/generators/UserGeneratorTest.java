package com.andersen.banking.service.registry.meeting_test.generators;

import com.andersen.banking.service.registry.meeting_db.entities.User;
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
        User user = userGenerator.generateUser();

        assertNotNull(user.getId());
        assertNotNull(user.getFirstName());
    }

    @Test
    void whenCreateTwoUsers_theyAreDifferent() {
        User user1 = userGenerator.generateUser();
        User user2 = userGenerator.generateUser();

        assertNotEquals(user1, user2);
    }
}