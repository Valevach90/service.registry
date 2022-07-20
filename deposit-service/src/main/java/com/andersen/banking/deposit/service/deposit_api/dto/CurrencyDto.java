package com.andersen.banking.deposit.service.deposit_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

import static com.andersen.banking.deposit.service.deposit_api.utils.OpenApiConstants.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for currency")
public class CurrencyDto {

    @Schema(description = DESCRIPTION_CURRENCY_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    @NotNull(message = "Currency id can't be null.")
    private Long id;

    @Schema(description = DESCRIPTION_CURRENCY_NAME, example = EXAMPLE_CURRENCY_NAME, defaultValue = EXAMPLE_CURRENCY_NAME)
    @JsonProperty("name")
    @NotNull(message = "Currency name can't be null.")
    private String name;
}
