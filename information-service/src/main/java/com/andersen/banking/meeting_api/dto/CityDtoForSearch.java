package com.andersen.banking.meeting_api.dto;

import com.andersen.banking.meeting_impl.config.CityNameConstraint;
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
    private String name;
}
