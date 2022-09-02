package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.TypeCard;
import com.andersen.banking.meeting_impl.mapper.CardMapper;
import com.andersen.banking.meeting_impl.mapper.CardMapperImpl;
import com.andersen.banking.meeting_test.generators.CardUnitTestGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = CardMapperImpl.class)
class CardMapperTest {

    @Autowired
    CardMapper cardMapper;

    @Test
    void toCard_ShouldReturnCard_WhenCardRegistrationIsCorrect() {
        Card card = CardUnitTestGenerator.populateCard();
        card.setId(null);
        card.getTypeCard().setId(null);
        assertEquals(card,
                cardMapper.toCard(CardUnitTestGenerator.populateCardRegistrationDto()));
    }

    @Test
    void toCard_ShouldReturnCard_WhenCardUpdateDtoIsCorrect() {
        Card card = CardUnitTestGenerator.populateCard();
        card.getTypeCard().setId(null);
        assertEquals(card,
                cardMapper.toCard(CardUnitTestGenerator.populateCardUpdateDto()));
    }

    @Test
    void toCardDto_ShouldReturnCardDto_WhenCardIsCorrect() {
        assertEquals(CardUnitTestGenerator.populateCardResponseDto(),
                cardMapper.toCardResponseDto(CardUnitTestGenerator.populateCardIncludeAccountWithBalanceAndCurrency()));
    }

    @Test
    void convertCardTypeToDto() {
        Card card = CardUnitTestGenerator.populateCard();
        TypeCard typeCard = card.getTypeCard();
        typeCard.setId(null);
        assertEquals(typeCard,
                cardMapper.toCard(CardUnitTestGenerator.populateCardUpdateDto()).getTypeCard());
        assertEquals(typeCard,
                cardMapper.toCard(CardUnitTestGenerator.populateCardRegistrationDto()).getTypeCard());
    }

    @Test
    void convertDtoToCard() {
        Card card = CardUnitTestGenerator.populateCard();
        TypeCard typeCard = card.getTypeCard();
        typeCard.setId(null);
        assertEquals(card,
                cardMapper.toCard(CardUnitTestGenerator.populateCardUpdateDto()));
    }

    @Test
    void toCardCredResponseDto_ShouldReturnCardCredResponseDto_OnCard() {
        Card card = CardUnitTestGenerator.populateCard();
        CardCredResponseDto credResponseDto = cardMapper.toCardCredResponseDto(card);

        assertNotNull(credResponseDto);
        assertEquals(card.getFirstTwelveNumbers(), credResponseDto.getFirstTwelveNumbersHash());
        assertEquals(card.getLastFourNumbers(), credResponseDto.getLastFourNumbers());
        assertEquals(card.getHolderName(), credResponseDto.getHolderName());
        assertEquals(card.getTypeCard().getPaymentSystem(), credResponseDto.getPaymentSystem());
    }
}
