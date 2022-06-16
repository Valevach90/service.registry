package com.andersen.banking.service.payment.meeting_impl.mapping;

import com.andersen.banking.service.payment.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CardRegistrationDtoMapperImpl.class)
public class CardRegistrationDtoMapperTest {

  @Autowired
  CardRegistrationDtoMapper mapper;

  @Test
  void toCard_ShouldReturnCard_WhenCardRegistrationDtoIsCorrect() {
    Assertions.assertEquals(populateCard(), mapper.toCard(populateCardRegistrationDto()));
  }

  private Card populateCard() {
    Account account = new Account();
    account.setId(5L);

    Card card = new Card();
    card.setAccount(account);
    card.setCardNumber("1234567890123456");
    card.setPinCode("1234");
    card.setExpirationDate(LocalDate.parse("2099-01-01"));
    card.setHolderName("Ivanov Ivan Ivanovich");
    return card;
  }

  private CardRegistrationDto populateCardRegistrationDto() {
    CardRegistrationDto cardDto = new CardRegistrationDto();
    cardDto.setAccountId(5L);
    cardDto.setCardNumber("1234567890123456");
    cardDto.setPinCode("1234");
    cardDto.setExpirationDate(LocalDate.parse("2099-01-01"));
    cardDto.setHolderName("Ivanov Ivan Ivanovich");
    return cardDto;
  }
}
