package com.andersen.banking.service.registry.meeting_api.dto;


import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.*;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private Long id;

  @Schema(description = DESCRIPTION_FIRST_NAME, example = EXAMPLE_FIRST_NAME, defaultValue = EXAMPLE_FIRST_NAME)
  @JsonProperty("first_name")
  @NotNull(message = "User first name can't be null.")
  @Pattern(regexp = "(?=.{1,30}$)([a-zA-Z]+(?:[-]?[a-zA-Z]+))|[a-zA-Z]{1,30}", message = "Invalid first name.")
  private String firstName;

  @Schema(description = DESCRIPTION_LAST_NAME, example = EXAMPLE_LAST_NAME, defaultValue = EXAMPLE_LAST_NAME)
  @JsonProperty("last_name")
  @NotNull(message = "User last name can't be null.")
  @Pattern(regexp = "(?=.{1,30}$)([a-zA-Z]+(?:[-]?[a-zA-Z]+))|[a-zA-Z]{1,30}", message = "Invalid last name.")
  private String lastName;

  @Schema(description = DESCRIPTION_PATRONYMIC, example = EXAMPLE_PATRONYMIC, defaultValue = EXAMPLE_PATRONYMIC)
  @JsonProperty("patronymic")
  @Pattern(regexp = "(?=.{1,30}$)([a-zA-Z]+(?:[-]?[a-zA-Z]+))|[a-zA-Z]{1,30}", message = "Invalid patronymic.")
  private String patronymic;

  @Schema(description = DESCRIPTION_EMAIL, example = EXAMPLE_EMAIL, defaultValue = EXAMPLE_EMAIL)
  @JsonProperty("email")
  @NotNull(message = "Email can't be null.")
  @Pattern(regexp = "(?=.{1,50}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})",
      message = "Invalid email.")
  private String email;

  @Schema(description = DESCRIPTION_PHONE, example = EXAMPLE_PHONE, defaultValue = EXAMPLE_PHONE)
  @JsonProperty("phone")
  @NotNull(message = "Phone number can't be null.")
  @Pattern(regexp = "[0-9]{10,12}", message = "Invalid phone number.")
  private String phone;

}
