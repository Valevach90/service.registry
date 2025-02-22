package com.andersen.banking.meeting_api.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @JsonProperty("id")
    private UUID id;

    @Min(1L)
    @JsonProperty("owner_id")
    private UUID ownerId;

    @NotBlank
    @JsonProperty("currency")
    @Size(min = 3, max = 3, message = "currency should have exactly 3 characters")
    private String currency;

    @NotNull
    @JsonProperty("balance")
    private double balance;

}
