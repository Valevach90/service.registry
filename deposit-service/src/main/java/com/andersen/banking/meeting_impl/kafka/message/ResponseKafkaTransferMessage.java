package com.andersen.banking.meeting_impl.kafka.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseKafkaTransferMessage {

    @NotNull
    @JsonProperty("transferId")
    private UUID transferId;

    @NotNull
    @JsonProperty("result")
    private Boolean result;

    @JsonProperty("statusDescription")
    private String statusDescription;
}