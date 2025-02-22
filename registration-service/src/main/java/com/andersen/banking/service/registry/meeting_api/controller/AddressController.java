package com.andersen.banking.service.registry.meeting_api.controller;

import static com.andersen.banking.service.registry.meeting_impl.security.SecurityUtil.ADMIN;
import static com.andersen.banking.service.registry.meeting_impl.security.SecurityUtil.EMPLOYEE;

import com.andersen.banking.service.registry.meeting_api.dto.AddressCreateDto;
import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import javax.annotation.security.RolesAllowed;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for mapping addresses.
 */
@Tag(name = "Address controller", description = "work with addresses")
@RequestMapping(value = "/api/v1/addresses")
public interface AddressController {

    @Operation(summary = "Create address",
            description = "Create address")
    @PostMapping
    AddressDto create(@RequestBody AddressCreateDto addressDto);

    @Operation(summary = "Get all Addresses",
            description = "get all Addresses information")
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @RolesAllowed({ADMIN, EMPLOYEE})
    List<AddressDto> findAll();

    @Operation(summary = "Get address by user id",
            description = "get address information by user id")
    @GetMapping("/user/{user_id}")
    AddressDto findAddressByUserId(
            @Parameter(description = "user id", required = true)
            @PathVariable(value = "user_id") UUID userId
    );

    @Operation(summary = "Get address by id",
            description = "get address information by id")
    @GetMapping("/{id}")
    AddressDto findById(
            @Parameter(description = "address id", required = true)
            @PathVariable("id") Long id
    );

    @Operation(summary = "Update address",
            description = "update address by params in dto object")
    @PutMapping()
    void updateAddress(
            @Parameter(description = "address id", required = true)
            @RequestBody
            @Validated AddressDto addressDto
    );
}
