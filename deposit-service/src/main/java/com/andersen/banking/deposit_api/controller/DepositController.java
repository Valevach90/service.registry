package com.andersen.banking.deposit_api.controller;

import com.andersen.banking.deposit_api.dto.DepositDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for deposit.
 */

@Tag(name = "Deposit Controller", description = "work with deposits")
@RequestMapping("/api/v1/deposits")
public interface DepositController {

    @Operation(summary = "Create deposit",
            description = "create deposit by params in dto object")
    @PostMapping
    DepositDto create(
            @RequestBody
            @Validated DepositDto depositDto
    );

     @Operation(summary = "Get deposit",
            description = "get deposit by id"
    )
    @GetMapping(value = "/{id}")
    DepositDto findById(
            @Parameter(description = "deposit id", required = true)
            @PathVariable("id") Long id
    );

    @Operation(summary = "Get all deposits",
            description = "get page of all deposits"
    )
    @GetMapping
    Page<DepositDto> findAll(
            @ParameterObject
            @PageableDefault Pageable pageable
    );

    @Operation(summary = "Update deposit",
            description = "update deposit by params in dto object")
    @PutMapping
    void update(
            @RequestBody
            @Validated DepositDto depositDto
    );

    @Operation(summary = "Delete deposit",
            description = "delete deposit by id")
    @DeleteMapping("/{id}")
    void deleteById(
            @Parameter(description = "deposit id", required = true)
            @PathVariable("id") Long id
    );
}
