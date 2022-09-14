package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "dto for card product")
public class CardProductCreateDto {

    @NotNull
    @JsonProperty("cashback")
    private int cashback;

    @JsonProperty("price")
    private double price;

    @NotBlank
    @JsonProperty("advantages")
    @Size(
            min = 5,
            max = 255,
            message = "advantages should contain at least 3 symbols and not more than 255"
    )
    private String advantages;

    @NotBlank
    @JsonProperty("bank_partners")
    @Size(
            min = 5,
            max = 255,
            message = "bank_partners should contain at least 3 symbols and not more than 255"
    )
    private String bankPartners;

    @NotBlank
    @JsonProperty("loyalty_program")
    @Size(
            min = 5,
            max = 255,
            message = "loyalty_program should contain at least 3 symbols and not more than 255"
    )
    private String loyaltyProgram;
}
