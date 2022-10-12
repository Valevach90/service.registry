package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.utils.OpenApiConstants.EXAMPLE_DESCRIPTION;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Dto for deposit product description.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for deposit product description")
public class DepositProductDescriptionCreateDto {

    @NotBlank
    @JsonProperty("shortDescription")
    @Schema(example = EXAMPLE_DESCRIPTION, defaultValue = EXAMPLE_DESCRIPTION)
    private String shortDescription;

    @JsonProperty("fullDescription")
    @Schema(example = EXAMPLE_DESCRIPTION, defaultValue = EXAMPLE_DESCRIPTION)
    private String fullDescription;
}
