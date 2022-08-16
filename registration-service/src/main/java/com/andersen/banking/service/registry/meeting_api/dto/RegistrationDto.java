package com.andersen.banking.service.registry.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for registration")
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    @JsonProperty("isRegistered")
    private boolean isRegistered;
}
