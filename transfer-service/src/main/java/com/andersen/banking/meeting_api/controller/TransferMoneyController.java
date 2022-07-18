package com.andersen.banking.meeting_api.controller;


import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TransferMoney controller", description = "work with transfers")
@RequestMapping(value = "/api/v1/transfers")
public interface TransferMoneyController {

    @Operation(summary = "Get all transfers",
            description = "get all transfers for user")
    @GetMapping("/{user_id}")
    List<TransferResponseDto> findAllByUserId(@Parameter(description = "user id", required = true)
                                              @PathVariable(value = "user_id") Long userId);

    @Operation(summary = "Get information about transfer by transfer id and user id",
            description = "get info about for user by transfer id")
    @GetMapping("/{user_id}/{transfer_id}")
    TransferResponseDto findById(@Parameter(description = "user id", required = true)
                                 @PathVariable(value = "user_id") Long userId,
                                 @Parameter(description = "transfer_id", required = true)
                                 @PathVariable(value = "transfer_id") Long transferId);

    @Operation(summary = "Create request on transfer money",
            description = "create request on transfer money with params in dto object")
    @PostMapping("/")
    TransferResponseDto create(
            @RequestBody
            @Validated TransferRequestDto transferRequestDto
    );


    @Operation(summary = "Get all payment types",
            description = "get all payment types")
    @GetMapping("/payment_types")
    List<PaymentTypeResponseDto> getAllPaymentTypes();

    @Operation(summary = "Get all currencies",
            description = "get all currencies")
    @GetMapping("/currencies")
    List<CurrencyResponseDto> getAllCurrency();

}
