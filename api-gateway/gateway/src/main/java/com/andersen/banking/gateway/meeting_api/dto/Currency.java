package com.andersen.banking.gateway.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class Currency {

        @JsonProperty("currency_name")
        private String name;

        @JsonProperty("balance")
        private double balance;
}
