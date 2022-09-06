package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * This class presents an entity, which is available via TypeCardController endpoints.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class TypeCardUpdateDto {

    @NotNull
    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("payment_system")
    private String paymentSystem;

    @NotNull
    @JsonProperty("type_name")
    private String typeName;
}
