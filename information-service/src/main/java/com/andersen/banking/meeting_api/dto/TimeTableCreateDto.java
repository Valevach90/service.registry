package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_DAY_FROM;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_DAY_TO;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_LONG;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_TIME_FROM;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_TIME_TO;

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
public class TimeTableCreateDto {

    @NotNull
    @Schema(example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    private Long branchId;

    @NotBlank
    @Schema(example = EXAMPLE_DAY_FROM, defaultValue = EXAMPLE_DAY_FROM)
    private String dayFrom;

    @NotBlank
    @Schema(example = EXAMPLE_DAY_TO, defaultValue = EXAMPLE_DAY_TO)
    private String dayTo;

    @NotBlank
    @Schema(example = EXAMPLE_TIME_FROM, defaultValue = EXAMPLE_TIME_FROM)
    private String timeFrom;

    @NotBlank
    @Schema(example = EXAMPLE_TIME_TO, defaultValue = EXAMPLE_TIME_TO)
    private String timeTo;
}
