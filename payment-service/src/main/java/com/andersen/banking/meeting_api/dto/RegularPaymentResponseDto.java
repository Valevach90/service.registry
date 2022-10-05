package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_DATE;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_FREQUENCY;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class RegularPaymentResponseDto {

    @JsonProperty("id")
    @NotNull
    private UUID id;

    @NotBlank
    @Pattern(
            regexp = "[a-zA-Z- ]{3,30}",
            message = "description should have at least 3 and at maximum 30 characters")
    @JsonProperty("description")
    private String description;

    @Schema(
            description = "Regular payment start date",
            example = EXAMPLE_DATE,
            defaultValue = EXAMPLE_DATE)
    @JsonProperty("start_date")
    @NotNull
    private LocalDate startDate;

    @Schema(
            description = "Regular payment last date",
            example = EXAMPLE_DATE,
            defaultValue = EXAMPLE_DATE)
    @JsonProperty("next_date")
    @NotNull
    private LocalDate nextDate;

    @JsonProperty("source_card_id")
    @NotNull
    private UUID sourceCardId;

    @JsonProperty("recipient_card_id")
    @NotNull
    private UUID recipientCardId;

    @JsonProperty("amount")
    @NotNull
    private Long amount;

    @Schema(
            description = "Frequency of sending payments",
            example = EXAMPLE_FREQUENCY,
            defaultValue = EXAMPLE_FREQUENCY)
    @JsonProperty("frequency")
    @NotNull
    private String frequency;
}
