package com.andersen.banking.meeting_api.error;

import com.andersen.banking.meeting_api.utils.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Dto for errors.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Response error")
public class NotFoundError {

    @Schema(description = OpenApiConstants.DESCRIPTION_ERROR,
            example = OpenApiConstants.EXAMPLE_ERROR_NOT_FOUND, defaultValue = OpenApiConstants.EXAMPLE_ERROR_NOT_FOUND)
    @JsonProperty("message")
    private String message;

    @Schema(description = OpenApiConstants.DESCRIPTION_ERROR_CODE, example = OpenApiConstants.HTTP_NOT_FOUND, defaultValue = OpenApiConstants.HTTP_NOT_FOUND)
    @JsonProperty("errorCode")
    private int errorCode;
}
