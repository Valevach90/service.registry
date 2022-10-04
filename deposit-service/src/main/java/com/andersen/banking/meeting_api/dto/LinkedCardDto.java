package com.andersen.banking.meeting_api.dto;

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
    private UUID id;

    @NotBlank
    @JsonProperty("firstTwelveNumbers")
    private String firstTwelveNumbers;

    @NotBlank
    @JsonProperty("lastFourNumbers")
    private String lastFourNumbers;
}
