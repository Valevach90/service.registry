package com.andersen.banking.meeting_api.dto.message;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferKafkaDeposit {

    @JsonProperty("message")
    private String message;

}
