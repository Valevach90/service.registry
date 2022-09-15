package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.CardResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Card controllers V2", description = "Endpoints to work with Card entity.")
@RequestMapping(value = "/api/v2/cards")
public interface CardControllerV2 {

    @Operation(
            summary = "Get all cards by user token",
            description = "Get page of cards by user token and a pageable settings")
    @GetMapping("")
    Page<CardResponseDto> findAll(
            Authentication authentication,
            @ParameterObject @PageableDefault Pageable pageable);

}
