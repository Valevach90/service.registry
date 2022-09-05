package com.andersen.banking.service.registry.meeting_api.controller;

import com.andersen.banking.service.registry.meeting_api.dto.PersonalDataDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for work with user personal data.
 */

@Tag(name = "Personal data controller", description = "work with users personal data")
@RequestMapping(value = "/api/v1/users/data")
@RestController
public interface PersonalDataController {

    @Operation(summary = "Get user personal data",
            description = "get user personal data"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    PersonalDataDto getUserPersonalData(
            Authentication authentication
    );

    @Operation(summary = "Update user personal data",
            description = "update user personal data by params in dto object")
    @PutMapping
    void updateUserPersonalData(
            @RequestBody
            @Validated PersonalDataDto personalDataDto);
}
