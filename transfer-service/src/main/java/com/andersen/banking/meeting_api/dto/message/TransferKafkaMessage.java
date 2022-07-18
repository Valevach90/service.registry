package com.andersen.banking.meeting_api.dto.message;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class TransferKafkaMessage {

    @NotNull
    private Long userId;

    @NotEmpty
    private String sourceNumber;

    @NotEmpty
    private String sourceType;

    @NotEmpty
    private String destinationNumber;

    @NotEmpty
    private String destinationType;

    @NotNull
    private Long amount;
    

}
