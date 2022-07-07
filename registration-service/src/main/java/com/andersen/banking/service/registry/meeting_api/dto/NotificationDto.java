package com.andersen.banking.service.registry.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.*;

/**
 * Dto for notification.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for notification")
public class NotificationDto {

    @Schema(description = DESCRIPTION_EMAIL, example = EXAMPLE_EMAIL, defaultValue = EXAMPLE_EMAIL)
    @JsonProperty("email")
    @NotNull(message = "Email can't be null.")
    private String email;

    @Schema(description = DESCRIPTION_NOTIFICATION_CODE, example = EXAMPLE_NOTIFICATION_CODE, defaultValue = EXAMPLE_NOTIFICATION_CODE)
    @JsonProperty("code")
    private String code;

    @Schema(description = DESCRIPTION_NOTIFICATION_TIME, example = EXAMPLE_TIME, defaultValue = EXAMPLE_TIME)
    @JsonProperty("time")
    @NotNull(message = "Time can't be null.")
    private Timestamp time;

    @Schema(description = DESCRIPTION_NOTIFICATION_STATUS, example = EXAMPLE_NOTIFICATION_STATUS, defaultValue = EXAMPLE_NOTIFICATION_STATUS)
    @JsonProperty("status")
    @NotNull(message = "Status can't be null.")
    private String status;
}
