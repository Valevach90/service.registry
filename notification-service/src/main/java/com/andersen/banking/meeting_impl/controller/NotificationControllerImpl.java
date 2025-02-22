package com.andersen.banking.meeting_impl.controller;

import static com.andersen.banking.meeting_impl.util.MailNotificationUtil.extractEmailFromToken;

import com.andersen.banking.meeting_api.controller.NotificationController;
import com.andersen.banking.meeting_api.dto.RegistrationNotificationDto;
import com.andersen.banking.meeting_db.entity.RegistrationNotification;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.NotificationMapper;
import com.andersen.banking.meeting_impl.service.NotificationService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

/**
 * Notification controller implementation.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class NotificationControllerImpl implements NotificationController {

    private final NotificationService notificationService;

    private final NotificationMapper notificationMapper;

    @Override
    public void sendEmailNotification(Authentication authentication) {

        String email = getEmail(authentication);

        log.trace("Sending email notification: {}", email);

        notificationService.sendEmailNotification(email);

        log.trace("Notification sent by mail: {}", email);

    }

    @Override
    public Boolean confirmEmailNotification(Authentication authentication, String code) {

        String email = getEmail(authentication);

        log.trace("Confirmation whether code {} was sent by mail {}", code, email);

        Boolean confirmation = notificationService.confirmCodeReceivedByEmailNotification(email,
                code);

        log.trace("Code {} was sent by mail {} : {}", code, email, confirmation);

        return confirmation;
    }

    @Override
    public void blockEmailAddress(Authentication authentication) {
        String email = getEmail(authentication);

        log.trace("Block email {} for notifications", email);

        notificationService.blockEmailAddress(email);

        log.trace("Email {} blocked for notifications", email);
    }

    private static String getEmail(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return extractEmailFromToken(jwt);
    }

    @Override
    public Boolean isEmailAddressBlocked(Authentication authentication) {
        String email = getEmail(authentication);

        log.trace("Checking if email {} is blocked", email);

        Boolean blockingStatus = notificationService.isEmailAddressBlocked(email);

        log.trace("Checked: email {} is blocked: {}", email, blockingStatus);

        return blockingStatus;
    }

    @Override
    public RegistrationNotificationDto getNotification(Authentication authentication) {
        String email = getEmail(authentication);

        log.trace("Find notification by email: {}", email);

        RegistrationNotification notification = notificationService.getNotification(email)
                .orElseThrow(
                        () -> new NotFoundException(RegistrationNotification.class, email));

        RegistrationNotificationDto registrationNotificationDto = notificationMapper
                .toNotificationDto(notification);

        log.trace("Found notification: {}", registrationNotificationDto);

        return registrationNotificationDto;
    }
}
