package com.andersen.banking.service.registry.meeting_api.controller;

import com.andersen.banking.service.registry.meeting_api.dto.RegistrationDto;
import com.andersen.banking.service.registry.meeting_api.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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
    RegistrationDto auth(
            @AuthenticationPrincipal Jwt jwt
    );

    @Operation(summary = "Set up new password",
            description = "Set up new password in Keycloak")
    @PostMapping("/password")
    void resetPassword(
            @AuthenticationPrincipal Jwt jwt,
            @Parameter(description = "new password", required = true)
            @RequestBody String newPassword
    );

    @Operation(summary = "Refresh token",
            description = "Refresh token when access token is expired")
    @PostMapping(value = "/refresh")
    TokenDto refreshToken(@RequestHeader("refresh-token") String refreshToken);

    @Operation(summary = "Logout user",
            description = "Remove access and refresh tokens for logged out user")
    @PostMapping(value = "/logout")
    void logout(@RequestHeader("refresh-token") String refreshToken);
}
