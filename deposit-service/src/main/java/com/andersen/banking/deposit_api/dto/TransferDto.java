package com.andersen.banking.deposit_api.dto;

import static com.andersen.banking.deposit_api.utils.OpenApiConstants.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for transfer")
public class TransferDto {

    @Schema(description = DESCRIPTION_TRANSFER_ID, example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    @JsonProperty("transferId")
    @NotNull(message = "Transfer id can't be null.")
    private UUID transferId;

    @Schema(description = DESCRIPTION_USER_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("userId")
    @NotNull(message = "User id can't be null.")
    private UUID userId;

    @Schema(description = DESCRIPTION_DEPOSIT_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("deposit")
    private DepositDto deposit;

    @Schema(description = DESCRIPTION_SOURCE_NUMBER, example = EXAMPLE_STRING_NUMBER, defaultValue = EXAMPLE_STRING_NUMBER)
    @JsonProperty("sourceNumber")
    @NotNull(message = "Source Number can't be null.")
    private String sourceNumber;

    @Schema(description = DESCRIPTION_SOURCE_TYPE, example = EXAMPLE_STRING_NUMBER, defaultValue = EXAMPLE_STRING_NUMBER)
    @JsonProperty("sourceType")
    @NotNull(message = "Type of source for Replenishment.")
    private String sourceType;

    @Schema(description = DESCRIPTION_DESTINATION_NUMBER, example = EXAMPLE_STRING_NUMBER, defaultValue = EXAMPLE_STRING_NUMBER)
    @JsonProperty("destinationNumber")
    @NotNull(message = "Destination Number can't be null.")
    private String destinationNumber;

    @Schema(description = DESCRIPTION_DESTINATION_TYPE, example = EXAMPLE_STRING_NUMBER, defaultValue = EXAMPLE_STRING_NUMBER)
    @JsonProperty("destinationType")
    @NotNull(message = "Type of destination for Withdrawal.")
    private String destinationType;

    @Schema(description = DESCRIPTION_AMOUNT, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("amount")
    @NotNull(message = "Amount can't be null.")
    private Long amount;

    @Schema(description = DESCRIPTION_CURRENCY_NAME, example = EXAMPLE_CURRENCY_NAME, defaultValue = EXAMPLE_CURRENCY_NAME)
    @JsonProperty("currencyName")
    @NotNull(message = "Currency name can't be null.")
    private String currencyName;

    @Schema(description = DESCRIPTION_TIME, example = EXAMPLE_TIME, defaultValue = EXAMPLE_TIME)
    @JsonProperty("time")
    @NotNull(message = "Time can't be null.")
    private Timestamp time;

    @Schema(description = DESCRIPTION_RESULT_OF_TRANSFER, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN)
    @JsonProperty("result")
    @NotNull(message = "Result of transfer can't be null.")
    private Boolean result;

    @Schema(description = DESCRIPTION_STATUS_OF_TRANSFER, example = EXAMPLE_TRANSFER_STATUS_DESCRIPTION, defaultValue = EXAMPLE_TRANSFER_STATUS_DESCRIPTION)
    @JsonProperty("statusDescription")
    @NotNull(message = "Description of transfer status can't be null.")
    private String statusDescription;
}
