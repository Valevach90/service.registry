package com.andersen.banking.meeting_api.dto.message;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferKafkaDeposit {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("interestRate")
    private Long interestRate;

    @JsonProperty("accrued")
    private Long accrued;

    @JsonProperty("currency")
    private String currency;
}
