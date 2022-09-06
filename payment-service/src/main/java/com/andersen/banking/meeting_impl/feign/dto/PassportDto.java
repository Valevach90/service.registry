package com.andersen.banking.meeting_impl.feign.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;
import lombok.NoArgsConstructor;

import static com.andersen.banking.meeting_impl.feign.OpenApiConstants.*;

/**
 * Dto for passport.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for passport")
public class PassportDto {

  @Schema(description = DESCRIPTION_PASSPORT_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
  @JsonProperty("id")
  @NotNull(message = "Passport id can't be null.")
  private Long id;

  @Schema(description = DESCRIPTION_USER_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
  @JsonProperty("userId")
  private UUID userId;

  @Schema(description = DESCRIPTION_ADDRESS_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
  @JsonProperty("addressId")
  private Long addressId;

  @Schema(description = DESCRIPTION_PASSPORT_SERIAL_NUMBER, example = EXAMPLE_PASSPORT_SERIAL_NUMBER,
      defaultValue = EXAMPLE_PASSPORT_SERIAL_NUMBER)
  @JsonProperty("serialNumber")
  @NotNull(message = "Serial number can't be null.")
  @Pattern(regexp = "[a-zA-z0-9]{2}", message = "Invalid passport serial number.")
  private String serialNumber;

  @Schema(description = DESCRIPTION_PASSPORT_CODE, example = EXAMPLE_PASSPORT_CODE, defaultValue = EXAMPLE_PASSPORT_CODE)
  @JsonProperty("passportCode")
  @NotNull(message = "Passport code can't be null.")
  @Pattern(regexp = "[0-9]{7}", message = "Invalid passport code.")
  private String passportCode;

  @Schema(description = DESCRIPTION_PASSPORT_BIRTHDAY, example = EXAMPLE_DATE, defaultValue = EXAMPLE_DATE)
  @JsonProperty("birthday")
  @NotNull(message = "Birthday can't be null.")
  private LocalDate birthday;

  @Schema(description = DESCRIPTION_PASSPORT_DATE_ISSUE, example = EXAMPLE_ISSUE_DATE, defaultValue = EXAMPLE_ISSUE_DATE)
  @JsonProperty("dateIssue")
  @NotNull(message = "Issue date can't be null.")
  private LocalDate dateIssue;

  @Schema(description = DESCRIPTION_PASSPORT_TERMINATION_DATE, example = EXAMPLE_TERMINATION_DATE,
      defaultValue = EXAMPLE_TERMINATION_DATE)
  @JsonProperty("terminationDate")
  @NotNull(message = "Termination date can't be null.")
  private LocalDate terminationDate;

  @Schema(description = DESCRIPTION_FIRST_NAME, example = EXAMPLE_FIRST_NAME, defaultValue = EXAMPLE_FIRST_NAME)
  @JsonProperty("firstName")
  @NotNull(message = "First name can't be null.")
  @Pattern(regexp = "(?=.{1,30}$)([a-zA-Z]+(?:[-]?[a-zA-Z]+))|[a-zA-Z]{1,30}", message = "Invalid first name.")
  private String firstName;

  @Schema(description = DESCRIPTION_LAST_NAME, example = EXAMPLE_LAST_NAME, defaultValue = EXAMPLE_LAST_NAME)
  @JsonProperty("lastName")
  @NotNull(message = "Last name can't be null.")
  @Pattern(regexp = "(?=.{1,30}$)([a-zA-Z]+(?:[-]?[a-zA-Z]+))|[a-zA-Z]{1,30}", message = "Invalid last name.")
  private String lastName;

  @Schema(description = DESCRIPTION_PATRONYMIC, example = EXAMPLE_PATRONYMIC, defaultValue = EXAMPLE_PATRONYMIC)
  @JsonProperty("patronymic")
  @Pattern(regexp = "(?=.{1,30}$)([a-zA-Z]+(?:[-]?[a-zA-Z]+))|[a-zA-Z]{1,30}", message = "Invalid patronymic.")
  private String patronymic;

  @Schema(description = DESCRIPTION_PASSPORT_DEPARTMENT_ISSUED, example = EXAMPLE_PASSPORT_DEPARTMENT_ISSUED,
      defaultValue = EXAMPLE_PASSPORT_DEPARTMENT_ISSUED)
  @JsonProperty("departmentIssued")
  @Pattern(regexp = "[a-zA-Z ]{1,45}", message = "Invalid department name.")
  @NotNull(message = "Department can't be null.")
  private String departmentIssued;

  @Schema(description = DESCRIPTION_PASSPORT_BORN_PLACE, example = EXAMPLE_PASSPORT_BORN_PLACE,
      defaultValue = EXAMPLE_PASSPORT_BORN_PLACE)
  @JsonProperty("bornPlace")
  @Pattern(regexp = "[a-zA-Z ]{1,45}", message = "Invalid birth place.")
  @NotNull(message = "Birth place can't be null.")
  private String bornPlace;
}
