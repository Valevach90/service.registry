package com.andersen.banking.service.registry.meeting_api.controller;

import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for mapping users.
 */
@Tag(name = "User Authentication controller", description = "work with authentication users")
@RequestMapping(value = "/api/v1/user")
@RestController
public interface UserAuthenticationController {

    @Operation(summary = "Get user by user id",
            description = "get user information by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    UserDto findUser(Authentication authentication);

    @Operation(summary = "Create user",
            description = "create user by params in dto object")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    UserDto create(
            Authentication authentication,
            @Parameter(description = "user", required = true) @RequestBody @Validated UserDto userDto);

    @Operation(summary = "Update user",
            description = "update user by params in dto object")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping
    void updateUser(Authentication authentication,
            @Parameter(description = "user id", required = true)
            @RequestBody
            @Validated UserDto userDto);

    @Operation(summary = "Delete user",
            description = "delete user by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping
    void deleteById(Authentication authentication);
}
