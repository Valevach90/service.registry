package com.andersen.banking.meeting_api.controller;


import com.andersen.banking.meeting_api.dto.CardCredResponseDto;
import com.andersen.banking.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.meeting_api.dto.CardResponseDto;
import com.andersen.banking.meeting_api.dto.CardUpdateDto;
import com.andersen.banking.meeting_api.dto.TypeCardResponseDto;
import com.andersen.banking.meeting_api.dto.TypeCardUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Interface that presents basic endpoints for working with Card entity.
 */
@Tag(name = "Card controllers", description = "Endpoints to work with Card entity.")
@RequestMapping(value = "/api/v1/cards")
@RestController
public interface CardController {

    @Operation(summary = "Get card by card id", description = "get card information by id")
    @GetMapping("/{id}")
    CardResponseDto findById(
            @Parameter(description = "card id", required = true) @PathVariable UUID id);

    @Operation(summary = "Get all cards", description = "get page cards information")
    @GetMapping("/")
    Page<CardResponseDto> findAll(@ParameterObject @PageableDefault(
            sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable);

    @Operation(
            summary = "Get all cards by account_id ",
            description = "get page cards by account_id")
    @GetMapping("/accounts/{id}")
    Page<CardResponseDto> findAllByAccountId(
            @Parameter(description = "account id", required = true) @PathVariable UUID id,
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

    @Operation(summary = "Deactivate card", description = "deactivate card by id")
    @DeleteMapping("/{id}")
    CardResponseDto deactivateById(
            @Parameter(description = "card id", required = true) @PathVariable UUID id);

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

    @Operation(
            summary = "Get type card by card id",
            description = "get type card information by id")
    @GetMapping("/types/{id}")
    TypeCardResponseDto findTypeCardById(
            @Parameter(description = "card id", required = true) @PathVariable UUID id);

    @Operation(
            summary = "Update type card",
            description = "update type card by params in dto object")
    @PutMapping("/types/{id}")
    TypeCardResponseDto updateTypeCard(
            @Parameter(description = "card id", required = true) @RequestBody @Validated
            TypeCardUpdateDto typeCardUpdateDto);

    @Operation(
            summary = "Get all cards of owner",
            description = "get all cards of current user/owner with id")
    @GetMapping("/owner/{id}")
    Page<CardResponseDto> findAllByOwner(
            @Parameter(description = "owner id", required = true) @PathVariable UUID id,
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
            @Parameter(description = "owner id", required = true) @PathVariable UUID ownerId,
            @Parameter(description = "already chosen card id", required = true) @PathVariable
            UUID cardId,
            @ParameterObject
            @PageableDefault(
                    sort = {"id"},
                    direction = Sort.Direction.DESC)
            Pageable pageable);

    @Operation(
            summary = "Get all cards of current user",
            description = "get all cards of current user with id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/user")
    Page<CardResponseDto> findAllByCurrentUser(Authentication authentication,
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
            @Parameter(description = "already chosen card id", required = true) @PathVariable
            UUID cardId,
            @ParameterObject
            @PageableDefault(
                    sort = {"id"},
                    direction = Sort.Direction.DESC)
            Pageable pageable);

    @GetMapping("/numbers")
    CardCredResponseDto findCardByCardNumber(
            @RequestParam(name = "first_twelve") String twelveNums,
            @RequestParam(name = "last_four") String fourNums);
}
