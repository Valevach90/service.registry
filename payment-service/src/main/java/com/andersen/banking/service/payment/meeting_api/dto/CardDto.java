package com.andersen.banking.service.payment.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  @JsonProperty("card_number")
  private String cardNumber;

  @NotNull
  @JsonProperty("exp_date")
  private LocalDate expirationDate;

  @NotNull
  @JsonProperty("pin_code")
  private String pinCode;

  @NotNull
  @JsonProperty("holder_name")
  private String holderName;
}
