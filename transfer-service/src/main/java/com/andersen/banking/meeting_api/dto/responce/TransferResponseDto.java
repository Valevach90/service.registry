package com.andersen.banking.meeting_api.dto.responce;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class TransferResponseDto {

    private String sourceNumber;

    private String sourceType;

    private String destinationNumber;

    private String destinationType;

    private String transactionStatus;

    private Timestamp createTime;

}
