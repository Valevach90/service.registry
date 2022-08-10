package com.andersen.banking.service.registry.meeting_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for user authentication.
 */

@Tag(name = "Authentication Controller", description = "work with user authentication")
@RequestMapping("/api/v1/auth")
public interface AuthController {

    @Operation(summary = "user authentication",
            description = "user authentication in registration service"
    )
    @GetMapping
    void auth(
            @AuthenticationPrincipal Jwt jwt
    );

    @Operation(summary = "Set up new passport",
            description = "Set up new passport in Keyclaok")
    @PostMapping("/password")
    void resetPassword(
            @AuthenticationPrincipal Jwt jwt,
            @Parameter(description = "new password", required = true)
            @RequestBody String newPassword
    );
}
