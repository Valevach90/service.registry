package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private DeliveryCityDto city;

    @NotBlank
    @JsonProperty("street")
    private DeliveryStreetDto street;

    @NotBlank
    @JsonProperty("house")
    private String house;

    @JsonProperty("building")
    private String building;

    @JsonProperty("flat")
    private String flat;
}
