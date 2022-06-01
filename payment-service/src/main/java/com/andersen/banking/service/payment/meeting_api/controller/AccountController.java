package com.andersen.banking.service.payment.meeting_api.controller;

import com.andersen.banking.service.payment.meeting_api.dto.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



/**
 * Interface that presents basic endpoints for working with Account entity.
 */


@Tag(name = "Account controller", description = "work with accounts")
@RequestMapping(value = "/api/v1/accounts")
public interface AccountController {

    @Operation(summary = "Create account",
            description = "create account by params from dto object")
    @PostMapping("/")
    AccountDto create(@Parameter(description = "account", required = true)
                      @RequestBody
                      @Validated AccountDto accountDto);


    @Operation(summary = "Get all accounts",
            description = "get all accounts information")
    @GetMapping("/")
    Page<AccountDto> findAll(Pageable pageable);

    @Operation(summary = "Get account by id",
            description = "get account information by id")
    @GetMapping("/{id}")
    AccountDto findById(
            @Parameter(description = "account id", required = true)
            @PathVariable("id") Long id
    );

    @Operation(summary = "Update account",
            description = "update account by params into dto object")
    @PutMapping("/")
    AccountDto updateAccount(
            @Parameter(description = "account id", required = true)
            @RequestBody
            @Validated AccountDto accountDto
    );

    @Operation(summary = "Delete account",
            description = "delete account by id")
    @DeleteMapping("/{id}")
    AccountDto deleteById(@Parameter(description = "account id", required = true)
                          @PathVariable Long id);


}
