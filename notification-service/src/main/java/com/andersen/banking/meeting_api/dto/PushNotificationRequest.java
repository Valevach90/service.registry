package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Request entity for push notifications.")
public class PushNotificationRequest {

    @JsonProperty("title")
    @NotNull
    private String title;

    @JsonProperty("message")
    @NotNull
    private String message;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("token")
    @NotNull
    private String token;
}
