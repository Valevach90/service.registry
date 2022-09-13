package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_NAME;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_PAYMENT_SYSTEM;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_TYPECARD;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardRegistrationDto {

    @NotNull
    @JsonProperty("account_id")
    private UUID accountId;

    @NotBlank
    @JsonProperty("first_twelve_numbers")
    @Pattern(
            regexp = "[0-9]{12}",
            message = "first_twelve_numbers should contain exactly 12 digits")
    private String firstTwelveNumbers;

    @NotBlank
    @JsonProperty("last_four_numbers")
    @Pattern(regexp = "[0-9]{4}", message = "last_four_numbers should contain exactly 4 digits")
    private String lastFourNumbers;

    @NotNull
    @JsonProperty("valid_from_date")
    private LocalDate validFromDate;

    @NotNull
    @JsonProperty("expire_date")
    private LocalDate expireDate;

    @NotBlank
    @Schema(defaultValue = EXAMPLE_NAME, example = EXAMPLE_NAME)
    @Pattern(
            regexp = "[a-zA-Z- ]{3,30}",
            message =
                    "message = \"holder_name should have at least 3 and at maximum 30 characters\"")
    @JsonProperty("holder_name")
    private String holderName;

    @NotNull
    @Schema(defaultValue = EXAMPLE_PAYMENT_SYSTEM, example = EXAMPLE_PAYMENT_SYSTEM)
    @JsonProperty("payment_system")
    private String paymentSystem;

    @NotNull
    @JsonProperty("type_name")
    @Schema(defaultValue = EXAMPLE_TYPECARD, example = EXAMPLE_TYPECARD)
    private String typeName;
}
