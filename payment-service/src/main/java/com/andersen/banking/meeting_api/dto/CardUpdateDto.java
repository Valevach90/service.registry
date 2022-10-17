package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.util.OpenApiConstants.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** This class presents an entity, which is available via CardController endpoints. */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardUpdateDto {

    @JsonProperty("id")
    @NotNull
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    private UUID id;

    @NotNull
    @JsonProperty("account_id")
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    private UUID accountId;

    @NotNull
    @JsonProperty("card_product_id")
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    private UUID cardProductId;

    @NotBlank
    @JsonProperty("first_twelve_numbers")
    @Pattern(
            regexp = "[0-9]{12}",
            message = "first_twelve_numbers should contain exactly 12 digits")
    @Schema(example = EXAMPLE_HASH_FIRST_NUMBER_CARD, defaultValue = EXAMPLE_HASH_FIRST_NUMBER_CARD)
    private String firstTwelveNumbers;

    @NotBlank
    @JsonProperty("last_four_numbers")
    @Pattern(regexp = "[0-9]{4}", message = "last_four_numbers should contain exactly 4 digits")
    @Schema(example = EXAMPLE_LAST_NUMBER_CARD, defaultValue = EXAMPLE_LAST_NUMBER_CARD)
    private String lastFourNumbers;

    @NotNull
    @JsonProperty("valid_from_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validFromDate;

    @NotNull
    @JsonProperty("expire_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate;

    @NotBlank
    @Pattern(
            regexp = "[a-zA-Z- ]{3,30}",
            message =
                    "holder_name should have at least 3 and at maximum 30 characters")
    @JsonProperty("holder_name")
    @Schema(example = EXAMPLE_NAME, defaultValue = EXAMPLE_NAME)
    private String holderName;

    @JsonProperty("is_active")
    private boolean isActive;
}
