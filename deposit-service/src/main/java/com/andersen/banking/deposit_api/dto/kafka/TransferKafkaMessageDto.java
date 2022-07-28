package com.andersen.banking.deposit_api.dto.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferKafkaMessageDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("userId")
    private Long userId;

    @NotEmpty
    @JsonProperty("sourceNumber")
    private String sourceNumber;

    @NotEmpty
    @JsonProperty("sourceType")
    private String sourceType;

    @NotEmpty
    @JsonProperty("destinationNumber")
    private String destinationNumber;

    @NotEmpty
    @JsonProperty("destinationType")
    private String destinationType;

    @NotNull
    @JsonProperty("amount")
    private Long amount;

    @NotBlank
    @JsonProperty("currencyName")
    private String currencyName;

    @NotBlank
    @JsonProperty("statusName")
    private String statusName;

    @NotBlank
    @JsonProperty("statusDescription")
    private String statusDescription;
}
