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

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class RegularPaymentRequestDto {

    @NotBlank
    @Pattern(regexp = "[a-zA-Z- ]{3,30}", message = "description should have at least 3 and at maximum 30 characters")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Regular payment start date", example = "2022-09-21",
            defaultValue = "2022-09-21")
    @JsonProperty("first_date")
    @NotNull
    private LocalDate firstDate;

    @Schema(description = "Regular payment last date", example = "2023-09-21",
            defaultValue = "2023-09-21")
    @JsonProperty("last_date")
    @NotNull
    private LocalDate lastDate;

    @JsonProperty("source_card_id")
    @NotNull
    private UUID sourceCardId;

    @JsonProperty("recipient_card_id")
    @NotNull
    private UUID recipientCardId;

    @JsonProperty("amount")
    @NotNull
    private Long amount;

    @Schema(description = "Frequency of sending payments", example = "monthly",
            defaultValue = "monthly")
    @JsonProperty("frequency")
    @NotNull
    private String frequency;
}
