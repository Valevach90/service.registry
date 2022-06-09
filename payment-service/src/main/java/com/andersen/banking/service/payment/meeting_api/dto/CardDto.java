package com.andersen.banking.service.payment.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class presents an entity, which is available via CardController endpoints.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

  @NotNull
  @JsonProperty("id")
  private Long id;

  @NotNull
  @JsonProperty("account_id")
  private Long accountId;

  @NotNull
  @Size(min = 16, max = 16)

  @JsonProperty("card_number")
  private String cardNumber;

  @NotNull
  @JsonProperty("exp_date")
  private LocalDate expirationDate;

  @NotNull
  @Size(min = 4, max = 4)
  @JsonProperty("pin_code")
  private String pinCode;

  @NotNull
  @Size(min = 6, max = 255)
  @JsonProperty("holder_name")
  private String holderName;
}
