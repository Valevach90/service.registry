package com.andersen.banking.meeting_impl.kafka.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseTransferMessage {

    @NotNull
    private UUID transferId;

    @NotNull
    private Integer status;

    private String statusDescription;

    private String service;
}
