package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.CardProductDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentRequestDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentResponseDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentUpdateDto;
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

import java.util.UUID;

@Tag(
        name = "RegularPayment controllers",
        description = "Endpoints to work with Regular Payment entity.")
@RequestMapping(value = "/api/v1/payments/regular")
@RestController
public interface RegularPaymentController {

    @Operation(
            summary = "Create regular payment",
            description = "create regular payment by params in dto object")
    @PostMapping("/")
    RegularPaymentResponseDto create(
            @Parameter(description = "regular_payment", required = true) @RequestBody @Validated
                    RegularPaymentRequestDto regularPaymentDto);

    @Operation(
            summary = "Updates regular payment",
            description = "update regular payment by params in dto object")
    @PutMapping("/")
    RegularPaymentResponseDto update(
            @Parameter(description = "regular_payment_id", required = true) @RequestBody @Validated
            RegularPaymentUpdateDto regularPaymentUpdateDto);

    @Operation(
            summary = "Get regular payment by id",
            description = "get regular payment by id")
    @GetMapping("/{id}")
    RegularPaymentResponseDto findById(
            @Parameter(description = "regular payment id", required = true) @PathVariable UUID id
    );

    @Operation(
            summary = "Get all regular payments",
            description = "get card regular payments")
    @GetMapping("/")
    Page<RegularPaymentResponseDto> findAll(@ParameterObject @PageableDefault(
            sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable);

    @Operation(
            summary = "Delete regular payment",
            description = "delete regular payment by id")
    @DeleteMapping("/{id}")
    void deleteById(
            @Parameter(description = "regular payments id", required = true) @PathVariable UUID id
    );
}
