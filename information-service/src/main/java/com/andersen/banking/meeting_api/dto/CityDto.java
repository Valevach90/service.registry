package com.andersen.banking.meeting_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {

    @NotNull
    private Long id;

    @NotNull
    private Long countryId;

    @NotBlank
    private String name;

}