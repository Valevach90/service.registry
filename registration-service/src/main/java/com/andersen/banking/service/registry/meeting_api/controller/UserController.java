package com.andersen.banking.service.registry.meeting_api.controller;

import static com.andersen.banking.service.registry.meeting_impl.security.SecurityUtil.ADMIN;
import static com.andersen.banking.service.registry.meeting_impl.security.SecurityUtil.EMPLOYEE;

import com.andersen.banking.service.registry.meeting_api.dto.user.UserCreateResponseDto;
import com.andersen.banking.service.registry.meeting_api.dto.user.UserRequestDto;
import com.andersen.banking.service.registry.meeting_api.dto.user.UserResponseDto;
import com.andersen.banking.service.registry.meeting_api.dto.user.UserUpdateEmailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import javax.annotation.security.RolesAllowed;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for mapping authentication users.
 */
@Tag(name = "User controller", description = "work with users")
@RequestMapping(value = "/api/v1/users")
@RestController
public interface UserController {

    @Operation(summary = "Get all users",
            description = "get all users information")
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @RolesAllowed({ADMIN, EMPLOYEE})
    Page<UserResponseDto> findAll(
            @ParameterObject
            @PageableDefault(
                    sort = {"id"},
                    direction = Sort.Direction.DESC) Pageable pageable);

    @Operation(summary = "Get user by user id",
            description = "get user information by id")
    @GetMapping("/find")
    @SecurityRequirement(name = "Bearer Authentication")
    UserResponseDto findUser(Authentication authentication);

    @Operation(summary = "Create user",
            description = "create user by params in dto object")
    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @RolesAllowed({ADMIN, EMPLOYEE})
    UserResponseDto create(@Parameter(description = "user", required = true)
    @RequestBody @Validated UserCreateResponseDto userResponseDto);

    @Operation(summary = "Update user",
            description = "update user by params in dto object")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping
    void updateUser(
            Authentication authentication,
            @Parameter(description = "user id", required = true)
            @RequestBody
            @Validated UserRequestDto userDto);

    @Operation(summary = "Update user email",
            description = "update user email by params in dto object")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/email")
    void updateUserEmail(
            Authentication authentication,
            @Parameter(description = "user id", required = true)
            @RequestBody
            @Validated UserUpdateEmailDto userUpdateEmailDto);

    @Operation(summary = "Delete user",
            description = "delete user by id")
    @DeleteMapping("/{id}")
    void deleteById(@Parameter(description = "user id", required = true)
    @PathVariable UUID id);
}
