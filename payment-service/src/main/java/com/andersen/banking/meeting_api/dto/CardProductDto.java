package com.andersen.banking.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for card product")
public class CardProductDto {

    private UUID id;

    private int maxCashback;

    private double price;

    private String advantages;

    private String bankPartners;

    private String loyaltyProgram;
}
