package com.andersen.banking.gateway.meeting_api.dto.deposit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for currency")
public class CurrencyDto {

    @JsonProperty("id")
    @NotNull(message = "Currency id can't be null.")
    private Long id;

    @JsonProperty("name")
    @NotNull(message = "Currency name can't be null.")
    private String name;
}
