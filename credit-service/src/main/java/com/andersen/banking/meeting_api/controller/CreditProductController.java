package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.CreditProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Tag(name = "Credit Product Controller", description = "work with credit products")
@RequestMapping("/api/v1/credit-products")
public interface CreditProductController {

    @Operation(summary = "Create credit product",
        description = "create credit product by params in dto object")
    @PostMapping
    CreditProductDTO create(@RequestBody CreditProductDTO creditProductDTO);

    @Operation(summary = "Get credit product",
        description = "get credit product by id"
    )
    @GetMapping(value = "/{id}")
    CreditProductDTO getById(
        @Parameter(description = "credit product id", required = true)
        @PathVariable("id") UUID id
    );

    @Operation(summary = "Get credit products",
        description = "get ist of all credit products"
    )
    @GetMapping
    List<CreditProductDTO> getAll();

    @Operation(summary = "Update credit product",
        description = "update credit product by params in dto object")
    @PutMapping(value = "/{id}")
    CreditProductDTO update(
        @PathVariable("id") UUID id,
        @RequestBody CreditProductDTO creditProductDTO
    );

    @Operation(summary = "Delete credit product",
        description = "delete credit product by id")
    @DeleteMapping("/{id}")
    void deleteById(
        @Parameter(description = "credit product id", required = true)
        @PathVariable("id") UUID id
    );
}
