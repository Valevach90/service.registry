package com.andersen.banking.service.registry.meeting_api.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_ERROR_CODE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_ERROR_DESCRIPTION;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_ERROR_METHOD_NOT_ALLOWED;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.HTTP_METHOD_NOT_ALLOWED;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "MethodNotAllowed error")
public class MethodNotAllowedError {

    @Schema(description = DESCRIPTION_ERROR_DESCRIPTION,
            example = EXAMPLE_ERROR_METHOD_NOT_ALLOWED, defaultValue = EXAMPLE_ERROR_METHOD_NOT_ALLOWED)
    @JsonProperty("message")
    private String message;

    @Schema(description = DESCRIPTION_ERROR_CODE, example = HTTP_METHOD_NOT_ALLOWED, defaultValue = HTTP_METHOD_NOT_ALLOWED)
    @JsonProperty("errorCode")
    private int errorCode;
}
