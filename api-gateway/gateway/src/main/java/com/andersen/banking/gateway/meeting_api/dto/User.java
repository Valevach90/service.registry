package com.andersen.banking.gateway.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class User {

    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("currency_list")
    private List<Currency> currency;
}
