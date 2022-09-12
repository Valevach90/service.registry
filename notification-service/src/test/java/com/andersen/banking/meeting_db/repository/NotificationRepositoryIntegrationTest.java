package com.andersen.banking.meeting_db.repository;

import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.EMAIL;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.STATUS_SEND;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.TIME_LAST;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.NotificationFactory.buildRegistrationNotification;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class NotificationRepositoryIntegrationTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void findByEmailWithSuccess() {

        var givenRegistrationNotification = buildRegistrationNotification(
            TIME_LAST,
            STATUS_SEND
        );

        notificationRepository.save(givenRegistrationNotification);

        var actualResult = notificationRepository
            .findByEmail(EMAIL);

        assertFalse(actualResult.isEmpty());
        assertEquals(EMAIL, actualResult.get().getEmail());

        actualResult.ifPresent(
            registrationNotification -> assertEquals(
                givenRegistrationNotification, registrationNotification));
    }

    @Test
    void findByEmailWithFailure() {

        var givenRegistrationNotification = buildRegistrationNotification(
            TIME_LAST,
            STATUS_SEND
        );

        var actualResult = notificationRepository
            .findByEmail(EMAIL);

        assertTrue(actualResult.isEmpty());

        actualResult.ifPresent(
            registrationNotification -> assertNotEquals(
                givenRegistrationNotification, registrationNotification));
    }

    @AfterEach
    void cleanUp() {
        notificationRepository.deleteAll();
    }
}
