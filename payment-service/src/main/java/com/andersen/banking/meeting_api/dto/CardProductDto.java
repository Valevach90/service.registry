package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for card product")
public class CardProductDto {

    @NotNull
    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("cashback")
    private Integer cashback;

    @JsonProperty("price")
    private Double price;

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

    @NotBlank
    @JsonProperty("payment_system")
    @Size(
            min = 3,
            max = 20,
            message = "payment_system should contain at least 3 symbols and not more than 20"
    )
    private String paymentSystem;

    @NotBlank
    @JsonProperty("type_name")
    @Size(
            min = 3,
            max = 20,
            message = "type_name should contain at least 3 symbols and not more than 20"
    )
    private String typeName;
}
