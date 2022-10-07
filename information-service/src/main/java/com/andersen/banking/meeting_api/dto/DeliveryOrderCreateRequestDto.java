package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Dto for delivery order creation request.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrderCreateRequestDto {

    @NotNull
    @JsonProperty("deliveryType")
    private DeliveryTypeDto deliveryType;

    @NotNull
    @JsonProperty("deliveryAddress")
    private DeliveryAddressDto deliveryAddress;

    @NotNull
    @JsonProperty("userId")
    private UUID userId;

    @NotNull
    @JsonProperty("cardId")
    private UUID cardId;
}
