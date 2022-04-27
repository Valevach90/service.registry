package com.andersen.banking.service.registry.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_ADDRESS_ID;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_FIRST_NAME;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_LAST_NAME;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PASSPORT_BIRTHDAY;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PASSPORT_BORN_PLACE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PASSPORT_CODE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PASSPORT_DATE_ISSUE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PASSPORT_DEPARTMENT_ISSUED;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PASSPORT_DIVISION_CODE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PASSPORT_ID;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PASSPORT_SERIAL_NUMBER;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PASSPORT_TERMINATION_DATE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_PATRONYMIC;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_USER_ID;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_DATE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_FIRST_NAME;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_LAST_NAME;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_LONG;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_PASSPORT_BORN_PLACE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_PASSPORT_CODE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_PASSPORT_DEPARTMENT_ISSUED;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_PASSPORT_DIVISION_CODE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_PASSPORT_SERIAL_NUMBER;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_PATRONYMIC;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for passport")
public class PassportDto {

    @Schema(description = DESCRIPTION_PASSPORT_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    private Long id;

    @Schema(description = DESCRIPTION_USER_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("userId")
    private Long userId;

    @Schema(description = DESCRIPTION_ADDRESS_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("addressId")
    private Long addressId;

    @Schema(description = DESCRIPTION_PASSPORT_SERIAL_NUMBER, example = EXAMPLE_PASSPORT_SERIAL_NUMBER,
            defaultValue = EXAMPLE_PASSPORT_SERIAL_NUMBER)
    @JsonProperty("serialNumber")
    private String serialNumber;

    @Schema(description = DESCRIPTION_PASSPORT_CODE, example = EXAMPLE_PASSPORT_CODE, defaultValue = EXAMPLE_PASSPORT_CODE)
    @JsonProperty("passportCode")
    private String passportCode;

    @Schema(description = DESCRIPTION_PASSPORT_DIVISION_CODE, example = EXAMPLE_PASSPORT_DIVISION_CODE,
            defaultValue = EXAMPLE_PASSPORT_DIVISION_CODE)
    @JsonProperty("divisionCode")
    private String divisionCode;

    @Schema(description = DESCRIPTION_PASSPORT_BIRTHDAY, example = EXAMPLE_DATE, defaultValue = EXAMPLE_DATE)
    @JsonProperty("birthday")
    private LocalDate birthday;

    @Schema(description = DESCRIPTION_PASSPORT_DATE_ISSUE, example = EXAMPLE_DATE, defaultValue = EXAMPLE_DATE)
    @JsonProperty("dateIssue")
    private LocalDate dateIssue;

    @Schema(description = DESCRIPTION_PASSPORT_TERMINATION_DATE, example = EXAMPLE_DATE, defaultValue = EXAMPLE_DATE)
    @JsonProperty("terminationDate")
    private LocalDate terminationDate;

    @Schema(description = DESCRIPTION_FIRST_NAME, example = EXAMPLE_FIRST_NAME, defaultValue = EXAMPLE_FIRST_NAME)
    @JsonProperty("firstName")
    private String firstName;

    @Schema(description = DESCRIPTION_LAST_NAME, example = EXAMPLE_LAST_NAME, defaultValue = EXAMPLE_LAST_NAME)
    @JsonProperty("lastName")
    private String lastName;

    @Schema(description = DESCRIPTION_PATRONYMIC, example = EXAMPLE_PATRONYMIC, defaultValue = EXAMPLE_PATRONYMIC)
    @JsonProperty("patronymic")
    private String patronymic;

    @Schema(description = DESCRIPTION_PASSPORT_DEPARTMENT_ISSUED, example = EXAMPLE_PASSPORT_DEPARTMENT_ISSUED,
            defaultValue = EXAMPLE_PASSPORT_DEPARTMENT_ISSUED)
    @JsonProperty("departmentIssued")
    private String departmentIssued;

    @Schema(description = DESCRIPTION_PASSPORT_BORN_PLACE, example = EXAMPLE_PASSPORT_BORN_PLACE,
            defaultValue = EXAMPLE_PASSPORT_BORN_PLACE)
    @JsonProperty("bornPlace")
    private String bornPlace;
}
