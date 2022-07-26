package com.andersen.banking.service.payment.meeting_impl.mapping;

import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_impl.mapper.CardMapper;
import com.andersen.banking.service.payment.meeting_impl.mapper.CardMapperImpl;
import com.andersen.banking.service.payment.meeting_test.generators.CardUnitTestGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CardMapperImpl.class)
public class CardMapperTest {

  @Autowired
  CardMapper cardMapper;

  @Test
  void toCard_ShouldReturnCard_WhenCardRegistrationIsCorrect() {
    Card card = CardUnitTestGenerator.populateCard();
    card.setId(null);
    card.getTypeCard().setId(null);
    Assertions.assertEquals(card,
        cardMapper.toCard(CardUnitTestGenerator.populateCardRegistrationDto()));
  }

  @Test
  void toCard_ShouldReturnCard_WhenCardUpdateDtoIsCorrect() {
    Card card = CardUnitTestGenerator.populateCard();
    card.getTypeCard().setId(null);

    Assertions.assertEquals(card,
            cardMapper.toCard(CardUnitTestGenerator.populateCardUpdateDto()));
  }

  @Test
  void toCardDto_ShouldReturnCard_WhenCardDtoIsCorrect() {
    Assertions.assertEquals(CardUnitTestGenerator.populateCardResponseDto(),
        cardMapper.toCardResponseDto(CardUnitTestGenerator.populateCard()));
  }
}
