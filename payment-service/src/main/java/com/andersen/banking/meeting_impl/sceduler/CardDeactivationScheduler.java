package com.andersen.banking.meeting_impl.sceduler;

import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.repository.CardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class CardDeactivationScheduler {

    private final CardRepository cardRepository;

    @Transactional
    @Scheduled(cron = "${scheduler.card}")
    public void deactivateExpiredCards() {
        log.info("CardDeactivationScheduler started working");
        List<Card> cardsToDeactivate = cardRepository.findCardsToDeactivate();

        while (!cardsToDeactivate.isEmpty()) {
            deactivateListOfCards(cardsToDeactivate);
            cardsToDeactivate = cardRepository.findCardsToDeactivate();
        }
        log.info("CardDeactivationScheduler ended working");
    }

    private void deactivateListOfCards(List<Card> cardsToDeactivate){
        cardsToDeactivate.forEach(card -> {
            card.setActive(false);
            log.info("Card with id: {} duration has expired. It was successfully deactivated", card.getId());
        });
        cardRepository.saveAll(cardsToDeactivate);
    }
}
