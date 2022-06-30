package com.andersen.banking.meeting_api.dto.responce;


import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class TransferResponseDto {

    @NotBlank
    private String sourceNumber;

    @NotBlank
    private String sourcePaymentTypeName;

    @NotBlank
    private String destinationNumber;

    @NotBlank
    private String destinationPaymentTypeName;

    @NotBlank
    private String transactionStatusName;

    @NotNull
    private Timestamp createTime;

    @NotNull
    private Long amount;

    @NotBlank
    private String currencyName;

}
