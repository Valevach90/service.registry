package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Dto for deposit product description.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for deposit product description")
public class DepositProductDescriptionDto {

    @JsonProperty("id")
    private UUID id;

    @NotBlank
    @JsonProperty("shortDescription")
    private String shortDescription;

    @JsonProperty("fullDescription")
    private String fullDescription;
}
