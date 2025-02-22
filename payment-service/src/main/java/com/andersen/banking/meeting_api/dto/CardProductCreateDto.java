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

import static com.andersen.banking.meeting_api.util.OpenApiConstants.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "dto for card product")
public class CardProductCreateDto {

    @NotNull
    @JsonProperty("cashback")
    @Schema(defaultValue = EXAMPLE_CASHBACK, example = EXAMPLE_CASHBACK)
    private Integer cashback;

    @JsonProperty("price")
    @Schema(defaultValue = EXAMPLE_PRICE, example = EXAMPLE_PRICE)
    private Long price;

    @NotBlank
    @JsonProperty("advantages")
    @Size(
            min = 5,
            max = 255,
            message = "advantages should contain at least 3 symbols and not more than 255"
    )
    @Schema(example = EXAMPLE_DESCRIPTION, defaultValue = EXAMPLE_DESCRIPTION)
    private String advantages;

    @NotBlank
    @JsonProperty("bank_partners")
    @Size(
            min = 5,
            max = 255,
            message = "bank_partners should contain at least 3 symbols and not more than 255"
    )
    @Schema(example = EXAMPLE_DESCRIPTION, defaultValue = EXAMPLE_DESCRIPTION)
    private String bankPartners;

    @NotBlank
    @JsonProperty("loyalty_program")
    @Size(
            min = 5,
            max = 255,
            message = "loyalty_program should contain at least 3 symbols and not more than 255"
    )
    @Schema(example = EXAMPLE_DESCRIPTION, defaultValue = EXAMPLE_DESCRIPTION)
    private String loyaltyProgram;

    @NotBlank
    @JsonProperty("bank_partners_mini")
    @Size(
            min = 3,
            max = 40,
            message = "bank_partners_mini should contain at least 3 symbols and not more than 40"
    )
    @Schema(example = EXAMPLE_DESCRIPTION_MINI, defaultValue = EXAMPLE_DESCRIPTION_MINI)
    private String bankPartnersMini;

    @NotBlank
    @JsonProperty("loyalty_program_mini")
    @Size(
            min = 3,
            max = 40,
            message = "loyalty_program_mini should contain at least 3 symbols and not more than 40"
    )
    @Schema(example = EXAMPLE_DESCRIPTION_MINI, defaultValue = EXAMPLE_DESCRIPTION_MINI)
    private String loyaltyProgramMini;

    @NotBlank
    @JsonProperty("payment_system")
    @Size(
            min = 3,
            max = 20,
            message = "payment_system should contain at least 3 symbols and not more than 20"
    )
    @Schema(defaultValue = EXAMPLE_PAYMENT_SYSTEM, example = EXAMPLE_PAYMENT_SYSTEM)
    private String paymentSystem;

    @NotBlank
    @JsonProperty("type_name")
    @Size(
            min = 3,
            max = 20,
            message = "type_name should contain at least 3 symbols and not more than 20"
    )
    @Schema(defaultValue = EXAMPLE_TYPENAME, example = EXAMPLE_TYPENAME)
    private String typeName;
}
