package com.andersen.banking.meeting_impl.kafka.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseKafkaTransferMessage {


    @NotNull
    private Long transferId;

    @NotNull
    private boolean result;

    private String statusDescription;

}
