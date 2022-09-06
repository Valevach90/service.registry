package com.andersen.banking.service.registry.meeting_api.controller;

import com.andersen.banking.service.registry.meeting_api.dto.RegistrationDto;
import com.andersen.banking.service.registry.meeting_api.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    RegistrationDto auth(
            Authentication authentication
    );

    @Operation(summary = "Set up new password",
            description = "Set up new password in Keycloak")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/password")
    void resetPassword(
            Authentication authentication,
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
