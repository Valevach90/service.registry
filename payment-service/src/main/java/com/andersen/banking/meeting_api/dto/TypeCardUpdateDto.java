package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.util.OpenApiConstants.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/** This class presents an entity, which is available via TypeCardController endpoints. */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class TypeCardUpdateDto {

    @NotNull
    @JsonProperty("id")
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    private UUID id;

    @NotNull
    @JsonProperty("payment_system")
    @Schema(example = EXAMPLE_PAYMENT_SYSTEM, defaultValue = EXAMPLE_PAYMENT_SYSTEM)
    private String paymentSystem;

    @NotNull
    @JsonProperty("type_name")
    @Schema(example = EXAMPLE_TYPENAME, defaultValue = EXAMPLE_TYPENAME)
    private String typeName;
}
