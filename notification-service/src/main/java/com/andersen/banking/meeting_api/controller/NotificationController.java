package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.RegistrationNotificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for sending notifications.
 */

@Tag(name = "Notification Controller", description = "sending notifications")
@RequestMapping("/api/v1/notifications")
public interface NotificationController {

    @Operation(summary = "Send email notification",
            description = "send notification on email"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/email")
    void sendEmailNotification(
            Authentication authentication
    );

    @Operation(summary = "Confirm email notification",
            description = "confirm code received by email"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/email")
    Boolean confirmEmailNotification(
            Authentication authentication,
            @Parameter(description = "confirmation code", required = true)
            @RequestBody String code
    );

    @Operation(summary = "Block email address",
            description = "block email address"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/email/block")
    void blockEmailAddress(
            Authentication authentication
    );

    @Operation(summary = "Check email address",
            description = "check if email address is blocked"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/email/blocked")
    Boolean isEmailAddressBlocked(
            Authentication authentication
    );

    @Operation(summary = "Get notification",
            description = "get saved notification"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/email/notification")
    RegistrationNotificationDto getNotification(
            Authentication authentication
    );
}