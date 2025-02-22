package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.PushNotificationRequest;
import com.andersen.banking.meeting_api.dto.PushNotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for push notifications.
 */
@Tag(name = "Push Notification Controller", description = "Controller for sending push notifications")
@RequestMapping("/api/v1/notifications/push")
public interface PushNotificationController {

    @Operation(summary = "Send push notification", description = "Sends push notification based on token in request")
    @PostMapping("/token")
    ResponseEntity<PushNotificationResponse> sendTokenNotification(
            @RequestBody @Validated PushNotificationRequest request);
}
