package com.andersen.banking.meeting_api.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeResponseDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
