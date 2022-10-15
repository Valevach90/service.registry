package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.AtmDtoRequest;
import com.andersen.banking.meeting_api.dto.AtmDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Atm controller", description = "work with ATMs")
@RequestMapping(value = "/api/v1/atm")
public interface AtmController {

    @Operation(summary = "Create ATM",
            description = "create ATM by params in dto object")
    @PostMapping
    AtmDtoResponse create(
            @RequestBody AtmDtoRequest atmDtoRequest
    );

    @Operation(summary = "Get a specific ATM",
            description = "get a specific ATM by id")
    @GetMapping("/{id}")
    AtmDtoResponse getById(
            @Parameter(description = "ATM id", required = true)
            @PathVariable("id") UUID id
    );

    @Operation(summary = "Get all ATM",
            description = "get page of all ATM")
    @GetMapping
    Page<AtmDtoResponse> getAll(
            @ParameterObject
            @PageableDefault Pageable pageable
    );

    @Operation(summary = "Get ATM on the street",
            description = "get page with ATM on the street")
    @GetMapping("/street/{id}")
    Page<AtmDtoResponse> getAllAtmOnTheStreet(
            @Parameter(description = "street id", required = true) @PathVariable("id") Long streetId,
            @ParameterObject @PageableDefault Pageable pageable
    );

    @Operation(summary = "Get all ATM on a specific bank branch")
    @GetMapping("/bankbranch/{id}")
    Page<AtmDtoResponse> getAllAtmOnTheBankBranchByBranchId(
            @Parameter(description = "Bank branch id", required = true) @PathVariable("id") Long bankBranchId,
            @ParameterObject @PageableDefault Pageable pageable
    );

    @Operation(summary = "Update ATM",
            description = "update ATM by params in dto object")
    @PutMapping("/{id}")
    AtmDtoResponse update(
            @RequestBody AtmDtoRequest atmDtoRequest
    );

    @Operation(summary = "Delete ATM",
            description = "delete ATM by id")
    @DeleteMapping("/{id}")
    void deleteById(
            @Parameter(description = "ATM id", required = true)
                    @PathVariable("id") UUID id
    );
}
