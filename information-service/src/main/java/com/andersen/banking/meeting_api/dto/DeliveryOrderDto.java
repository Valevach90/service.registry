package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Dto for delivery order.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrderDto {

    @NotNull
    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("delivery_type")
    private DeliveryTypeDto deliveryType;

    @NotNull
    @JsonProperty("delivery_address")
    private DeliveryAddressDto deliveryAddress;

    @NotNull
    @JsonProperty("user_id")
    private UUID userId;

    @NotNull
    @JsonProperty("card_id")
    private UUID cardId;

    @NotNull
    @JsonProperty("opening_time")
    private Timestamp openingTime;

    @JsonProperty("lead_time")
    private Timestamp leadTime;

    @NotNull
    @JsonProperty("is_delivered")
    private Boolean isDelivered;

    @JsonProperty("status_description")
    private String statusDescription;
}
