package com.andersen.banking.meeting_api.dto;

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
@Schema(description = "dto for ATM")
public class AtmDto {

    @Schema(description = "", example = "", defaultValue = "")
    @JsonProperty("id")
    @NotNull(message = "field id can't be null")
    private UUID id;

    @Schema(description = "", example = "", defaultValue = "")
    @JsonProperty("streetId")
    @NotNull(message = "field street id can't be null")
    private Long streetId;

    @Schema(description = "", example = "", defaultValue = "")
    @JsonProperty("houseNumber")
    @NotNull(message = "field houseNumber can't be null")
    private Long houseNumber;

    @Schema(description = "", example = "", defaultValue = "")
    @JsonProperty("bankBranch")
    private Long bankBranch;

    @Schema(description = "", example = "", defaultValue = "")
    @JsonProperty("roundTheClock")
    @NotNull(message = "field roundTheClock can't be null")
    private Boolean roundTheClock;

    @Schema(description = "", example = "", defaultValue = "")
    @JsonProperty("workAtWeekend")
    @NotNull(message = "field workAtWeekend can't be null")
    private Boolean workAtWeekend;

    @Schema(description = "", example = "", defaultValue = "")
    @JsonProperty("cashWithdraw")
    @NotNull(message = "field cashWithdraw can't be null")
    private Boolean cashWithdraw;

    @Schema(description = "", example = "", defaultValue = "")
    @JsonProperty("currencyExchange")
    @NotNull(message = "field currencyExchange can't be null")
    private Boolean currencyExchange;

    @Schema(description = "", example = "", defaultValue = "")
    @JsonProperty("isActive")
    @NotNull(message = "field isActive can't be null")
    private Boolean isActive;
}
