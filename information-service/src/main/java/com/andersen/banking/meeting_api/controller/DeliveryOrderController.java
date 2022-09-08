package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.DeliveryOrderCreateRequestDto;
import com.andersen.banking.meeting_api.dto.DeliveryOrderDto;
import com.andersen.banking.meeting_api.dto.DeliveryTypeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for work with card delivery orders.
 */

@Tag(name = "Delivery order controller", description = "work with card delivery orders")
@RequestMapping(value = "/api/v1/delivery/orders")
public interface DeliveryOrderController {

    @Operation(summary = "Create delivery order",
            description = "create delivery order by params in dto object")
    @PostMapping
    DeliveryOrderDto create(
            @RequestBody
            @Validated DeliveryOrderCreateRequestDto deliveryOrderDto
    );

    @Operation(summary = "Get delivery order",
            description = "get delivery order by id"
    )
    @GetMapping("/{id}")
    DeliveryOrderDto findById(
            @Parameter(description = "delivery order id", required = true)
            @PathVariable("id") UUID id
    );

    @Operation(summary = "Get all delivery orders",
            description = "Get all delivery orders"
    )
    @GetMapping
    Page<DeliveryOrderDto> findAll(
            @ParameterObject
            @PageableDefault Pageable pageable
    );

    @Operation(summary = "Get all delivery types",
            description = "Get all delivery types"
    )
    @GetMapping
    Page<DeliveryTypeDto> findAllDeliveryTypes(
            @ParameterObject
            @PageableDefault Pageable pageable
    );

    @Operation(summary = "Get all delivery orders for user",
            description = "Get all delivery orders by user id"
    )
    @GetMapping("/users/{id}")
    Page<DeliveryOrderDto> findDeliveryOrderByUserId(
            @Parameter(description = "user id", required = true) @PathVariable("id") UUID userId,
            @ParameterObject @PageableDefault Pageable pageable
    );

    @Operation(summary = "Get delivery order for card",
            description = "Get delivery order by card id"
    )
    @GetMapping("/cards/{id}")
    DeliveryOrderDto findDeliveryOrderByCardId(
            @Parameter(description = "card id", required = true)
            @PathVariable("id") UUID cardId
    );

    @Operation(summary = "Update delivery order",
            description = "update delivery order by params in dto object")
    @PutMapping
    void update(
            @RequestBody
            @Validated DeliveryOrderDto deliveryOrderDto
    );
}
