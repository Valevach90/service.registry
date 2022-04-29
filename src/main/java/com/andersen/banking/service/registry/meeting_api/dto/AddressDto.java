package com.andersen.banking.service.registry.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_ADDRESS_ID;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_BUILDING;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_CITY;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_COUNTRY;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_FLAT;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_HOUSE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_LOCATION;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_REGION;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_STREET;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_USER_ID;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_ZIP_CODE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_BUILDING;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_CITY;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_COUNTRY;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_FLAT;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_HOUSE;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_LOCATION;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_LONG;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_REGION;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_STREET;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_ZIP_CODE;

/**
 * Address Dto with full information about user.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for address")
public class AddressDto {

    @Schema(description = DESCRIPTION_ADDRESS_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    private Long id;

    @Schema(description = DESCRIPTION_USER_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("userId")
    private Long userId;

    @Schema(description = DESCRIPTION_ZIP_CODE, example = EXAMPLE_ZIP_CODE, defaultValue = EXAMPLE_ZIP_CODE)
    @JsonProperty("zipCode")
    private Integer zipCode;

    @Schema(description = DESCRIPTION_COUNTRY, example = EXAMPLE_COUNTRY, defaultValue = EXAMPLE_COUNTRY)
    @JsonProperty("country")
    private String country;

    @Schema(description = DESCRIPTION_REGION, example = EXAMPLE_REGION, defaultValue = EXAMPLE_REGION)
    @JsonProperty("region")
    private String region;

    @Schema(description = DESCRIPTION_LOCATION, example = EXAMPLE_LOCATION, defaultValue = EXAMPLE_LOCATION)
    @JsonProperty("location")
    private String location;

    @Schema(description = DESCRIPTION_CITY, example = EXAMPLE_CITY, defaultValue = EXAMPLE_CITY)
    @JsonProperty("city")
    private String city;

    @Schema(description = DESCRIPTION_STREET, example = EXAMPLE_STREET, defaultValue = EXAMPLE_STREET)
    @JsonProperty("street")
    private String street;

    @Schema(description = DESCRIPTION_HOUSE, example = EXAMPLE_HOUSE, defaultValue = EXAMPLE_HOUSE)
    @JsonProperty("house")
    private String house;

    @Schema(description = DESCRIPTION_BUILDING, example = EXAMPLE_BUILDING, defaultValue = EXAMPLE_BUILDING)
    @JsonProperty("building")
    private String building;

    @Schema(description = DESCRIPTION_FLAT, example = EXAMPLE_FLAT, defaultValue = EXAMPLE_FLAT)
    @JsonProperty("flat")
    private String flat;
}
