package com.andersen.banking.meeting_api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TransferRequestDto {

    @NotBlank
    private String sourceNumber;

    @NotNull
    private Long sourcePaymentTypeId;

    @NotBlank
    private String destinationNumber;

    @NotNull
    private Long destinationPaymentTypeId;

    @NotNull
    private Long amount;

}
