package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.util.OpenApiConstants.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class CardRegistrationDto {

    @NotNull
    @JsonProperty("account_id")
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    private UUID accountId;

    @NotNull
    @JsonProperty("card_product_id")
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    private UUID cardProductId;

    @NotBlank
    @Schema(defaultValue = EXAMPLE_NAME, example = EXAMPLE_NAME)
    @Pattern(
            regexp = "[a-zA-Z- ]{3,30}",
            message =
                    "message = \"holder_name should have at least 3 and at maximum 30 characters\"")
    @JsonProperty("holder_name")
    private String holderName;
}
