package com.andersen.banking.meeting_api.dto.responce;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CurrencyResponseDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
