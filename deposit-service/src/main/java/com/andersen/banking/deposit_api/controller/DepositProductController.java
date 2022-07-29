package com.andersen.banking.deposit_api.controller;

import com.andersen.banking.deposit_api.dto.DepositProductDto;
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
 * Controller for deposit product.
 */

@Tag(name = "Deposit Product Controller", description = "work with deposit products")
@RequestMapping("/api/v1/products")
public interface DepositProductController {

    @Operation(summary = "Create deposit product",
            description = "create deposit product by params in dto object")
    @PostMapping
    DepositProductDto create(
            @RequestBody
            @Validated DepositProductDto depositProductDto
    );

    @Operation(summary = "Get deposit product",
            description = "get deposit product by id"
    )
    @GetMapping(value = "/{id}")
    DepositProductDto findById(
            @Parameter(description = "deposit product id", required = true)
            @PathVariable("id") Long id
    );

    @Operation(summary = "Get all deposit products",
            description = "get page of all deposit products"
    )
    @GetMapping
    Page<DepositProductDto> findAll(
            @ParameterObject
            @PageableDefault Pageable pageable
    );

    @Operation(summary = "Update deposit product",
            description = "update deposit product by params in dto object")
    @PutMapping
    void update(
            @RequestBody
            @Validated DepositProductDto depositProductDto
    );

     @Operation(summary = "Delete deposit product",
            description = "delete deposit product by id")
    @DeleteMapping("/{id}")
    void deleteById(
            @Parameter(description = "deposit product id", required = true)
            @PathVariable("id") Long id
    );


    @Operation(summary = "Search deposit product",
    description = "search by depositName and currencyName")
    @GetMapping("/search")
    Page<DepositProductDto> findByDepositNameAndCurrency(@ParameterObject
                                      @PageableDefault Pageable pageable,

                                      @Parameter(description = "deposit product name")
                                      @RequestParam(value = "depositName", required = false) String depositName,

                                      @Parameter(description = "currency name")
                                      @RequestParam(value = "currency", required = false) String currency);

}
