package com.andersen.banking.deposit_api.dto;

import com.andersen.banking.deposit_api.utils.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for currency")
public class CurrencyDto {

    @Schema(description = OpenApiConstants.DESCRIPTION_CURRENCY_ID, example = OpenApiConstants.EXAMPLE_LONG, defaultValue = OpenApiConstants.EXAMPLE_LONG)
    @JsonProperty("id")
    @NotNull(message = "Currency id can't be null.")
    private Long id;

    @Schema(description = OpenApiConstants.DESCRIPTION_CURRENCY_NAME, example = OpenApiConstants.EXAMPLE_CURRENCY_NAME, defaultValue = OpenApiConstants.EXAMPLE_CURRENCY_NAME)
    @JsonProperty("name")
    @NotNull(message = "Currency name can't be null.")
    private String name;
}
