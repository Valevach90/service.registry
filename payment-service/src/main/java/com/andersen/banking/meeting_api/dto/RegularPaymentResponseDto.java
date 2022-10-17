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
import java.time.LocalDate;
import java.util.UUID;

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
    @Schema(example = EXAMPLE_DESCRIPTION, defaultValue = EXAMPLE_DESCRIPTION)
    private String description;

    @Schema(
            description = "Regular payment start date",
            example = "2022-09-21",
            defaultValue = "2022-09-21")
    @JsonProperty("start_date")
    @NotNull
    private LocalDate startDate;

    @Schema(
            description = "Regular payment last date",
            example = "2023-09-21",
            defaultValue = "2023-09-21")
    @JsonProperty("next_date")
    @NotNull
    private LocalDate nextDate;

    @JsonProperty("source_card_id")
    @NotNull
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    private UUID sourceCardId;

    @JsonProperty("recipient_card_id")
    @NotNull
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    private UUID recipientCardId;

    @JsonProperty("amount")
    @NotNull
    @Schema(example = EXAMPLE_BALANCE, defaultValue = EXAMPLE_BALANCE)
    private Long amount;

    @Schema(
            description = EXAMPLE_DESCRIPTION_FREQUENCY,
            example = EXAMPLE_FREQUENCY,
            defaultValue = EXAMPLE_FREQUENCY)
    @JsonProperty("frequency")
    @NotNull
    private String frequency;


    @JsonProperty("is_active")
    @NotNull
    private Boolean isActive;
}
