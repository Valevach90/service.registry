package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.NotificationController;
import com.andersen.banking.service.registry.meeting_impl.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;
import static com.andersen.banking.service.registry.meeting_impl.util.MailNotificationUtil.*;

/**
 * Notification controller implementation.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class NotificationControllerImpl implements NotificationController {

    @Autowired
    NotificationService notificationService;

    @Override
    public void sendEmailNotification(Jwt jwt) {

        String email = extractEmailFromToken(jwt);

        log.trace("Sending email notification: " + email);

        notificationService.sendEmailNotification(email);

        log.trace("Notification sent by mail: " + email);

    }

    @Override
    public Boolean confirmEmailNotification(Jwt jwt, Long code) {

        String email = extractEmailFromToken(jwt);

        log.trace("Confirmation whether code " + code + " was sent by mail " + email);

        Boolean confirmation = notificationService.confirmCodeReceivedByEmailNotification(email, code);

        log.trace("Code " + code + " was sent by mail " + email + " : " + confirmation);

        return confirmation;
    }
}
