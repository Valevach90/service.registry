package com.andersen.banking.meeting_api.dto.deposit;

import static com.andersen.banking.meeting_api.utils.OpenApiConstants.*;

import com.andersen.banking.meeting_api.dto.LinkedCardDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Dto for deposit creation request.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for deposit creation request")
public class DepositCreateRequestDto {

    @Schema(description = DESCRIPTION_DEPOSIT_PRODUCT_ID, example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    @JsonProperty("depositProductId")
    @NotNull(message = "Deposit product id can't be null.")
    private UUID depositProductId;

    @Schema(description = DESCRIPTION_DEPOSIT_TYPE_ID, example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    @JsonProperty("typeId")
    @NotNull(message = "Deposit type id can't be null.")
    private UUID typeId;

    @Schema(description = DESCRIPTION_CURRENCY_ID, example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    @JsonProperty("currencyId")
    @NotNull(message = "Currency can't be null.")
    private UUID currencyId;

    @Schema(description = DESCRIPTION_TERM_MONTHS, example = EXAMPLE_INTEGER, defaultValue = EXAMPLE_INTEGER)
    @JsonProperty("termMonths")
    @NotNull(message = "Term in Months can't be null.")
    private Integer termMonths;

    @Schema(description = DESCRIPTION_AMOUNT, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("amount")
    @NotNull(message = "Amount can't be null.")
    private Long amount;

    @Schema(description = DESCRIPTION_INTEREST_RATE, example = EXAMPLE_INTEGER, defaultValue = EXAMPLE_INTEGER)
    @JsonProperty("interestRate")
    @NotNull(message = "Interest Rate can't be null.")
    private Integer interestRate;

    @Schema(description = DESCRIPTION_FIXED_INTEREST, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("fixedInterest")
    @NotNull(message = "Fixed Interest Option can't be null.")
    private Boolean fixedInterest;

    @Schema(description = DESCRIPTION_SUBSEQUENT_REPLENISHMENT, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("subsequentReplenishment")
    @NotNull(message = "Subsequent Replenishment Option can't be null.")
    private Boolean subsequentReplenishment;

    @Schema(description = DESCRIPTION_EARLY_WITHDRAWAL, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("earlyWithdrawal")
    @NotNull(message = "Early Withdrawal Option can't be null.")
    private Boolean earlyWithdrawal;

    @Schema(description = DESCRIPTION_INTEREST_WITHDRAWAL, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("interestWithdrawal")
    @NotNull(message = "Interest Withdrawal Option can't be null.")
    private Boolean interestWithdrawal;

    @Schema(description = DESCRIPTION_CAPITALIZATION, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("capitalization")
    @NotNull(message = "Capitalization Option can't be null.")
    private Boolean capitalization;

    @Schema(description = DESCRIPTION_IS_REVOCABLE, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("isRevocable")
    @NotNull(message = "Revocation Option can't be null.")
    private Boolean isRevocable;

    @Schema(description = DESCRIPTION_USER_ID, example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    @JsonProperty("userId")
    @NotNull(message = "User id can't be null.")
    private UUID userId;

    @JsonProperty("linkedCards")
    private List<LinkedCardDto> linkedCards;
}
