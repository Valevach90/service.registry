package com.andersen.banking.service.payment.meeting_impl.mapping;

import com.andersen.banking.service.payment.meeting_api.dto.CardDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_impl.mapper.CardMapper;
import com.andersen.banking.service.payment.meeting_impl.mapper.CardMapperImpl;
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
    card.setFirstTwelveNumbers("123456789012");
    card.setLastFourNumbers("4567");
    card.setValidFromDate(LocalDate.of(2021, 10, 23));
    card.setExpireDate(LocalDate.of(2024, 10, 23));
    card.setHolderName("Ivanov Ivan Ivanovich");
    return card;
  }

  private CardDto populateCardDto() {
    CardDto cardDto = new CardDto();
    cardDto.setId(17L);
    cardDto.setAccountId(5L);
    cardDto.setFirstTwelveNumbers("123456789012");
    cardDto.setLastFourNumbers("4567");
    cardDto.setValidFromDate(LocalDate.of(2021, 10, 23));
    cardDto.setExpireDate(LocalDate.of(2024, 10, 23));
    cardDto.setHolderName("Ivanov Ivan Ivanovich");
    return cardDto;
  }
}
