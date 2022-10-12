package com.andersen.banking.meeting_api.dto.deposit_product;

import static com.andersen.banking.meeting_api.utils.OpenApiConstants.*;

import com.andersen.banking.meeting_api.dto.DepositProductDescriptionDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Dto for deposit product.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for deposit product")
public class DepositProductRequestDto {

    @Schema(description = DESCRIPTION_DEPOSIT_PRODUCT_ID, example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    @JsonProperty("id")
    @NotNull(message = "Deposit product id can't be null.")
    private UUID id;

    @Schema(description = DESCRIPTION_DEPOSIT_NAME, example = EXAMPLE_DEPOSIT_NAME, defaultValue = EXAMPLE_DEPOSIT_NAME)
    @JsonProperty("depositName")
    @NotNull(message = "Deposit name can't be null.")
    private String depositName;

    @JsonProperty("type_id")
    @NotNull(message = "Deposit type can't be null.")
    private UUID typeId;

    @JsonProperty("currency_id")
    @NotNull(message = "Currency can't be null.")
    private UUID currencyId;

    @Schema(description = DESCRIPTION_MIN_TERM_MONTHS, example = EXAMPLE_INTEGER, defaultValue = EXAMPLE_INTEGER)
    @JsonProperty("minTermMonths")
    @NotNull(message = "Minimal Term in Months can't be null.")
    private Integer minTermMonths;

    @Schema(description = DESCRIPTION_MAX_TERM_MONTHS, example = EXAMPLE_INTEGER, defaultValue = EXAMPLE_INTEGER)
    @JsonProperty("maxTermMonths")
    @NotNull(message = "Maximal Term in Months can't be null.")
    private Integer maxTermMonths;

    @Schema(description = DESCRIPTION_MIN_AMOUNT, example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    @JsonProperty("minAmount")
    @NotNull(message = "Minimal Amount can't be null.")
    private Long minAmount;

    @Schema(description = DESCRIPTION_MAX_AMOUNT, example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    @JsonProperty("maxAmount")
    @NotNull(message = "Maximal Amount can't be null.")
    private Long maxAmount;

    @Schema(description = DESCRIPTION_MIN_INTEREST_RATE, example = EXAMPLE_INTEGER, defaultValue = EXAMPLE_INTEGER)
    @JsonProperty("minInterestRate")
    @NotNull(message = "Minimal Interest Rate can't be null.")
    private Integer minInterestRate;

    @Schema(description = DESCRIPTION_MAX_INTEREST_RATE, example = EXAMPLE_INTEGER, defaultValue = EXAMPLE_INTEGER)
    @JsonProperty("maxInterestRate")
    @NotNull(message = "Maximal Interest Rate can't be null.")
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
    @NotNull(message = "Customizable option can't be null.")
    private Boolean isCustomizable;

    @Schema(description = DESCRIPTION_IS_ACTIVE, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("isActive")
    @NotNull(message = "Active option can't be null.")
    private Boolean isActive;

    @JsonProperty("descriptions")
    private List<DepositProductDescriptionDto> descriptions;
}
