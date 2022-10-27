package com.andersen.banking.meeting_impl.feign.dto;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDto {

    @NotNull
    private UUID userId;

    @NotBlank
    private String sourceNumber;

    @NotNull
    private UUID sourcePaymentTypeId;

    @NotBlank
    private String destinationNumber;

    @NotNull
    private UUID destinationPaymentTypeId;

    @NotNull
    private Long amount;

    @NotNull
    private UUID currencyId;

    private String comment;

}
