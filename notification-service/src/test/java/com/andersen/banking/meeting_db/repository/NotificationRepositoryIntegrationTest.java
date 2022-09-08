package com.andersen.banking.meeting_db.repository;

import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.EMAIL;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.STATUS_SEND;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.TIME_LAST;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.givenRegistrationNotification;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    notificationRepository.save(givenRegistrationNotification(TIME_LAST, STATUS_SEND));

    var actualResult = notificationRepository
        .findByEmail(EMAIL);

    actualResult.ifPresent(
        registrationNotification -> assertEquals(
            givenRegistrationNotification(TIME_LAST, STATUS_SEND), registrationNotification));
  }

  @AfterEach
  void cleanUp() {
    notificationRepository.deleteAll();
  }
}
