package com.andersen.banking.deposit.service.deposit_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

import java.sql.Date;

import static com.andersen.banking.deposit.service.deposit_api.utils.OpenApiConstants.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for transfer")
public class TransferDto {

    @Schema(description = DESCRIPTION_TRANSFER_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    @NotNull(message = "Transfer id can't be null.")
    private Long id;

    @Schema(description = DESCRIPTION_DEPOSIT_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("depositId")
    private Long depositId;

    @Schema(description = DESCRIPTION_FROM_CARD_NUMBER, example = EXAMPLE_STRING_NUMBER, defaultValue = EXAMPLE_STRING_NUMBER)
    @JsonProperty("fromCardNumber")
    @NotNull(message = "Replenishment Card Number can't be null.")
    private String fromCardNumber;

    @Schema(description = DESCRIPTION_TO_CARD_NUMBER, example = EXAMPLE_STRING_NUMBER, defaultValue = EXAMPLE_STRING_NUMBER)
    @JsonProperty("toCardNumber")
    @NotNull(message = "Withdrawal Card Number can't be null.")
    private String toCardNumber;

    @Schema(description = DESCRIPTION_AMOUNT, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("amount")
    @NotNull(message = "Amount can't be null.")
    private Long amount;

    @Schema(description = DESCRIPTION_DATE, example = EXAMPLE_DATE, defaultValue = EXAMPLE_DATE)
    @JsonProperty("date")
    @NotNull(message = "Date can't be null.")
    private Date date;

    @Schema(description = DESCRIPTION_STATUS_OF_TRANSFER, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("successStatus")
    @NotNull(message = "Success status can't be null.")
    private Boolean successStatus;
}
