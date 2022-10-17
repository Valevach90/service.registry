package com.andersen.banking.meeting_api.dto.deposit_product;

import static com.andersen.banking.meeting_api.utils.OpenApiConstants.*;
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

    @Schema(description = DESCRIPTION_DEPOSIT_TYPE, example = EXAMPLE_DEPOSIT_TYPE, defaultValue = EXAMPLE_DEPOSIT_TYPE)
    @JsonProperty("type")
    private List<String> type;

    @Schema(description = DESCRIPTION_CURRENCY, example = EXAMPLE_CURRENCY_NAME, defaultValue = EXAMPLE_CURRENCY_NAME)
    @JsonProperty("currency")
    private List<String> currency;

    @Schema(description = DESCRIPTION_MIN_TERM_MONTHS, example = EXAMPLE_INTEGER, defaultValue = EXAMPLE_INTEGER)
    @JsonProperty("minTermMonths")
    private Integer minTermMonths;

    @Schema(description = DESCRIPTION_MAX_TERM_MONTHS, example = EXAMPLE_INTEGER, defaultValue = EXAMPLE_INTEGER)
    @JsonProperty("maxTermMonths")
    private Integer maxTermMonths;

    @Schema(description = DESCRIPTION_MIN_AMOUNT, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("minAmount")
    private Long minAmount;

    @Schema(description = DESCRIPTION_MAX_AMOUNT, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("maxAmount")
    private Long maxAmount;

    @Schema(description = DESCRIPTION_MIN_INTEREST_RATE, example = EXAMPLE_INTEGER, defaultValue = EXAMPLE_INTEGER)
    @JsonProperty("minInterestRate")
    private Integer minInterestRate;

    @Schema(description = DESCRIPTION_MAX_INTEREST_RATE, example = EXAMPLE_INTEGER, defaultValue = EXAMPLE_INTEGER)
    @JsonProperty("maxInterestRate")
    private Integer maxInterestRate;

    @Schema(description = DESCRIPTION_FIXED_INTEREST, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("fixedInterest")
    private Boolean fixedInterest;

    @Schema(description = DESCRIPTION_SUBSEQUENT_REPLENISHMENT, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("subsequentReplenishment")
    private Boolean subsequentReplenishment;

    @Schema(description = DESCRIPTION_EARLY_WITHDRAWAL, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("earlyWithdrawal")
    private Boolean earlyWithdrawal;

    @Schema(description = DESCRIPTION_INTEREST_WITHDRAWAL, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("interestWithdrawal")
    private Boolean interestWithdrawal;

    @Schema(description = DESCRIPTION_CAPITALIZATION, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("capitalization")
    private Boolean capitalization;

    @Schema(description = DESCRIPTION_IS_REVOCABLE, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("isRevocable")
    private Boolean isRevocable;

    @Schema(description = DESCRIPTION_IS_CUSTOMIZABLE, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("isCustomizable")
    private Boolean isCustomizable;
}

