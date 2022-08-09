package com.andersen.banking.gateway.meeting_api.dto.deposit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deposit {
    @JsonProperty("id")
    @NotNull(message = "Deposit id can't be null.")
    private Long id;

    @JsonProperty("userId")
    @NotNull(message = "User id can't be null.")
    private Long userId;

    @JsonProperty("amount")
    @NotNull(message = "Amount can't be null.")
    private Long amount;

    @JsonProperty("currency")
    @NotNull(message = "Currency can't be null.")
    private CurrencyDto currency;
}
