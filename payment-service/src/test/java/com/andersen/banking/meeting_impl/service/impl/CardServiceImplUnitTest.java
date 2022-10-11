package com.andersen.banking.meeting_impl.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.repository.CardRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.util.Crypter;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CardServiceImplUnitTest {

    static final String TWELVE_NUMS = "123456789012";
    static final String FOUR_NUMS = "4567";
    static final String TWELVE_NUMS_HASH = Crypter.getCrypt(TWELVE_NUMS);

    @Mock Card card;

    @Mock CardRepository cardRepository;

    @InjectMocks CardServiceImpl cardService;

    @Test
    void findByNums_ShouldReturnCard_WhenCardRepositoryReturnedCardIsPresent() {
        final Optional<Card> optionalCard = Optional.of(card);
        when(cardRepository.findByFirstTwelveNumbersAndLastFourNumbers(TWELVE_NUMS_HASH, FOUR_NUMS))
                .thenReturn(optionalCard);

        Card actual = cardService.findByNotHashedNums(TWELVE_NUMS, FOUR_NUMS);

        assertNotNull(actual);
        assertEquals(card.getFirstTwelveNumbers(), actual.getFirstTwelveNumbers());
    }

    @Test
    void findByNums_ShouldThrowNewNotFoundException_WhenCardRepositoryReturnedCardIsNotPresent() {

        when(cardRepository.findByFirstTwelveNumbersAndLastFourNumbers(TWELVE_NUMS_HASH, FOUR_NUMS))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> cardService.findByNotHashedNums(TWELVE_NUMS, FOUR_NUMS));
    }
}
