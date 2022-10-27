package com.andersen.banking.meeting_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FrequencyDto {

    private Long years;
    private Long mounts;
    private Long weeks;
    private Long days;

}
