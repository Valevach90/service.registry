package com.andersen.banking.service.registry.meeting_api.controller;

import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    List<UserEntity> findAll();

    @Operation(summary = "Get user by user id",
            description = "get user information by user id")
    @GetMapping("/users/{id}")
    Optional<UserEntity> findUserById(@Parameter(description = "user id", required = true)
                         @PathVariable Long id);

    @Operation(summary = "Save user in database",
            description = "save user in database")
    @PostMapping("/users")
    Optional<UserEntity> saveUser(@Parameter(description = "user", required = true)
                         @RequestBody UserEntity user);

    @Operation(summary = "Update user",
            description = "update user by params in dto")
    @PutMapping("/users/{id}")
    void updateUser(
            @Parameter(description = "user id", required = true)
            @PathVariable Long id,
            @RequestBody UserEntity user
    );

    @Operation(summary = "Delete user by user id",
            description = "delete user information by user id")
    @DeleteMapping("/users/{id}")
    void deleteUserById(@Parameter(description = "user id", required = true)
                         @PathVariable Long id);

}
