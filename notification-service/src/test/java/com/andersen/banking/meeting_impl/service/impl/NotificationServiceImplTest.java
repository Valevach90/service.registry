package com.andersen.banking.meeting_impl.service.impl;

import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.CODE;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.EMAIL;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.EMAIL_SENDER;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.STATUS_BLOCKED;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.STATUS_SEND;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.TIME_FUTURE;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.Constants.TIME_LAST;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.NotificationFactory.buildBlocking;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.NotificationFactory.buildCode;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.NotificationFactory.buildRegistrationNotification;
import static com.andersen.banking.meeting_impl.service.impl.NotificationServiceImplTest.NotificationFactory.buildTime;
import static com.andersen.banking.meeting_impl.util.MailNotificationUtil.createMessage;
import static com.andersen.banking.meeting_impl.util.MailNotificationUtil.createNotification;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceImplTest {

    @Mock
    private NotificationMailProperties mail;
    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Test
    void sendEmailNotificationIsSuccess() {

        when(mail.getCode()).thenReturn(buildCode(1, buildTime(10)));

        RegistrationNotification registrationNotification = createNotification(
            mail.getCode().getLength(), EMAIL);

        final var emailSenderMocked = mock(JavaMailSender.class);
        SimpleMailMessage message = createMessage(registrationNotification);

        ArgumentCaptor<RegistrationNotification> registrationNotificationArgumentCaptor =
            ArgumentCaptor.forClass(
                RegistrationNotification.class
            );
        ArgumentCaptor<SimpleMailMessage> messageArgumentCaptor = ArgumentCaptor.forClass(
            SimpleMailMessage.class);

        setEmailSenderByReflection(emailSenderMocked);

        doReturn(new RegistrationNotification())
            .when(notificationRepository).save(registrationNotificationArgumentCaptor.capture());
        doNothing().when(emailSenderMocked).send(messageArgumentCaptor.capture());

        notificationService.sendEmailNotification(EMAIL);

        verify(notificationRepository, times(1)).save(
            registrationNotificationArgumentCaptor.capture());
        verify(emailSenderMocked, times(1))
            .send(messageArgumentCaptor.capture());

        assertEquals(registrationNotification.getStatus(),
            registrationNotificationArgumentCaptor.getValue().getStatus());
        assertEquals(message.getSubject(), messageArgumentCaptor.getValue().getSubject());
    }


    @Test
    void confirmCodeReceivedByEmailNotificationWithFailure() {

        when(notificationRepository.findByEmail(EMAIL))
            .thenReturn(Optional.of(buildRegistrationNotification(TIME_LAST, STATUS_SEND)));
        when(mail.getCode()).thenReturn(buildCode(1, buildTime(3)));

        var actualResult = notificationService
            .confirmCodeReceivedByEmailNotification(EMAIL, CODE);

        verify(notificationRepository, times(1)).findByEmail(EMAIL);
        verify(mail, times(1)).getCode();

        assertFalse(actualResult);
    }

    @Test
    void confirmCodeReceivedByEmailNotificationWithSuccess() {

        when(notificationRepository.findByEmail(EMAIL))
            .thenReturn(Optional.of(buildRegistrationNotification(TIME_FUTURE, STATUS_SEND)));
        when(mail.getCode()).thenReturn(buildCode(1, buildTime(3)));

        var actualResult = notificationService
            .confirmCodeReceivedByEmailNotification(EMAIL, CODE);

        verify(notificationRepository, times(1)).findByEmail(EMAIL);
        verify(mail, times(1)).getCode();

        assertTrue(actualResult);
    }

    @Test
    void isEmailAddressBlockedWithSuccess() {

        when(notificationRepository.findByEmail(EMAIL))
            .thenReturn(Optional.of(buildRegistrationNotification(TIME_FUTURE, STATUS_BLOCKED)));
        when(mail.getBlocking()).thenReturn(buildBlocking(buildTime(1)));

        var actualResult = notificationService
            .isEmailAddressBlocked(EMAIL);

        verify(notificationRepository, times(1)).findByEmail(EMAIL);
        verify(mail, times(1)).getBlocking();

        assertTrue(actualResult);
    }

    @Test
    void isEmailAddressBlockedWithFailure() {

        when(notificationRepository.findByEmail(EMAIL))
            .thenReturn(Optional.of(buildRegistrationNotification(TIME_LAST, STATUS_SEND)));

        var actualResult = notificationService
            .isEmailAddressBlocked(EMAIL);

        verify(notificationRepository, times(1)).findByEmail(EMAIL);

        assertFalse(actualResult);
    }

    @Test
    void blockEmailAddressIsSuccess() {

        ArgumentCaptor<RegistrationNotification> registrationNotificationArgumentCaptor =
            ArgumentCaptor.forClass(RegistrationNotification.class);

        doReturn(new RegistrationNotification())
            .when(notificationRepository).save(registrationNotificationArgumentCaptor.capture());

        notificationService.blockEmailAddress(EMAIL);

        verify(notificationRepository, times(1)).save(
            registrationNotificationArgumentCaptor.capture());

        assertEquals(buildRegistrationNotification(TIME_FUTURE, STATUS_BLOCKED).getStatus(),
            registrationNotificationArgumentCaptor.getValue().getStatus());
    }

    @Test
    void getNotificationIsReturnedNotification() {

        when(notificationRepository.findByEmail(EMAIL)).thenReturn(
            Optional.of(buildRegistrationNotification(TIME_LAST, STATUS_SEND)));

        var actualResult = notificationService.getNotification(EMAIL);

        assertFalse(actualResult.isEmpty());
        assertEquals(EMAIL, actualResult.get().getEmail());

        actualResult.ifPresent(
            registrationNotification -> assertEquals(EMAIL, registrationNotification.getEmail()));
    }

    @Test
    void getNotificationIsNotReturnedNotification() {

        when(notificationRepository.findByEmail(EMAIL)).thenReturn(
            Optional.empty());

        var actualResult = notificationService.getNotification(EMAIL);

        assertTrue(actualResult.isEmpty());
    }

    private void setEmailSenderByReflection(JavaMailSender emailSenderMocked) {
        try {
            var field = notificationService.getClass().getDeclaredField(EMAIL_SENDER);
            field.setAccessible(true);
            field.set(notificationService, emailSenderMocked);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Constants {

        public static final String EMAIL = "michail@mail.ru";
        public static final String CODE = "0002";
        public static final Timestamp TIME_LAST = Timestamp.valueOf("2022-06-06 13:33:29.573");
        public static final Timestamp TIME_FUTURE = Timestamp.valueOf("2023-06-06 13:33:29.573");
        public static final String STATUS_SEND = "sent";
        public static final String STATUS_BLOCKED = "blocked";
        public static final String EMAIL_SENDER = "emailSender";

    }

    public static class NotificationFactory {

        public static RegistrationNotification buildRegistrationNotification(
            Timestamp time, String status) {
            return RegistrationNotification
                .builder()
                .email(EMAIL)
                .code(CODE)
                .time(time)
                .status(status)
                .build();
        }

        public static Code buildCode(int length, Time valid) {
            Code codeResult = new Code();
            codeResult.setValid(valid);
            codeResult.setLength(length);
            return codeResult;
        }

        public static Time buildTime(int millis) {
            Time timeResult = new Time();
            timeResult.setMillis(millis);
            return timeResult;
        }

        public static Blocking buildBlocking(Time time) {
            Blocking blockingResult = new Blocking();
            blockingResult.setTime(time);
            return blockingResult;
        }
    }
}
