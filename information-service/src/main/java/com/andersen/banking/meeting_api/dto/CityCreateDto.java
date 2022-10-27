package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_CITY;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_LONG;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityCreateDto {

    @NotNull
    @Schema(example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    private Long countryId;

    @NotBlank
    @Schema(example = EXAMPLE_CITY, defaultValue = EXAMPLE_CITY)
    private String name;

}
