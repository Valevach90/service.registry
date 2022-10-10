package com.andersen.banking.meeting_impl.feign.dto;

import java.sql.Timestamp;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String sourceNumber;

    @NotBlank
    private String sourcePaymentTypeName;

    @NotBlank
    private String destinationNumber;

    @NotBlank
    private String destinationPaymentTypeName;

    @NotBlank
    private int status;

    @NotNull
    private Timestamp createTime;

    @NotNull
    private Long amount;

    @NotBlank
    private String currencyName;

}
