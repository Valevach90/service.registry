package com.andersen.banking.service.registry.meeting_api.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.validation.constraints.NotNull;

import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_EMAIL;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_FIRST_NAME;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_LAST_NAME;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PATRONYMIC;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PHONE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_USER_ID;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_EMAIL;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_FIRST_NAME;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_LAST_NAME;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_LONG;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_PATRONYMIC;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_PHONE;

/**
 * Dto for updating User.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for user")
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Schema(description = DESCRIPTION_USER_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    @NotNull
    private Long id;

    @Schema(description = DESCRIPTION_FIRST_NAME, example = EXAMPLE_FIRST_NAME, defaultValue = EXAMPLE_FIRST_NAME)
    @JsonProperty("first_name")
    @NotNull
    private String firstName;

    @Schema(description = DESCRIPTION_LAST_NAME, example = EXAMPLE_LAST_NAME, defaultValue = EXAMPLE_LAST_NAME)
    @JsonProperty("last_name")
    @NotNull
    private String lastName;

    @Schema(description = DESCRIPTION_PATRONYMIC, example = EXAMPLE_PATRONYMIC, defaultValue = EXAMPLE_PATRONYMIC)
    @JsonProperty("patronymic")
    @NotNull
    private String patronymic;

    @Schema(description = DESCRIPTION_EMAIL, example = EXAMPLE_EMAIL, defaultValue = EXAMPLE_EMAIL)
    @JsonProperty("email")
    @NotNull
    private String email;

    @Schema(description = DESCRIPTION_PHONE, example = EXAMPLE_PHONE, defaultValue = EXAMPLE_PHONE)
    @JsonProperty("phone")
    @NotNull
    private String phone;

}