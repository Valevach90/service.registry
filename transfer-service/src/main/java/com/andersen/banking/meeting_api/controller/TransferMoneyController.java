package com.andersen.banking.meeting_api.controller;


import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "TransferMoney controller", description = "work with transfers")
@RequestMapping(value = "/api/v1/transfer")
public interface TransferMoneyController {

    @Operation(summary = "Get all transfers",
            description = "get all transfers for user")
    @GetMapping("")
    List<TransferResponseDto> findAllByUserId(@RequestParam(required = true) UUID userId,
                                              @ParameterObject @PageableDefault Pageable pageable);


    @Operation(summary = "Get information about transfer by transfer id and user id",
            description = "get info about for user by transfer id")
    @GetMapping("/{transfer_id}")
    TransferResponseDto findById(@Parameter(description = "transfer_id", required = true)
                                 @PathVariable(value = "transfer_id") UUID transferId);


    @Operation(summary = "Get information about transfer status by transfer id",
            description = "get info about for status by transfer id")
    @GetMapping("/{transfer_id}/status")
    TransferStatusResponseDto findTransferStatusById(@Parameter(description = "transfer_id", required = true)
                                                     @PathVariable(value = "transfer_id") UUID transferId);


    @Operation(summary = "Create request on transfer money",
            description = "create request on transfer money with params in dto object")
    @PostMapping("")
    ResponseEntity<TransferResponseDto> create(
            @RequestBody @Validated
            TransferRequestDto transferRequestDto
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
