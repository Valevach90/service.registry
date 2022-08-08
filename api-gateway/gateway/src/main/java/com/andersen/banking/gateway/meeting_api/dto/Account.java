package com.andersen.banking.gateway.meeting_api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @JsonProperty("id")
    private Long id;

    @Min(1L)
    @JsonProperty("owner_id")
    private Long ownerId;

    @NotBlank
    @JsonProperty("currency")
    @Size(min = 3, max = 3, message = "currency should have exactly 3 characters")
    private String currency;

    @NotNull
    @JsonProperty("balance")
    private double balance;

}
