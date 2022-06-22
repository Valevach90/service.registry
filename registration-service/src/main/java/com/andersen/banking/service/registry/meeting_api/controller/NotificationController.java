package com.andersen.banking.service.registry.meeting_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for sending notifications.
 */

@Tag(name = "Notification Controller", description = "sending notifications")
@RequestMapping("/api/v1/notifications")
public interface NotificationController {

    @Operation(summary = "send email notification",
            description = "send notification on email"
    )
    @GetMapping("/email")
    void sendEmailNotification(
            @AuthenticationPrincipal Jwt jwt
    );

    @Operation(summary = "confirm email notification",
            description = "confirm code received by email"
    )
    @PostMapping("/email")
    Boolean confirmEmailNotification(
            @AuthenticationPrincipal Jwt jwt,
            @Parameter(description = "confirmation code", required = true)
            @RequestBody String code
    );
}