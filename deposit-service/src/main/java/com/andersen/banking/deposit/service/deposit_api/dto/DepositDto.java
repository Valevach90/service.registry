package com.andersen.banking.deposit.service.deposit_api.dto;

import com.andersen.banking.deposit.service.deposit_db.entities.Currency;
import com.andersen.banking.deposit.service.deposit_db.entities.DepositType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

import static com.andersen.banking.deposit.service.deposit_api.utils.OpenApiConstants.*;

/**
 * Dto for deposit.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for deposit")
public class DepositDto {

    @Schema(description = DESCRIPTION_DEPOSIT_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    @NotNull(message = "Deposit id can't be null.")
    private Long id;

    @Schema(description = DESCRIPTION_DEPOSIT_NUMBER, example = EXAMPLE_STRING_NUMBER, defaultValue = EXAMPLE_STRING_NUMBER)
    @JsonProperty("depositNumber")
    @NotNull(message = "Deposit Number can't be null.")
    private String depositNumber;

    @Schema(description = DESCRIPTION_DEPOSIT_PRODUCT, example = EXAMPLE_DEPOSIT_NAME, defaultValue = EXAMPLE_DEPOSIT_NAME)
    @JsonProperty("depositProduct")
    @NotNull(message = "Deposit product can't be null.")
    private DepositProductDto depositProduct;

    @Schema(description = DESCRIPTION_DEPOSIT_TYPE, example = EXAMPLE_DEPOSIT_TYPE, defaultValue = EXAMPLE_DEPOSIT_TYPE)
    @JsonProperty("type")
    @NotNull(message = "Deposit type can't be null.")
    private DepositTypeDto type;

    @Schema(description = DESCRIPTION_CURRENCY, example = EXAMPLE_CURRENCY_NAME, defaultValue = EXAMPLE_CURRENCY_NAME)
    @JsonProperty("currency")
    @NotNull(message = "Currency can't be null.")
    private CurrencyDto currency;

    @Schema(description = DESCRIPTION_TERM_MONTHS, example = EXAMPLE_INTEGER, defaultValue = EXAMPLE_INTEGER)
    @JsonProperty("termMonths")
    @NotNull(message = "Term in Months can't be null.")
    private Integer termMonths;

    @Schema(description = DESCRIPTION_OPEN_DATE, example = EXAMPLE_DATE, defaultValue = EXAMPLE_DATE)
    @JsonProperty("openDate")
    @NotNull(message = "Open date can't be null.")
    private Date openDate;

    @Schema(description = DESCRIPTION_CLOSE_DATE, example = EXAMPLE_DATE, defaultValue = EXAMPLE_DATE)
    @JsonProperty("closeDate")
    private Date closeDate;

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

    @Schema(description = DESCRIPTION_FROM_CARD_NUMBER, example = EXAMPLE_STRING_NUMBER, defaultValue = EXAMPLE_STRING_NUMBER)
    @JsonProperty("replenishmentCardNumber")
    @NotNull(message = "Replenishment Card Number can't be null.")
    private String replenishmentCardNumber;

    @Schema(description = DESCRIPTION_SUBSEQUENT_REPLENISHMENT, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("subsequentReplenishment")
    @NotNull(message = "Subsequent Replenishment Option can't be null.")
    private Boolean subsequentReplenishment;

    @Schema(description = DESCRIPTION_TO_CARD_NUMBER, example = EXAMPLE_STRING_NUMBER, defaultValue = EXAMPLE_STRING_NUMBER)
    @JsonProperty("withdrawalCardNumber")
    private String withdrawalCardNumber;

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

    @Schema(description = DESCRIPTION_USER_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("userId")
    @NotNull(message = "User id can't be null.")
    private Long userId;

    @Schema(description = DESCRIPTION_TRANSFERS, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("transfersDto")
    private List<TransferDto> transfersDto;
}
