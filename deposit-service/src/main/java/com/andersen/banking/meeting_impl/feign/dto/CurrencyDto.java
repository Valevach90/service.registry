package com.andersen.banking.meeting_impl.feign.dto;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
