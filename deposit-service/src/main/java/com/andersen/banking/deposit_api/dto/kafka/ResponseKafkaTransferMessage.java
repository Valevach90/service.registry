package com.andersen.banking.deposit_api.dto.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseKafkaTransferMessage {

    @NotNull
    @JsonProperty("transferId")
    private Long transferId;

    @NotNull
    @JsonProperty("result")
    private Boolean result;

    @JsonProperty("statusDescription")
    private String statusDescription;
}
