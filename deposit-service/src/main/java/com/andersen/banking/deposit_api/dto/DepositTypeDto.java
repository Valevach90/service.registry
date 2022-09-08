package com.andersen.banking.deposit_api.dto;

import com.andersen.banking.deposit_api.utils.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for deposit type")
public class DepositTypeDto {

    @Schema(description = OpenApiConstants.DESCRIPTION_DEPOSIT_TYPE_ID, example = OpenApiConstants.EXAMPLE_LONG, defaultValue = OpenApiConstants.EXAMPLE_LONG)
    @JsonProperty("id")
    @NotNull(message = "Deposit type id can't be null.")
    private UUID id;

    @Schema(description = OpenApiConstants.DESCRIPTION_DEPOSIT_TYPE_NAME, example = OpenApiConstants.EXAMPLE_DEPOSIT_TYPE_NAME, defaultValue = OpenApiConstants.EXAMPLE_DEPOSIT_TYPE_NAME)
    @JsonProperty("name")
    @NotNull(message = "Deposit type name can't be null.")
    private String name;
}
