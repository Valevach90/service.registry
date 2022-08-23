package com.andersen.banking.deposit_api.dto;

import com.andersen.banking.deposit_api.utils.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * Dto for deposit product filter.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for deposit product filter")
public class DepositProductFilterDto {

    @Schema(description = OpenApiConstants.DESCRIPTION_DEPOSIT_TYPE, example = OpenApiConstants.EXAMPLE_DEPOSIT_TYPE, defaultValue = OpenApiConstants.EXAMPLE_DEPOSIT_TYPE)
    @JsonProperty("type")
    private List<DepositTypeDto> type;

    @Schema(description = OpenApiConstants.DESCRIPTION_CURRENCY, example = OpenApiConstants.EXAMPLE_CURRENCY_NAME, defaultValue = OpenApiConstants.EXAMPLE_CURRENCY_NAME)
    @JsonProperty("currency")
    private List<CurrencyDto> currency;

    @Schema(description = OpenApiConstants.DESCRIPTION_MIN_TERM_MONTHS, example = OpenApiConstants.EXAMPLE_INTEGER, defaultValue = OpenApiConstants.EXAMPLE_INTEGER)
    @JsonProperty("minTermMonths")
    private Integer minTermMonths;

    @Schema(description = OpenApiConstants.DESCRIPTION_MAX_TERM_MONTHS, example = OpenApiConstants.EXAMPLE_INTEGER, defaultValue = OpenApiConstants.EXAMPLE_INTEGER)
    @JsonProperty("maxTermMonths")
    private Integer maxTermMonths;

    @Schema(description = OpenApiConstants.DESCRIPTION_MIN_AMOUNT, example = OpenApiConstants.EXAMPLE_LONG, defaultValue = OpenApiConstants.EXAMPLE_LONG)
    @JsonProperty("minAmount")
    private Long minAmount;

    @Schema(description = OpenApiConstants.DESCRIPTION_MAX_AMOUNT, example = OpenApiConstants.EXAMPLE_LONG, defaultValue = OpenApiConstants.EXAMPLE_LONG)
    @JsonProperty("maxAmount")
    private Long maxAmount;

    @Schema(description = OpenApiConstants.DESCRIPTION_MIN_INTEREST_RATE, example = OpenApiConstants.EXAMPLE_INTEGER, defaultValue = OpenApiConstants.EXAMPLE_INTEGER)
    @JsonProperty("minInterestRate")
    private Integer minInterestRate;

    @Schema(description = OpenApiConstants.DESCRIPTION_MAX_INTEREST_RATE, example = OpenApiConstants.EXAMPLE_INTEGER, defaultValue = OpenApiConstants.EXAMPLE_INTEGER)
    @JsonProperty("maxInterestRate")
    private Integer maxInterestRate;

    @Schema(description = OpenApiConstants.DESCRIPTION_FIXED_INTEREST, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("fixedInterest")
    private Boolean fixedInterest;

    @Schema(description = OpenApiConstants.DESCRIPTION_SUBSEQUENT_REPLENISHMENT, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("subsequentReplenishment")
    private Boolean subsequentReplenishment;

    @Schema(description = OpenApiConstants.DESCRIPTION_EARLY_WITHDRAWAL, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("earlyWithdrawal")
    private Boolean earlyWithdrawal;

    @Schema(description = OpenApiConstants.DESCRIPTION_INTEREST_WITHDRAWAL, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("interestWithdrawal")
    private Boolean interestWithdrawal;

    @Schema(description = OpenApiConstants.DESCRIPTION_CAPITALIZATION, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("capitalization")
    private Boolean capitalization;

    @Schema(description = OpenApiConstants.DESCRIPTION_IS_REVOCABLE, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("isRevocable")
    private Boolean isRevocable;

    @Schema(description = OpenApiConstants.DESCRIPTION_IS_CUSTOMIZABLE, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("isCustomizable")
    private Boolean isCustomizable;

/*    @Schema(description = OpenApiConstants.DESCRIPTION_IS_ACTIVE, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("isActive")
    @NotNull(message = "Active option can't be null.")
    private Boolean isActive;*/
}

