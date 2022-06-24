package com.andersen.banking.service.payment.meeting_test.generators;

import com.andersen.banking.service.payment.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.service.payment.meeting_api.dto.CardResponseDto;
import com.andersen.banking.service.payment.meeting_api.dto.CardUpdateDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardUnitTestGenerator {

  public static Card populateCard() {
    Card card = new Card();
    card.setId(17L);
    card.setAccount(populateAccount(new Account()));
    card.setValidFromDate(LocalDate.of(2021, 10, 23));
    card.setExpireDate(LocalDate.of(2024, 10, 23));
    card.setFirstTwelveNumbers("123456789012");
    card.setLastFourNumbers("4567");
    card.setHolderName("Ivanov Ivan Ivanovich");
    return card;
  }

  public static CardUpdateDto populateCardUpdateDto() {
    CardUpdateDto cardUpdateDto = new CardUpdateDto();
    cardUpdateDto.setId(17L);
    cardUpdateDto.setAccountId(5L);
    cardUpdateDto.setFirstTwelveNumbers("123456789012");
    cardUpdateDto.setLastFourNumbers("4567");
    cardUpdateDto.setValidFromDate(LocalDate.of(2021, 10, 23));
    cardUpdateDto.setExpireDate(LocalDate.of(2024, 10, 23));
    cardUpdateDto.setHolderName("Ivanov Ivan Ivanovich");
    return cardUpdateDto;
  }

  public static CardRegistrationDto populateCardRegistrationDto() {
    CardRegistrationDto cardRegistrationDto = new CardRegistrationDto();
    cardRegistrationDto.setAccountId(5L);
    cardRegistrationDto.setFirstTwelveNumbers("123456789012");
    cardRegistrationDto.setLastFourNumbers("4567");
    cardRegistrationDto.setValidFromDate(LocalDate.of(2021, 10, 23));
    cardRegistrationDto.setExpireDate(LocalDate.of(2024, 10, 23));
    cardRegistrationDto.setHolderName("Ivanov Ivan Ivanovich");
    return cardRegistrationDto;
  }

  public static CardResponseDto populateCardResponseDto() {
    CardResponseDto cardResponseDto = new CardResponseDto();
    cardResponseDto.setId(17L);
    cardResponseDto.setAccountId(5L);
    cardResponseDto.setLastFourNumbers("4567");
    cardResponseDto.setValidFromDate(LocalDate.of(2021, 10, 23));
    cardResponseDto.setExpireDate(LocalDate.of(2024, 10, 23));
    cardResponseDto.setHolderName("Ivanov Ivan Ivanovich");
    return cardResponseDto;
  }

  public static Account populateAccount(Account account) {
    account.setId(5L);
    return account;
  }


}
