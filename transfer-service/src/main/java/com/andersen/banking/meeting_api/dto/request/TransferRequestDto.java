package com.andersen.banking.meeting_api.dto.request;

import lombok.Data;

@Data
public class TransferRequestDto {

    private String sourceNumber;

    private String sourceType;

    private String destinationNumber;

    private String destinationType;

}
