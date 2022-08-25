package com.andersen.banking.meeting_impl.kafka.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestKafkaTransferMessage {

    @NotNull
    @JsonProperty("transferId")
    private UUID transferId;

    @NotNull
    @JsonProperty("userId")
    private Long userId;

    @NotBlank
    @JsonProperty("sourceNumber")
    private String sourceNumber;

    @NotBlank
    @JsonProperty("sourceType")
    private String sourceType;

    @NotBlank
    @JsonProperty("destinationNumber")
    private String destinationNumber;

    @NotBlank
    @JsonProperty("destinationType")
    private String destinationType;

    @NotNull
    @JsonProperty("amount")
    private Long amount;

    @NotBlank
    @JsonProperty("currencyName")
    private String currencyName;
}
