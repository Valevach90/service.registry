package com.andersen.banking.service.registry.meeting_impl.controller;

import static com.andersen.banking.service.registry.meeting_impl.util.MailNotificationUtil.extractEmailFromToken;

import com.andersen.banking.service.registry.meeting_api.controller.NotificationController;
import com.andersen.banking.service.registry.meeting_api.dto.NotificationDto;
import com.andersen.banking.service.registry.meeting_db.entities.Notification;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.mapping.NotificationMapper;
import com.andersen.banking.service.registry.meeting_impl.service.NotificationService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void sendEmailNotification(Jwt jwt) {

        String email = extractEmailFromToken(jwt);

        log.trace("Sending email notification: {}", email);

        notificationService.sendEmailNotification(email);

        log.trace("Notification sent by mail: {}", email);

    }

    @Override
    public Boolean confirmEmailNotification(Jwt jwt, String code) {

        String email = extractEmailFromToken(jwt);

        log.trace("Confirmation whether code {} was sent by mail {}", code, email);

        Boolean confirmation = notificationService.confirmCodeReceivedByEmailNotification(email, code);

        log.trace("Code {} was sent by mail {} : {}", code, email, confirmation);

        return confirmation;
    }

    @Override
    public void blockEmailAddress(Jwt jwt) {
        String email = extractEmailFromToken(jwt);

        log.trace("Block email {} for notifications", email);

        notificationService.blockEmailAddress(email);

        log.trace("Email {} blocked for notifications", email);
    }

    @Override
    public Boolean isEmailAddressBlocked(Jwt jwt) {
        String email = extractEmailFromToken(jwt);

        log.trace("Checking if email {} is blocked", email);

        Boolean blockingStatus = notificationService.isEmailAddressBlocked(email);

        log.trace("Checked: email {} is blocked: {}", email, blockingStatus);

        return blockingStatus;
    }

    @Override
    public NotificationDto getNotification(Jwt jwt) {
        String email = extractEmailFromToken(jwt);

        log.trace("Find notification by email: {}", email);

        Optional<Notification> notification = notificationService.getNotification(email);

        NotificationDto notificationDto = notificationMapper.toNotificationDto(notification.orElseThrow(
                () -> new NotFoundException(Notification.class, email)));

        log.trace("Found notification: {}", notificationDto);

        return notificationDto;
    }
}
