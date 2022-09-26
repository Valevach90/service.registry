package com.andersen.banking.meeting_impl.kafka.message;

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
public class ResponseTransferMessage {

    @NotNull
    private UUID transferId;

    @NotNull
    private Integer status;

    private String statusDescription;

    private String service;
}
