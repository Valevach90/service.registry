package com.andersen.banking.service.payment.meeting_impl.mapping;

import com.andersen.banking.service.payment.meeting_api.dto.CardDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CardMapperImpl.class)
public class CardMapperTest {

  @Autowired
  CardMapper cardMapper;

  @Test
  void toCard_ShouldReturnCardDto_WhenCardIsCorrect() {
    Assertions.assertEquals(populateCard(), cardMapper.toCard(populateCardDto()));
  }

  @Test
  void toCardDto_ShouldReturnCard_WhenCardDtoIsCorrect() {
    Assertions.assertEquals(populateCardDto(), cardMapper.toCardDto(populateCard()));
  }

  private Card populateCard() {
    Account account = new Account();
    account.setId(5L);

    Card card = new Card();
    card.setId(17L);
    card.setAccount(account);
    card.setCardNumber("1234567890123456");
    card.setPinCode("1234");
    card.setExpirationDate(LocalDate.parse("2099-01-01"));
    card.setHolderName("Ivanov Ivan Ivanovich");
    return card;
  }

  private CardDto populateCardDto() {
    CardDto cardDto = new CardDto();
    cardDto.setId(17L);
    cardDto.setAccountId(5L);
    cardDto.setCardNumber("1234567890123456");
    cardDto.setPinCode("1234");
    cardDto.setExpirationDate(LocalDate.parse("2099-01-01"));
    cardDto.setHolderName("Ivanov Ivan Ivanovich");
    return cardDto;
  }
}
