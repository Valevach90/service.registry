package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.CardProductCreateDto;
import com.andersen.banking.meeting_api.dto.CardProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Interface that presents basic endpoints for working with CardProduct entity. */
@Tag(name = "Card product controller", description = "Endpoints to work with Card product")
@RequestMapping(value = "/api/v1/products")
@RestController
public interface CardProductController {

    @Operation(
            summary = "Get card product by id",
            description = "get card product information by id")
    @GetMapping("/{id}")
    CardProductDto findById(
            @Parameter(description = "card product id", required = true) @PathVariable UUID id
    );

    @Operation(
            summary = "Get all card products",
            description = "get card products information")
    @GetMapping("/")
    Page<CardProductDto> findAll(@ParameterObject @PageableDefault Pageable pageable);

    @Operation(
            summary = "Create card product",
            description = "create card product by params in dto object")
    @PostMapping("/")
    CardProductDto create(
            @Parameter(description = "card product", required = true) @RequestBody CardProductCreateDto cardProductCreateDto
    );


    @Operation(
            summary = "Update card product",
            description = "update card by param in dto object")
    @PutMapping("/")
    CardProductDto update(
            @Parameter(description = "card product id", required = true) @RequestBody @Validated CardProductDto cardProductDto
    );

    @Operation(
            summary = "Delete card product",
            description = "delete card product by id")
    @DeleteMapping("/{id}")
    void deleteById(
            @Parameter(description = "card product id", required = true) @PathVariable UUID id
    );
}
