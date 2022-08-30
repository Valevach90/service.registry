package com.andersen.banking.service.payment.meeting_impl.service.impl;

import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_db.repository.CardRepository;
import com.andersen.banking.service.payment.meeting_impl.exception.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.util.CryptWithSHA;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardServiceImplUnitTest {

  static final String TWELVE_NUMS = "123456789012";
  static final String FOUR_NUMS = "4567";
  static final String TWELVE_NUMS_HASH = CryptWithSHA.getCrypt(TWELVE_NUMS);

  @Mock Card card;

  @Mock CardRepository cardRepository;

  @InjectMocks CardServiceImpl cardService;

  @Test
  void findByNums_ShouldReturnCard_WhenCardRepositoryReturnedCardIsPresent() {
    final Optional<Card> optionalCard = Optional.of(card);
    when(cardRepository.findByFirstTwelveNumbersAndLastFourNumbers(TWELVE_NUMS_HASH, FOUR_NUMS))
        .thenReturn(optionalCard);

    Card actual = cardService.findByNums(TWELVE_NUMS, FOUR_NUMS);

    assertNotNull(actual);
    assertEquals(card.getFirstTwelveNumbers(), actual.getFirstTwelveNumbers());
  }

  @Test
  void findByNums_ShouldThrowNewNotFoundException_WhenCardRepositoryReturnedCardIsNotPresent() {

    when(cardRepository.findByFirstTwelveNumbersAndLastFourNumbers(TWELVE_NUMS_HASH, FOUR_NUMS))
        .thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> cardService.findByNums(TWELVE_NUMS, FOUR_NUMS));
  }
}
