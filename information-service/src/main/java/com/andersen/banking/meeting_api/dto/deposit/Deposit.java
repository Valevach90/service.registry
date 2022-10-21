package com.andersen.banking.meeting_api.dto.deposit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deposit {
    @JsonProperty("id")
    @NotNull(message = "Deposit id can't be null.")
    private UUID id;

    @JsonProperty("userId")
    @NotNull(message = "User id can't be null.")
    private UUID userId;

    @JsonProperty("amount")
    @NotNull(message = "Amount can't be null.")
    private Long amount;

    @JsonProperty("currency")
    @NotNull(message = "Currency can't be null.")
    private CurrencyDto currency;
}
