package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_CITY;

import com.andersen.banking.meeting_impl.config.CityNameConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDtoForSearch {

    @CityNameConstraint
    @Schema(example = EXAMPLE_CITY, defaultValue = EXAMPLE_CITY)
    private String name;
}
