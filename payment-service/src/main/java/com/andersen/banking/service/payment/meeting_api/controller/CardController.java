package com.andersen.banking.service.payment.meeting_api.controller;

import com.andersen.banking.service.payment.meeting_api.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/** Interface that presents basic endpoints for working with Card entity. */
@Tag(name = "Card controllers", description = "Endpoints to work with Card entity.")
@RequestMapping(value = "/api/v1/cards")
@RestController
public interface CardController {

  @Operation(summary = "Get card by card id", description = "get card information by id")
  @GetMapping("/{id}")
  CardResponseDto findById(
      @Parameter(description = "user id", required = true) @PathVariable Long id);

  @Operation(summary = "Get all cards", description = "get page cards information")
  @GetMapping("/")
  Page<CardResponseDto> findAll(@ParameterObject @PageableDefault Pageable pageable);

  @Operation(summary = "Get all cards by account_id ", description = "get page cards by account_id")
  @GetMapping("/accounts/{id}")
  Page<CardResponseDto> findAllByAccountId(
      @Parameter(description = "account id", required = true) @PathVariable Long id,
      @ParameterObject
          @PageableDefault(
              sort = {"id"},
              direction = Sort.Direction.DESC)
          Pageable pageable);

  @Operation(summary = "Update card", description = "update card by params in dto object")
  @PutMapping("/{id}")
  CardResponseDto updateCard(
      @Parameter(description = "card id", required = true) @RequestBody @Validated
          CardUpdateDto cardUpdateDto);

  @Operation(summary = "Delete card", description = "delete card by id")
  @DeleteMapping("/{id}")
  CardResponseDto deleteById(
      @Parameter(description = "card id", required = true) @PathVariable Long id);

  @Operation(summary = "Create card", description = "create card by params in dto object")
  @PostMapping("/")
  CardResponseDto create(
      @Parameter(description = "card", required = true) @RequestBody @Validated
          CardRegistrationDto cardDto);

  @Operation(summary = "Get all cards by type_card ", description = "get page cards by type_card")
  @GetMapping("/search/")
  Page<CardResponseDto> findAllByTypeCard(
      @RequestParam(required = false) String payment,
      @RequestParam(required = false) String type,
      @ParameterObject
          @PageableDefault(
              sort = {"id"},
              direction = Sort.Direction.DESC)
          Pageable pageable);

  @Operation(summary = "Get type card by card id", description = "get type card information by id")
  @GetMapping("/types/{id}")
  TypeCardResponseDto findTypeCardById(
      @Parameter(description = "card id", required = true) @PathVariable Long id);

  @Operation(summary = "Update type card", description = "update type card by params in dto object")
  @PutMapping("/types/{id}")
  TypeCardResponseDto updateTypeCard(
      @Parameter(description = "card id", required = true) @RequestBody @Validated
          TypeCardUpdateDto typeCardUpdateDto);

  @Operation(
      summary = "Get all cards of owner",
      description = "get all cards of current user/owner with id")
  @GetMapping("/owner/{id}")
  Page<CardResponseDto> findAllByOwner(
      @Parameter(description = "owner id", required = true) @PathVariable Long id,
      @ParameterObject
          @PageableDefault(
              sort = {"id"},
              direction = Sort.Direction.DESC)
          Pageable pageable);

  @Operation(
      summary = "Get all cards of owner except already chosen card",
      description = "get page cards by owner except already chosen card")
  @GetMapping("/owner/{ownerId}/card/{cardId}")
  Page<CardResponseDto> findAllExceptChosenByOwnerId(
      @Parameter(description = "owner id", required = true) @PathVariable Long ownerId,
      @Parameter(description = "already chosen card id", required = true) @PathVariable Long cardId,
      @ParameterObject
          @PageableDefault(
              sort = {"id"},
              direction = Sort.Direction.DESC)
          Pageable pageable);

  @Operation(
      summary = "Get all cards of current user",
      description = "get all cards of current user with id")
  @GetMapping("/user")
  Page<CardResponseDto> findAllByCurrentUser(
      @ParameterObject
          @PageableDefault(
              sort = {"id"},
              direction = Sort.Direction.DESC)
          Pageable pageable);

  @Operation(
      summary = "Get all cards of current user except already chosen card",
      description = "get page cards by current user except already chosen card")
  @GetMapping("/user/{cardId}")
  Page<CardResponseDto> findAllExceptChosenByCurrentUser(
      @Parameter(description = "already chosen card id", required = true) @PathVariable Long cardId,
      @ParameterObject
          @PageableDefault(
              sort = {"id"},
              direction = Sort.Direction.DESC)
          Pageable pageable);

  @GetMapping("/numbers")
  CardCredResponseDto findCardByCardNumber(
      @RequestParam(required = true, name = "first_twelve") String twelveNums,
      @RequestParam(required = true, name = "last_four") String fourNums);
}
