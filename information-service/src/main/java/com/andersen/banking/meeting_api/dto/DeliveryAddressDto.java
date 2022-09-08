package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


/**
 * Dto for delivery address.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddressDto {

    @JsonProperty("id")
    private UUID id;

    @NotBlank
    @JsonProperty("city")
    private String city;

    @NotBlank
    @JsonProperty("street")
    private String street;

    @NotBlank
    @JsonProperty("house")
    private String house;

    @JsonProperty("building")
    private String building;

    @JsonProperty("flat")
    private String flat;
}
