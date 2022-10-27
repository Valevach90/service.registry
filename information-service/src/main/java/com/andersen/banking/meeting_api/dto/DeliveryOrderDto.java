package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @NotNull
    @JsonProperty("openingTime")
    private Timestamp openingTime;

    @JsonProperty("leadTime")
    private Timestamp leadTime;

    @NotNull
    @JsonProperty("isDelivered")
    private Boolean isDelivered;

    @JsonProperty("statusDescription")
    private String statusDescription;
}
