package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_LONG;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_STREET;

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
public class StreetDto {

    @NotNull
    @Schema(example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    private Long id;

    @NotNull
    @Schema(example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    private Long cityId;

    @NotBlank
    @Schema(example = EXAMPLE_STREET, defaultValue = EXAMPLE_STREET)
    private String name;

}
