package com.andersen.banking.meeting_api.dto;

import com.andersen.banking.meeting_db.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableDto {

    @NotNull
    private Long id;

    @NotBlank
    private String dayFrom;

    @NotBlank
    private String dayTo;

    @NotBlank
    private String timeFrom;

    @NotBlank
    private String timeTo;
}
