package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.util.OpenApiConstants.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardCredResponseDto {

    @NotNull
    @JsonProperty("id")
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    private UUID id;

    @NotBlank
    @JsonProperty("first_twelve")
    private String firstTwelveNumbers;

    @NotBlank
    @JsonProperty("last_four_numbers")
    @Pattern(regexp = "[0-9]{4}", message = "last_four_numbers should contain exactly 4 digits")
    @Schema(example = EXAMPLE_LAST_NUMBER_CARD, defaultValue = EXAMPLE_LAST_NUMBER_CARD)
    private String lastFourNumbers;

    @NotBlank
    @JsonProperty("holder_name")
    @Pattern(
            regexp = "[a-zA-Z- ]{3,30}",
            message = "holder_name should have at least 3 and at maximum 30 characters")
    @Schema(example = EXAMPLE_NAME, defaultValue = EXAMPLE_NAME)
    private String holderName;

    @NotNull
    @JsonProperty("payment_system")
    @Schema(example = EXAMPLE_PAYMENT_SYSTEM, defaultValue = EXAMPLE_PAYMENT_SYSTEM)
    private String paymentSystem;

    @NotNull
    @JsonProperty("type_name")
    private String typeName;
}
