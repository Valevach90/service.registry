package com.andersen.banking.service.registry.meeting_api.controller;

import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<User> findAll(Pageable pageable);

    @Operation(summary = "Get user by user id",
            description = "get user information by user id")
    @GetMapping("/users/{id}")
    Optional<User> findUserById(@Parameter(description = "user id", required = true)
                         @PathVariable Long id);

    @Operation(summary = "Save user in database",
            description = "save user in database")
    @PostMapping("/users")
    User saveUser(@Parameter(description = "user", required = true)
                         @RequestBody User user);

    @Operation(summary = "Update user",
            description = "update user by params in dto")
    @PutMapping("/users/{id}")
    void updateUser(
            @Parameter(description = "user id", required = true)
            @PathVariable Long id,
            @RequestBody User user
    );

    @Operation(summary = "Delete user by user id",
            description = "delete user information by user id")
    @DeleteMapping("/users/{id}")
    void deleteUserById(@Parameter(description = "user id", required = true)
                         @PathVariable Long id);
    @Operation(summary = "Get all users dto",
            description = "get all users information")
    @GetMapping("/usersDto")
    Page<UserDto> findAllDto(Pageable pageable);

    @Operation(summary = "Get user by user id and return user dto",
            description = "get user information by user id dto")
    @GetMapping("/usersDto/{id}")
    UserDto findUserByIdUserDto(@Parameter(description = "user id", required = true)
                                          @PathVariable Long id);

}
