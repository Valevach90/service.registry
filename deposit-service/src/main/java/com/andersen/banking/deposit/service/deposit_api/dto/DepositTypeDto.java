package com.andersen.banking.deposit.service.deposit_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

import static com.andersen.banking.deposit.service.deposit_api.utils.OpenApiConstants.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for deposit type")
public class DepositTypeDto {

    @Schema(description = DESCRIPTION_DEPOSIT_TYPE_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    @NotNull(message = "Deposit type id can't be null.")
    private Long id;

    @Schema(description = DESCRIPTION_DEPOSIT_TYPE_NAME, example = EXAMPLE_DEPOSIT_TYPE_NAME, defaultValue = EXAMPLE_DEPOSIT_TYPE_NAME)
    @JsonProperty("name")
    @NotNull(message = "Deposit type name can't be null.")
    private String name;
}
