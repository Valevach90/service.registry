package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.CurrencyDto;
import com.andersen.banking.meeting_api.dto.DepositTypeDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductRequestCreateDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductRequestDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductResponseDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductFilterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for deposit product.
 */

@Tag(name = "Deposit Product Controller", description = "work with deposit products")
@RequestMapping("/api/v1/products")
public interface DepositProductController {

    @Operation(summary = "Create deposit product", description = "create deposit product by params in dto object")
    @PostMapping
    DepositProductResponseDto create(@RequestBody @Validated DepositProductRequestCreateDto depositProductRequestDto);

    @Operation(summary = "Get deposit product", description = "get deposit product by id")
    @GetMapping(value = "/{id}")
    DepositProductResponseDto findById(
            @Parameter(description = "deposit product id", required = true) @PathVariable("id") UUID id);

    @Operation(summary = "Get all deposit products", description = "get page of all deposit products")
    @GetMapping
    Page<DepositProductResponseDto> findAll(@ParameterObject @PageableDefault Pageable pageable);

    @Operation(summary = "Get all currencies for deposit products", description = "get list currencies")
    @GetMapping("/currencies")
    List<CurrencyDto> findCurrencies();

    @Operation(summary = "Get all types for deposit products", description = "get list type deposit product")
    @GetMapping("/types")
    List<DepositTypeDto> findDepositType();

    @Operation(summary = "Update deposit product", description = "update deposit product by params in dto object")
    @PutMapping
    void update(@RequestBody @Validated DepositProductRequestDto depositProductRequestDto);

    @Operation(summary = "Delete deposit product", description = "delete deposit product by id")
    @DeleteMapping("/{id}")
    void deleteById(
            @Parameter(description = "deposit product id", required = true) @PathVariable("id") UUID id);


    @Operation(summary = "Search deposit product", description = "search by depositName and currencyName")
    @GetMapping("/search")
    Page<DepositProductResponseDto> findByDepositNameAndCurrency(
            @ParameterObject @PageableDefault(
                    sort = {"id"},
                    direction = Sort.Direction.DESC) Pageable pageable,

            @Parameter(description = "deposit product name")
            @RequestParam(value = "depositName", required = false) String depositName,

            @Parameter(description = "currency name")
            @RequestParam(value = "currency", required = false) String currency);

    @Operation(summary = "Get deposit product available setting", description = "get deposit product available setting")
    @GetMapping("/filter")
    DepositProductFilterDto getDepositProductAvailableSetting();

    @Operation(summary = "Get filtered deposit products", description = "get filtered deposit products")
    @PostMapping("/filter")
    Page<DepositProductResponseDto> getFilteredDepositProducts(
            @ParameterObject @PageableDefault(
                    sort = {"id"},
                    direction = Sort.Direction.DESC) Pageable pageable,

            @RequestBody @Validated DepositProductFilterDto depositProductFilterDto);
}
