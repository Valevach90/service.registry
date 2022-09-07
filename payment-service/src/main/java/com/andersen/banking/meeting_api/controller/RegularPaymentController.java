package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.RegularPaymentRequestDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
