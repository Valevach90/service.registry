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
public class AddressDto {

    @NotNull
    private Long id;

    @NotNull
    private Long streetId;

    @NotBlank
    private String house;

    @NotBlank
    private String building;

}