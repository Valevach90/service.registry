package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDto {

    @NotNull
    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("account_id")
    private UUID accountId;

    @NotBlank
    @JsonProperty("last_four_numbers")
    @Pattern(regexp = "[0-9]{4}", message = "last_four_numbers should contain exactly 4 digits")
    private String lastFourNumbers;

    @NotBlank
    @JsonProperty("first_twelve_numbers_hash")
    private String firstTwelveNumbersHash;

    @NotNull
    @JsonProperty("valid_from_date")
    private LocalDate validFromDate;

    @NotNull
    @JsonProperty("expire_date")
    private LocalDate expireDate;

    @NotBlank
    @JsonProperty("holder_name")
    @Pattern(
            regexp = "[a-zA-Z- ]{3,30}",
            message = "holder_name should have at least 3 and at maximum 30 characters")
    private String holderName;

    @NotNull
    @JsonProperty("payment_system")
    private String paymentSystem;

    @NotNull
    @JsonProperty("type_name")
    private String typeName;

    @NotBlank
    @JsonProperty("balance")
    private long balance;

    @NotBlank
    @JsonProperty("currency")
    private String currency;
}
