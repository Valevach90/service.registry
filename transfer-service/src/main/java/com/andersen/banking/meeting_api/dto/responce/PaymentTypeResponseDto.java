package com.andersen.banking.meeting_api.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeResponseDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
