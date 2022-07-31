package com.andersen.banking.service.payment.meeting_api.controller;

import com.andersen.banking.service.payment.meeting_api.dto.TypeCardResponseDto;
import com.andersen.banking.service.payment.meeting_api.dto.TypeCardUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Interface that presents basic endpoints for working with TypeCard entity.
 */

@Tag(name = "TypeCard controllers", description = "Endpoints to work with TypeCard entity.")
@RequestMapping(value = "/api/v1/types")
@RestController
public interface TypeCardController {

    @Operation(summary = "Get type card by card id", description = "get type card information by id")
    @GetMapping("/{id}")
    TypeCardResponseDto findById(@Parameter(description = "card id", required = true)
                             @PathVariable Long id);

    @Operation(summary = "Update type card", description = "update type card by params in dto object")
    @PutMapping("/{id}")
    TypeCardResponseDto updateCard(@Parameter(description = "card id", required = true)
                                   @RequestBody
                                   @Validated TypeCardUpdateDto typeCardUpdateDto);
}
