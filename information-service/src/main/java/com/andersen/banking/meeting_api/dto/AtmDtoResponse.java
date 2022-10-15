package com.andersen.banking.meeting_api.dto;

import com.andersen.banking.meeting_api.utility.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto request for ATM")
public class AtmDtoResponse {

    @Schema(description = OpenApiConstants.DESCRIPTION_ATM_ID, example = OpenApiConstants.EXAMPLE_UUID, defaultValue = OpenApiConstants.EXAMPLE_UUID)
    @JsonProperty("id")
    @NotNull(message = "field id can't be null")
    private UUID id;

    @Schema(description = OpenApiConstants.DESCRIPTION_STREET_ID, example = OpenApiConstants.EXAMPLE_LONG, defaultValue = OpenApiConstants.EXAMPLE_LONG)
    @JsonProperty("streetId")
    @NotNull(message = "field street id can't be null")
    private Long streetId;

    @Schema(description = OpenApiConstants.DESCRIPTION_HOUSE_NUMBER, example = OpenApiConstants.EXAMPLE_LONG, defaultValue = OpenApiConstants.EXAMPLE_LONG)
    @JsonProperty("houseNumber")
    @NotNull(message = "field houseNumber can't be null")
    private Long houseNumber;

    @Schema(description = OpenApiConstants.DESCRIPTION_BANK_BRAHCH_ID, example = OpenApiConstants.EXAMPLE_LONG, defaultValue = OpenApiConstants.EXAMPLE_LONG)
    @JsonProperty("bankBranch")
    private Long bankBranch;

    @Schema(description = OpenApiConstants.DESCRIPTION_ROUND_THE_CLOCK, example = OpenApiConstants.EXAMPLE_ROUND_THE_CLOCK, defaultValue = OpenApiConstants.EXAMPLE_ROUND_THE_CLOCK)
    @JsonProperty("roundTheClock")
    @NotNull(message = "field roundTheClock can't be null")
    private Boolean roundTheClock;

    @Schema(description = OpenApiConstants.DESCRIPTION_WORK_AT_WEEKEND, example = OpenApiConstants.EXAMPLE_WORK_AT_WEEKEND, defaultValue = OpenApiConstants.EXAMPLE_WORK_AT_WEEKEND)
    @JsonProperty("workAtWeekend")
    @NotNull(message = "field workAtWeekend can't be null")
    private Boolean workAtWeekend;

    @Schema(description = OpenApiConstants.DESCRIPTION_CASH_WITHDRAW, example = OpenApiConstants.EXAMPLE_CASH_WITHDRAW, defaultValue = OpenApiConstants.EXAMPLE_CASH_WITHDRAW)
    @JsonProperty("cashWithdraw")
    @NotNull(message = "field cashWithdraw can't be null")
    private Boolean cashWithdraw;

    @Schema(description = OpenApiConstants.DESCRIPTION_CURRENCY_EXCHANGE, example = OpenApiConstants.EXAMPLE_CURRENCY_EXCHANGE, defaultValue = OpenApiConstants.EXAMPLE_CURRENCY_EXCHANGE)
    @JsonProperty("currencyExchange")
    @NotNull
    private Boolean currencyExchange;

    @Schema(description = OpenApiConstants.DESCRIPTION_ATM_IS_ACTIVE, example = OpenApiConstants.EXAMPLE_ATM_IS_ACTIVE, defaultValue = OpenApiConstants.EXAMPLE_ATM_IS_ACTIVE)
    @JsonProperty("isActive")
    @NotNull(message = "field isActive can't be null")
    private Boolean isActive;

}
