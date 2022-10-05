package com.andersen.banking.meeting_impl.feign.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
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

    @Schema(hidden = true)
    private StatusTransfer statusTransfer;

    private String comment;

}
