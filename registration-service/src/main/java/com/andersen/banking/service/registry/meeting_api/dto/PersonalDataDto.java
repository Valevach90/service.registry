package com.andersen.banking.service.registry.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.*;

/**
 * Dto for user personal data.
 */

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for personal data")
public class PersonalDataDto {

    @Schema(description = DESCRIPTION_USER)
    @JsonProperty("user")
    @NotNull(message = "User can't be null.")
    UserDto user;

    @Schema(description = DESCRIPTION_PASSPORT)
    @JsonProperty("passport")
    @NotNull(message = "Passport can't be null.")
    PassportDto passport;

    @Schema(description = DESCRIPTION_ADDRESS)
    @JsonProperty("address")
    @NotNull(message = "Address can't be null.")
    AddressDto address;
}