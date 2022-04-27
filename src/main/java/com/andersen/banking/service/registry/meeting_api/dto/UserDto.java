package com.andersen.banking.service.registry.meeting_api.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static com.andersen.banking.service.registry.meeting_api.utils.UserOpenApiConstants.*;

/**
 * Dto for updating User.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for user")
public class UserDto {

    @Schema(description = DESCRIPTION_FIRST_NAME, example = EXAMPLE_FIRST_NAME, defaultValue = EXAMPLE_FIRST_NAME)
    @JsonProperty("first_name")
    private String firstName;

    @Schema(description = DESCRIPTION_LAST_NAME, example = EXAMPLE_LAST_NAME, defaultValue = EXAMPLE_LAST_NAME)
    @JsonProperty("last_name")
    private String lastName;

    @Schema(description = DESCRIPTION_PATRONYMIC, example = EXAMPLE_PATRONYMIC, defaultValue = EXAMPLE_PATRONYMIC)
    @JsonProperty("patronymic")
    private String patronymic;

    @Schema(description = DESCRIPTION_EMAIL, example = EXAMPLE_EMAIL, defaultValue = EXAMPLE_EMAIL)
    @JsonProperty("email")
    private String email;

    @Schema(description = DESCRIPTION_PHONE, example = EXAMPLE_PHONE, defaultValue = EXAMPLE_PHONE)
    @JsonProperty("phone")
    private String phone;

}
