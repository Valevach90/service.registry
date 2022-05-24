package com.andersen.banking.service.payment.meeting_api.controller;

import com.andersen.banking.service.payment.meeting_api.dto.CardDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "User controller", description = "work with cards")
@RequestMapping(value = "/api/v1/")
@RestController
public interface CardController {

  @Operation(summary = "Get card by card id",
      description = "get card information by id")
  @GetMapping("/cards/{id}")
  CardDto findById(@Parameter(description = "user id", required = true)
  @PathVariable Long id);

  @Operation(summary = "Get all cards",
      description = "get all cards information")
  @GetMapping("/cards")
  Page<CardDto> findAll(
      @ParameterObject
      @PageableDefault Pageable pageable);

  @Operation(summary = "Update card",
      description = "update card by params in dto object")
  @PutMapping("/cards/{id}")
  void updateCard(
      @Parameter(description = "card id", required = true)
      @RequestBody
      @Validated CardDto cardDto);

  @Operation(summary = "Delete card",
      description = "delete card by id")
  @DeleteMapping("/cards/{id}")
  void deleteById(@Parameter(description = "card id", required = true)
  @PathVariable Long id);

  @Operation(summary = "Create card",
      description = "create card by params in dto object")
  @PostMapping("/cards")
  CardDto create(@Parameter(description = "card", required = true)
  @RequestBody
  @Validated CardDto cardDto);
}
