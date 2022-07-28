package com.andersen.banking.deposit_api.dto;

import com.andersen.banking.deposit_api.utils.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

import java.sql.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for transfer")
public class TransferDto {

    @Schema(description = OpenApiConstants.DESCRIPTION_TRANSFER_ID, example = OpenApiConstants.EXAMPLE_LONG, defaultValue = OpenApiConstants.EXAMPLE_LONG)
    @JsonProperty("id")
    @NotNull(message = "Transfer id can't be null.")
    private Long id;

    @Schema(description = OpenApiConstants.DESCRIPTION_DEPOSIT_ID, example = OpenApiConstants.EXAMPLE_LONG, defaultValue = OpenApiConstants.EXAMPLE_LONG)
    @JsonProperty("deposit")
    private DepositDto deposit;

    @Schema(description = OpenApiConstants.DESCRIPTION_FROM_CARD_NUMBER, example = OpenApiConstants.EXAMPLE_STRING_NUMBER, defaultValue = OpenApiConstants.EXAMPLE_STRING_NUMBER)
    @JsonProperty("fromCardNumber")
    @NotNull(message = "Replenishment Card Number can't be null.")
    private String fromCardNumber;

    @Schema(description = OpenApiConstants.DESCRIPTION_TO_CARD_NUMBER, example = OpenApiConstants.EXAMPLE_STRING_NUMBER, defaultValue = OpenApiConstants.EXAMPLE_STRING_NUMBER)
    @JsonProperty("toCardNumber")
    @NotNull(message = "Withdrawal Card Number can't be null.")
    private String toCardNumber;

    @Schema(description = OpenApiConstants.DESCRIPTION_AMOUNT, example = OpenApiConstants.EXAMPLE_LONG, defaultValue = OpenApiConstants.EXAMPLE_LONG)
    @JsonProperty("amount")
    @NotNull(message = "Amount can't be null.")
    private Long amount;

    @Schema(description = OpenApiConstants.DESCRIPTION_DATE, example = OpenApiConstants.EXAMPLE_DATE, defaultValue = OpenApiConstants.EXAMPLE_DATE)
    @JsonProperty("date")
    @NotNull(message = "Date can't be null.")
    private Date date;

    @Schema(description = OpenApiConstants.DESCRIPTION_STATUS_OF_TRANSFER, example = OpenApiConstants.EXAMPLE_BOOLEAN, defaultValue = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("successStatus")
    @NotNull(message = "Success status can't be null.")
    private Boolean successStatus;
}
