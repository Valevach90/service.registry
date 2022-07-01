package com.andersen.banking.deposit.service.deposit_api.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static com.andersen.banking.deposit.service.deposit_api.utils.OpenApiConstants.*;

/**
 * Dto for errors.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Response error")
public class NotFoundError {

    @Schema(description = DESCRIPTION_ERROR,
            example = EXAMPLE_ERROR_NOT_FOUND, defaultValue = EXAMPLE_ERROR_NOT_FOUND)
    @JsonProperty("message")
    private String message;

    @Schema(description = DESCRIPTION_ERROR_CODE, example = HTTP_NOT_FOUND, defaultValue = HTTP_NOT_FOUND)
    @JsonProperty("errorCode")
    private int errorCode;
}
