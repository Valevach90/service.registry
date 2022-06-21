package com.andersen.banking.service.payment.meeting_api.controller;

import com.andersen.banking.service.payment.meeting_api.dto.CardDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Interface that presents basic endpoints for working with Card entity.
 */

@Tag(name = "Card controllers", description = "Endpoints to work with Card entity.")
@RequestMapping(value = "/api/v1/cards")
@RestController
public interface CardController {

    @Operation(summary = "Get card by card id",
            description = "get card information by id")
    @GetMapping("/{id}")
    CardDto findById(@Parameter(description = "user id", required = true)
                     @PathVariable Long id);

    @Operation(summary = "Get all cards",
            description = "get page cards information")
    @GetMapping("/")
    Page<CardDto> findAll(
            @ParameterObject
            @PageableDefault Pageable pageable);

    @Operation(summary = "Get all cards by account_id ",
            description = "get page cards by account_id")
    @GetMapping("/accounts/{id}")
    Page<CardDto> findAllByAccountId(@Parameter(description = "account id", required = true) @PathVariable Long id,
                                     @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable);

    @Operation(summary = "Update card",
            description = "update card by params in dto object")
    @PutMapping("/{id}")
    CardDto updateCard(
            @Parameter(description = "card id", required = true)
            @RequestBody
            @Validated CardDto cardDto);

    @Operation(summary = "Delete card",
            description = "delete card by id")
    @DeleteMapping("/{id}")
    CardDto deleteById(@Parameter(description = "card id", required = true)
                       @PathVariable Long id);

    @Operation(summary = "Create card",
            description = "create card by params in dto object")
    @PostMapping("/")
    CardDto create(@Parameter(description = "card", required = true)
                   @RequestBody
                   @Validated CardDto cardDto);
}
