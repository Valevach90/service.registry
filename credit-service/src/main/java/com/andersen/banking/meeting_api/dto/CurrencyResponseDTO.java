package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.utils.OpenApiConstants.*;

import com.andersen.banking.meeting_api.utils.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "currency response dto")
public class CurrencyResponseDTO {

    @Schema(description = DESCRIPTION_CURRENCY_ID,
        example = EXAMPLE_ID)
    @JsonProperty("id")
    @NotNull(message = "Currency id can't be null")
    private UUID id;

    @Schema(description = DESCRIPTION_CURRENCY,
        example = EXAMPLE_CURRENCY)
    @JsonProperty("currency_name")
    @NotNull(message = "Currency can't be null")
    private String name;
}
