package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.utility.OpenApiConstants.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRatesResponseDto {

    @JsonProperty("source_currency")
    @Schema(example = EXAMPLE_CURRENCY_SELL, defaultValue = EXAMPLE_CURRENCY_SELL)
    private String sourceCurrency;

    @JsonProperty("destination_currency")
    @Schema(example = EXAMPLE_CURRENCY_BUY, defaultValue = EXAMPLE_CURRENCY_BUY)
    private String destinationCurrency;

    @JsonProperty("buy_price")
    @Schema(example = EXAMPLE_DOUBLE_VALUE, defaultValue = EXAMPLE_DOUBLE_VALUE)
    private Double buyPrice;

    @JsonProperty("sell_price")
    @Schema(example = EXAMPLE_DOUBLE_VALUE, defaultValue = EXAMPLE_DOUBLE_VALUE)
    private Double sellPrice;
}
