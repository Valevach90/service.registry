package com.andersen.banking.meeting_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FrequencyDto {

    private Long days;
    private Long weeks;
    private Long mounts;
    private Long years;
}
