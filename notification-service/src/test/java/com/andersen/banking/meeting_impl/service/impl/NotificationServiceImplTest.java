package com.andersen.banking.meeting_impl.service.impl;

import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.CODE;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.EMAIL;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.STATUS_BLOCKED;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.STATUS_SEND;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.TIME_FUTURE;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.TIME_LAST;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.givenBlocking;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.givenCode;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.givenRegistrationNotification;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.givenTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.andersen.banking.meeting_db.entity.RegistrationNotification;
import com.andersen.banking.meeting_db.repository.NotificationRepository;
import com.andersen.banking.meeting_impl.util.properties.NotificationMailProperties;
import com.andersen.banking.meeting_impl.util.properties.NotificationMailProperties.Blocking;
import com.andersen.banking.meeting_impl.util.properties.NotificationMailProperties.Code;
import com.andersen.banking.meeting_impl.util.properties.NotificationMailProperties.Time;
import java.sql.Timestamp;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceImplTest {

  @Mock
  private NotificationMailProperties mail;
  @Mock
  private NotificationRepository notificationRepository;

  @InjectMocks
  private NotificationServiceImpl notificationService;

  @Test
  void ConfirmCodeReceivedByEmailNotificationWithFailure() {

    when(notificationRepository.findByEmail(EMAIL))
        .thenReturn(Optional.of(givenRegistrationNotification(TIME_LAST, STATUS_SEND)));
    when(mail.getCode()).thenReturn(givenCode(1, givenTime(3)));

    var actualResult = notificationService
        .confirmCodeReceivedByEmailNotification(EMAIL, CODE);

    verify(notificationRepository, times(1)).findByEmail(EMAIL);
    verify(mail, times(1)).getCode();

    assertFalse(actualResult);
  }

  @Test
  void confirmCodeReceivedByEmailNotificationWithSuccess() {

    when(notificationRepository.findByEmail(EMAIL))
        .thenReturn(Optional.of(givenRegistrationNotification(TIME_FUTURE, STATUS_SEND)));
    when(mail.getCode()).thenReturn(givenCode(1, givenTime(3)));

    var actualResult = notificationService
        .confirmCodeReceivedByEmailNotification(EMAIL, CODE);

    verify(notificationRepository, times(1)).findByEmail(EMAIL);
    verify(mail, times(1)).getCode();

    assertTrue(actualResult);
  }

  @Test
  void isEmailAddressBlockedWithSuccess() {

    when(notificationRepository.findByEmail(EMAIL))
        .thenReturn(Optional.of(givenRegistrationNotification(TIME_FUTURE, STATUS_BLOCKED)));
    when(mail.getBlocking()).thenReturn(givenBlocking(givenTime(1)));

    var actualResult = notificationService
        .isEmailAddressBlocked(EMAIL);

    verify(notificationRepository, times(1)).findByEmail(EMAIL);
    verify(mail, times(1)).getBlocking();

    assertTrue(actualResult);
  }

  @Test
  void isEmailAddressBlockedWithFailure() {

    when(notificationRepository.findByEmail(EMAIL))
        .thenReturn(Optional.of(givenRegistrationNotification(TIME_LAST, STATUS_SEND)));

    var actualResult = notificationService
        .isEmailAddressBlocked(EMAIL);

    verify(notificationRepository, times(1)).findByEmail(EMAIL);

    assertFalse(actualResult);
  }

  @Test
  void blockEmailAddress() {
  }

  @Test
  void getNotification() {

    when(notificationRepository.findByEmail(EMAIL)).thenReturn(
        Optional.of(givenRegistrationNotification(TIME_LAST, STATUS_SEND)));

    var actualResult = notificationService.getNotification(EMAIL);

    actualResult.ifPresent(
        registrationNotification -> assertEquals(EMAIL, registrationNotification.getEmail()));
  }


  public static class Constants {

    public static final String EMAIL = "michail@mail.ru";
    public static final String CODE = "0002";
    public static final Timestamp TIME_LAST = Timestamp.valueOf("2022-06-06 13:33:29.573");
    public static final Timestamp TIME_FUTURE = Timestamp.valueOf("2023-06-06 13:33:29.573");
    public static final String STATUS_SEND = "sent";
    public static final String STATUS_BLOCKED = "blocked";

    public static RegistrationNotification givenRegistrationNotification(
        Timestamp time, String status) {
      return RegistrationNotification
          .builder()
          .email(EMAIL)
          .code(CODE)
          .time(time)
          .status(status)
          .build();
    }

    public static Code givenCode(int length, Time valid) {
      Code codeResult = new Code();
      codeResult.setValid(valid);
      codeResult.setLength(length);
      return codeResult;
    }

    public static Time givenTime(int millis) {
      Time timeResult = new Time();
      timeResult.setMillis(millis);
      return timeResult;
    }

    public static Blocking givenBlocking(Time time) {
      Blocking blockingResult = new Blocking();
      blockingResult.setTime(time);
      return blockingResult;
    }

  }
}
