package com.andersen.banking.service.registry.meeting_api.controller;

import com.andersen.banking.service.registry.meeting_api.dto.NotificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @GetMapping("/email")
    void sendEmailNotification(
            @AuthenticationPrincipal Jwt jwt
    );

    @Operation(summary = "Confirm email notification",
            description = "confirm code received by email"
    )
    @PostMapping("/email")
    Boolean confirmEmailNotification(
            @AuthenticationPrincipal Jwt jwt,
            @Parameter(description = "confirmation code", required = true)
            @RequestBody String code
    );

    @Operation(summary = "Block email address",
            description = "block email address"
    )
    @GetMapping("/email/block")
    void blockEmailAddress(
            @AuthenticationPrincipal Jwt jwt
    );

    @Operation(summary = "Check email address",
            description = "check if email address is blocked"
    )
    @GetMapping("/email/blocked")
    Boolean isEmailAddressBlocked(
            @AuthenticationPrincipal Jwt jwt
    );

    @Operation(summary = "Get notification",
            description = "get saved notification"
    )
    @GetMapping("/email/notification")
    NotificationDto getNotification(
            @AuthenticationPrincipal Jwt jwt
    );
}