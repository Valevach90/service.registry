package com.andersen.banking.meeting_api.dto.deposit;

import com.andersen.banking.meeting_api.dto.LinkedCardDto;
import com.andersen.banking.meeting_api.utils.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for request")
public class DepositRequestDto {

    @Schema(description = OpenApiConstants.DESCRIPTION_DEPOSIT_ID, example = OpenApiConstants.EXAMPLE_UUID, defaultValue = OpenApiConstants.EXAMPLE_UUID)
    @JsonProperty("id")
    @NotNull(message = "Deposit id")
    private UUID id;

    @Schema(description = OpenApiConstants.DESCRIPTION_DEPOSIT_NUMBER, example = OpenApiConstants.EXAMPLE_STRING_NUMBER, defaultValue = OpenApiConstants.EXAMPLE_STRING_NUMBER)
    @JsonProperty("depositNumber")
    @NotNull(message = "Deposit Number")
    private String depositNumber;

    @JsonProperty("depositProduct")
    @NotNull(message = "Deposit product")
    private UUID productId;

    @JsonProperty("type")
    @NotNull(message = "Deposit type")
    private UUID typeId;

    @JsonProperty("currency")
    @NotNull(message = "Currency can't be null.")
    private UUID currencyId;

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
    @NotNull(message = "Amount")
    private Long amount;

    @Schema(description = OpenApiConstants.DESCRIPTION_INTEREST_RATE, example = OpenApiConstants.EXAMPLE_INTEGER, defaultValue = OpenApiConstants.EXAMPLE_INTEGER)
    @JsonProperty("interestRate")
    @NotNull(message = "Interest Rate")
    private Integer interestRate;

    @Schema(description = OpenApiConstants.DESCRIPTION_FIXED_INTEREST, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("fixedInterest")
    @NotNull(message = "Fixed Interest Option")
    private Boolean fixedInterest;

    @Schema(description = OpenApiConstants.DESCRIPTION_SUBSEQUENT_REPLENISHMENT, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("subsequentReplenishment")
    @NotNull(message = "Subsequent Replenishment Option")
    private Boolean subsequentReplenishment;

    @Schema(description = OpenApiConstants.DESCRIPTION_EARLY_WITHDRAWAL, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("earlyWithdrawal")
    @NotNull(message = "Early Withdrawal Option")
    private Boolean earlyWithdrawal;

    @Schema(description = OpenApiConstants.DESCRIPTION_INTEREST_WITHDRAWAL, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("interestWithdrawal")
    @NotNull(message = "Interest Withdrawal Option")
    private Boolean interestWithdrawal;

    @Schema(description = OpenApiConstants.DESCRIPTION_CAPITALIZATION, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("capitalization")
    @NotNull(message = "Capitalization Option")
    private Boolean capitalization;

    @Schema(description = OpenApiConstants.DESCRIPTION_IS_REVOCABLE, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("isRevocable")
    @NotNull(message = "Revocation Option")
    private Boolean isRevocable;

    @Schema(description = OpenApiConstants.DESCRIPTION_USER_ID, example = OpenApiConstants.EXAMPLE_UUID, defaultValue = OpenApiConstants.EXAMPLE_UUID)
    @JsonProperty("userId")
    @NotNull(message = "User id")
    private UUID userId;

    @Schema(description = OpenApiConstants.DESCRIPTION_CAPITALIZATION)
    @JsonProperty("Deposit status")
    private Boolean isActive;

    @JsonProperty("linkedCards")
    private List<LinkedCardDto> linkedCards;
}
