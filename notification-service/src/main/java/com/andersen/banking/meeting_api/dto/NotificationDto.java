package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.util.OpenApiConstants.DESCRIPTION_EMAIL;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.DESCRIPTION_NOTIFICATION_CODE;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.DESCRIPTION_NOTIFICATION_STATUS;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.DESCRIPTION_NOTIFICATION_TIME;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_EMAIL;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_NOTIFICATION_CODE;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_NOTIFICATION_STATUS;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_TIME;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import lombok.Data;

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
