package com.andersen.banking.service.registry.meeting_api.controller;

import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for mapping users.
 */
@Tag(name = "User controller", description = "work with users")
@RequestMapping(value = "/api/v1/")
@RestController
public interface UserController {

    @Operation(summary = "Get all users",
            description = "get all users information")
    @GetMapping("/users")
    Page<UserDto> findAll(
            @ParameterObject
            @PageableDefault Pageable pageable);

    @Operation(summary = "Get user by user id",
            description = "get user information by id")
    @GetMapping("/users/{id}")
    UserDto findById(@Parameter(description = "user id", required = true)
                         @PathVariable UUID id);

    @Operation(summary = "Create user",
            description = "create user by params in dto object")
    @PostMapping("/users")
    UserDto create(@Parameter(description = "user", required = true)
                         @RequestBody
                         @Validated UserDto userDto);

    @Operation(summary = "Update user",
            description = "update user by params in dto object")
    @PutMapping("/users/{id}")
    void updateUser(
            @Parameter(description = "user id", required = true)
            @RequestBody
            @Validated UserDto userDto);

    @Operation(summary = "Delete user",
            description = "delete user by id")
    @DeleteMapping("/users/{id}")
    void deleteById(@Parameter(description = "user id", required = true)
                         @PathVariable UUID id);
}
