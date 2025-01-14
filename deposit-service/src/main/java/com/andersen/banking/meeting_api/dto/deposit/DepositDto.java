package com.andersen.banking.meeting_api.dto.deposit;

import com.andersen.banking.meeting_api.dto.CurrencyDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductResponseDto;
import com.andersen.banking.meeting_api.dto.DepositTypeDto;
import com.andersen.banking.meeting_api.dto.LinkedCardDto;
import com.andersen.banking.meeting_api.utils.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

/**
 * Dto for deposit.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for deposit")
public class DepositDto {

    @Schema(description = OpenApiConstants.DESCRIPTION_DEPOSIT_ID, example = OpenApiConstants.EXAMPLE_UUID, defaultValue = OpenApiConstants.EXAMPLE_UUID)
    @JsonProperty("id")
    @NotNull(message = "Deposit id can't be null.")
    private UUID id;

    @Schema(description = OpenApiConstants.DESCRIPTION_DEPOSIT_NUMBER, example = OpenApiConstants.EXAMPLE_STRING_NUMBER, defaultValue = OpenApiConstants.EXAMPLE_STRING_NUMBER)
    @JsonProperty("depositNumber")
    @NotNull(message = "Deposit Number can't be null.")
    private String depositNumber;

    @JsonProperty("depositProduct")
    @NotNull(message = "Deposit product can't be null.")
    private DepositProductResponseDto depositProduct;

    @JsonProperty("type")
    @NotNull(message = "Deposit type can't be null.")
    private DepositTypeDto type;

    @JsonProperty("currency")
    @NotNull(message = "Currency can't be null.")
    private CurrencyDto currency;

    @Schema(description = OpenApiConstants.DESCRIPTION_TERM_MONTHS, example = OpenApiConstants.EXAMPLE_INTEGER, defaultValue = OpenApiConstants.EXAMPLE_INTEGER)
    @JsonProperty("termMonths")
    @NotNull(message = "Term in Months can't be null.")
    private Integer termMonths;

    @Schema(description = OpenApiConstants.DESCRIPTION_OPEN_DATE, example = OpenApiConstants.EXAMPLE_DATE, defaultValue = OpenApiConstants.EXAMPLE_DATE)
    @JsonProperty("openDate")
    @NotNull(message = "Open date can't be null.")
    private Date openDate;

    @Schema(description = OpenApiConstants.DESCRIPTION_CLOSE_DATE, example = OpenApiConstants.EXAMPLE_DATE, defaultValue = OpenApiConstants.EXAMPLE_DATE)
    @JsonProperty("closeDate")
    private Date closeDate;

    @Schema(description = OpenApiConstants.DESCRIPTION_AMOUNT, example = OpenApiConstants.EXAMPLE_UUID, defaultValue = OpenApiConstants.EXAMPLE_UUID)
    @JsonProperty("amount")
    @NotNull(message = "Amount can't be null.")
    private Long amount;

    @Schema(description = OpenApiConstants.DESCRIPTION_INTEREST_RATE, example = OpenApiConstants.EXAMPLE_INTEGER, defaultValue = OpenApiConstants.EXAMPLE_INTEGER)
    @JsonProperty("interestRate")
    @NotNull(message = "Interest Rate can't be null.")
    private Integer interestRate;

    @Schema(description = OpenApiConstants.DESCRIPTION_FIXED_INTEREST, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("fixedInterest")
    @NotNull(message = "Fixed Interest Option can't be null.")
    private Boolean fixedInterest;

    @Schema(description = OpenApiConstants.DESCRIPTION_SUBSEQUENT_REPLENISHMENT, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("subsequentReplenishment")
    @NotNull(message = "Subsequent Replenishment Option can't be null.")
    private Boolean subsequentReplenishment;

    @Schema(description = OpenApiConstants.DESCRIPTION_EARLY_WITHDRAWAL, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("earlyWithdrawal")
    @NotNull(message = "Early Withdrawal Option can't be null.")
    private Boolean earlyWithdrawal;

    @Schema(description = OpenApiConstants.DESCRIPTION_INTEREST_WITHDRAWAL, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("interestWithdrawal")
    @NotNull(message = "Interest Withdrawal Option can't be null.")
    private Boolean interestWithdrawal;

    @Schema(description = OpenApiConstants.DESCRIPTION_CAPITALIZATION, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("capitalization")
    @NotNull(message = "Capitalization Option can't be null.")
    private Boolean capitalization;

    @Schema(description = OpenApiConstants.DESCRIPTION_IS_REVOCABLE, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("isRevocable")
    @NotNull(message = "Revocation Option can't be null.")
    private Boolean isRevocable;

    @Schema(description = OpenApiConstants.DESCRIPTION_IS_REVOCABLE, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("isActive_status")
    @NotNull(message = "Revocation Option can't be null.")
    private Boolean isActive;

    @Schema(description = OpenApiConstants.DESCRIPTION_USER_ID, example = OpenApiConstants.EXAMPLE_UUID, defaultValue = OpenApiConstants.EXAMPLE_UUID)
    @JsonProperty("userId")
    @NotNull(message = "User id can't be null.")
    private UUID userId;

    @JsonProperty("linkedCards")
    private List<LinkedCardDto> linkedCards;
}
