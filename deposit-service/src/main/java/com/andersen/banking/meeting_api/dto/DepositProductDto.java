package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.utils.OpenApiConstants.*;

import com.andersen.banking.meeting_api.utils.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Dto for deposit product.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for deposit product")
public class DepositProductDto {

    @Schema(description = OpenApiConstants.DESCRIPTION_DEPOSIT_PRODUCT_ID, example = OpenApiConstants.EXAMPLE_UUID, defaultValue = OpenApiConstants.EXAMPLE_UUID)
    @JsonProperty("id")
    @NotNull(message = "Deposit product id can't be null.")
    private UUID id;

    @Schema(description = OpenApiConstants.DESCRIPTION_DEPOSIT_NAME, example = OpenApiConstants.EXAMPLE_DEPOSIT_NAME, defaultValue = OpenApiConstants.EXAMPLE_DEPOSIT_NAME)
    @JsonProperty("depositName")
    @NotNull(message = "Deposit name can't be null.")
    private String depositName;

    @JsonProperty("type")
    @NotNull(message = "Deposit type can't be null.")
    private DepositTypeDto type;

    @JsonProperty("currency")
    @NotNull(message = "Currency can't be null.")
    private CurrencyDto currency;

    @Schema(description = OpenApiConstants.DESCRIPTION_MIN_TERM_MONTHS, example = OpenApiConstants.EXAMPLE_INTEGER, defaultValue = OpenApiConstants.EXAMPLE_INTEGER)
    @JsonProperty("minTermMonths")
    @NotNull(message = "Minimal Term in Months can't be null.")
    private Integer minTermMonths;

    @Schema(description = OpenApiConstants.DESCRIPTION_MAX_TERM_MONTHS, example = OpenApiConstants.EXAMPLE_INTEGER, defaultValue = OpenApiConstants.EXAMPLE_INTEGER)
    @JsonProperty("maxTermMonths")
    @NotNull(message = "Maximal Term in Months can't be null.")
    private Integer maxTermMonths;

    @Schema(description = OpenApiConstants.DESCRIPTION_MIN_AMOUNT, example = OpenApiConstants.EXAMPLE_UUID, defaultValue = OpenApiConstants.EXAMPLE_UUID)
    @JsonProperty("minAmount")
    @NotNull(message = "Minimal Amount can't be null.")
    private Long minAmount;

    @Schema(description = OpenApiConstants.DESCRIPTION_MAX_AMOUNT, example = OpenApiConstants.EXAMPLE_UUID, defaultValue = OpenApiConstants.EXAMPLE_UUID)
    @JsonProperty("maxAmount")
    @NotNull(message = "Maximal Amount can't be null.")
    private Long maxAmount;

    @Schema(description = OpenApiConstants.DESCRIPTION_MIN_INTEREST_RATE, example = OpenApiConstants.EXAMPLE_INTEGER, defaultValue = OpenApiConstants.EXAMPLE_INTEGER)
    @JsonProperty("minInterestRate")
    @NotNull(message = "Minimal Interest Rate can't be null.")
    private Integer minInterestRate;

    @Schema(description = OpenApiConstants.DESCRIPTION_MAX_INTEREST_RATE, example = OpenApiConstants.EXAMPLE_INTEGER, defaultValue = OpenApiConstants.EXAMPLE_INTEGER)
    @JsonProperty("maxInterestRate")
    @NotNull(message = "Maximal Interest Rate can't be null.")
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
    @NotNull(message = "Customizable option can't be null.")
    private Boolean isCustomizable;

    @Schema(description = OpenApiConstants.DESCRIPTION_IS_ACTIVE, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("isActive")
    @NotNull(message = "Active option can't be null.")
    private Boolean isActive;

    @Schema(description = DESCRIPTION_DEPOSIT_PRODUCT, example = EXAMPLE_PRODUCT_DESCRIPTION, defaultValue = EXAMPLE_PRODUCT_DESCRIPTION)
    @JsonProperty("shortDescription")
    private String shortDescription;

    @Schema(description = DESCRIPTION_DEPOSIT_PRODUCT, example = EXAMPLE_PRODUCT_DESCRIPTION, defaultValue = EXAMPLE_PRODUCT_DESCRIPTION)
    @JsonProperty("fullDescription")
    private String fullDescription;
}
