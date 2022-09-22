package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.CreditProductResponseDTO;
import com.andersen.banking.meeting_api.dto.CreditProductRequestDTO;
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
    CreditProductResponseDTO create(@RequestBody CreditProductRequestDTO creditProductDTO);

    @Operation(summary = "Get credit product",
        description = "get credit product by id"
    )
    @GetMapping(value = "/{id}")
    CreditProductResponseDTO getById(
        @Parameter(description = "credit product id", required = true)
        @PathVariable("id") UUID id
    );

    @Operation(summary = "Get credit products",
        description = "get ist of all credit products"
    )
    @GetMapping
    List<CreditProductResponseDTO> getAll();

    @Operation(summary = "Update credit product",
        description = "update credit product by params in dto object")
    @PutMapping(value = "/{id}")
    CreditProductResponseDTO update(
        @PathVariable("id") UUID id,
        @RequestBody CreditProductRequestDTO creditProductDTO
    );

    @Operation(summary = "Delete credit product",
        description = "delete credit product by id")
    @DeleteMapping("/{id}")
    void deleteById(
        @Parameter(description = "credit product id", required = true)
        @PathVariable("id") UUID id
    );
}
