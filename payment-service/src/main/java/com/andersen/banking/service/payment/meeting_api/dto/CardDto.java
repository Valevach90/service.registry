package com.andersen.banking.service.payment.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * This class presents an entity, which is available via CardController endpoints.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    @JsonProperty("id")
    private Long id;

    @Min(1L)
    @NotNull
    @JsonProperty("account_id")
    private Long accountId;

    @NotBlank
    @JsonProperty("first_twelve_numbers")
    @Size(min = 12, max = 12, message = "first_twelve_numbers should have exactly 12 characters")
    private String firstTwelveNumbers;

    @NotBlank
    @JsonProperty("last_four_numbers")
    @Size(min = 4, max = 4, message = "last_four_numbers should have exactly 4 characters")
    private String lastFourNumbers;

    @NotNull
    @JsonProperty("valid_from_date")
    private Date validFromDate;

    @NotNull
    @JsonProperty("expire_date")
    private Date expireDate;

    @NotBlank
    @Size(min = 3, max = 30, message = "holder_name should have at least 3 and at maximum 30 characters")
    @JsonProperty("holder_name")
    private String holderName;
}
