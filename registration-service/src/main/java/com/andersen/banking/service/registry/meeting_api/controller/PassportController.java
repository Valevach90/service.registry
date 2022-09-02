package com.andersen.banking.service.registry.meeting_api.controller;

import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * Controller for passport.
 */
@Tag(name = "Passport Controller", description = "work with passports")
@RequestMapping("/api/v1/passport")
public interface PassportController {

    @Operation(summary = "passport info",
            description = "get information about passport"
    )
    @GetMapping(value = "/{id}")
    PassportDto findById(
            @Parameter(description = "passport id", required = true)
            @PathVariable("id") Long id
    );

    @Operation(summary = "passport info",
            description = "get information about user's passport"
    )
    @GetMapping(value = "user/{id}")
    PassportDto findByUserId(
            @Parameter(description = "user id", required = true)
            @PathVariable("id") UUID id
    );

    @Operation(summary = "user passport info",
            description = "get information about user's passport"
    )
    @GetMapping(value = "address/{id}")
    PassportDto findByAddressId(
            @Parameter(description = "address id", required = true)
            @PathVariable("id") Long id
    );

    @Operation(summary = "all passports",
            description = "get page of all users passport"
    )
    @GetMapping(value = "/all")
    Page<PassportDto> findAll(
            @ParameterObject
            @PageableDefault Pageable pageable
    );

    @Operation(summary = "Update passport",
            description = "update passport by params in dto object")
    @PutMapping
    void update(
            @RequestBody
            @Validated PassportDto passportDto
    );

    @Operation(summary = "Delete passport",
            description = "delete passport")
    @DeleteMapping("/{id}")
    void deleteById(
            @Parameter(description = "passport id", required = true)
            @PathVariable("id") Long id
    );

    @Operation(summary = "Create passport",
            description = "create passport by params in dto object")
    @PostMapping
    PassportDto create(
            @RequestBody
            @Validated PassportDto passportDto
    );
}
