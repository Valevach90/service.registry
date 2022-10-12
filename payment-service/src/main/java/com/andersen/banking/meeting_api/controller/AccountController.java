package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.AccountChangesResponseDto;
import com.andersen.banking.meeting_api.dto.AccountDto;
import com.andersen.banking.meeting_api.dto.AccountRegistrationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Interface that presents basic endpoints for working with Account entity.
 */
@Tag(name = "Account controller", description = "work with accounts")
@RequestMapping(value = "/api/v1/accounts")
public interface AccountController {

    @Operation(summary = "Create account", description = "create account by params from dto object")
    @PostMapping("/")
    AccountDto create(
            @Parameter(description = "account", required = true) @RequestBody @Validated
            AccountRegistrationDto accountRegistrationDto);

    @Operation(summary = "Get all accounts", description = "get page of account's information")
    @GetMapping("/")
    Page<AccountDto> findAll(@ParameterObject @PageableDefault(
            sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable);

    @Operation(
            summary = "Get accounts with ownerId",
            description = "get page of accounts with ownerId")
    @GetMapping("/owners/{id}")
    Page<AccountDto> findByOwnerId(
            @Parameter(description = "ownerId", required = true) @PathVariable("id") UUID id,
            @ParameterObject @PageableDefault(
                    sort = {"id"},
                    direction = Sort.Direction.DESC) Pageable pageable);

    @Operation(summary = "Get account by id", description = "get account information by id")
    @GetMapping("/{id}")
    AccountDto findById(
            @Parameter(description = "account id", required = true) @PathVariable("id") UUID id);

    @Operation(summary = "Update account", description = "update account by params into dto object")
    @PutMapping("/")
    AccountDto updateAccount(
            @Parameter(description = "account id", required = true) @RequestBody @Validated
            AccountDto accountDto);

    @Operation(summary = "Deactivate account", description = "deactivate account by id")
    @DeleteMapping("/{id}")
    AccountDto deactivateById(
            @Parameter(description = "account id", required = true) @PathVariable UUID id);

    @Operation(summary = "Get changes account", description = "Get changes account by id, their create, update and delete")
    @GetMapping("/changes/{id}")
    List<AccountChangesResponseDto> changes(
            @Parameter(description = "account id", required = true) @PathVariable UUID id);
}
