package com.andersen.banking.service.registry.meeting_api.controller;

import com.andersen.banking.service.registry.meeting_api.dto.PersonalDataDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    PersonalDataDto getUserPersonalData(
            @AuthenticationPrincipal Jwt jwt
    );

    @Operation(summary = "Update user personal data",
            description = "update user personal data by params in dto object")
    @PutMapping
    void updateUserPersonalData(
            @RequestBody
            @Validated PersonalDataDto personalDataDto);
}
