package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.utils.OpenApiConstants.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Dto for linked card info.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for linked card info")
public class LinkedCardDto {

    @NotNull
    @JsonProperty("id")
    @Schema(description = DESCRIPTION_CARD_ID, example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    private UUID id;

    @NotBlank
    @JsonProperty("firstTwelveNumbers")
    @Schema(description = DESCRIPTION_CARD_FIRST_NUMBER, example = EXAMPLE_HASH_FIRST_NUMBER_CARD, defaultValue = EXAMPLE_HASH_FIRST_NUMBER_CARD)
    private String firstTwelveNumbers;

    @NotBlank
    @JsonProperty("lastFourNumbers")
    @Schema(description = DESCRIPTION_CARD_LAST_NUMBER, example = EXAMPLE_LAST_NUMBER_CARD, defaultValue = EXAMPLE_LAST_NUMBER_CARD)
    private String lastFourNumbers;

    @NotNull
    @JsonProperty("flag means that card is a main card for all operations")
    @Schema(description = DESCRIPTION_FLAG_FOR_DEFAULT_CARD, example = EXAMPLE_BOOLEAN, defaultValue = EXAMPLE_BOOLEAN_FALSE)
    private Boolean defaultCard;
}
