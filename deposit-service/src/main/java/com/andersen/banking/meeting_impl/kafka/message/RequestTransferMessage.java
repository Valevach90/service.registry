package com.andersen.banking.meeting_impl.kafka.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class RequestTransferMessage {

    @NotNull
    @JsonProperty("transferId")
    private UUID transferId;

    @NotNull
    @JsonProperty("userId")
    private UUID userId;

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

    @NotNull
    @JsonProperty("status")
    private Integer status;

    @NotBlank
    @JsonProperty("currencyName")
    private String currencyName;

}
