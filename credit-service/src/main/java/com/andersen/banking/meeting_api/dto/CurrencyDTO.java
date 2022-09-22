package com.andersen.banking.meeting_api.dto;

import com.andersen.banking.meeting_api.utils.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "currency dto")
public class CurrencyDTO {

    @Schema(description = OpenApiConstants.DESCRIPTION_CURRENCY_ID,
        example = OpenApiConstants.EXAMPLE_ID)
    @JsonProperty("id")
    @NotNull(message = "Currency id can't be null")
    private UUID id;

    @Schema(description = OpenApiConstants.DESCRIPTION_CURRENCY,
        example = OpenApiConstants.EXAMPLE_CURRENCY)
    @JsonProperty("currency_name")
    @NotNull(message = "Currency can't be null")
    private String name;

}
