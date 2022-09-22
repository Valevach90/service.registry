package com.andersen.banking.meeting_impl.sceduler;

import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.repository.CardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeactivationScheduler {

    private final CardRepository cardRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(cron = "${scheduler.card}")
    public void deactivateExpiredCards(){
        List<Card> cardsToDeactivate = cardRepository.findCardsToDeactivate();
        if (!cardsToDeactivate.isEmpty()) {
            deactivateListOfCards(cardsToDeactivate);
            deactivateExpiredCards();
        } else {
            log.info("All cards with expired duration were deactivated");
        }
    }

    private void deactivateListOfCards(List<Card> cardsToDeactivate){
        cardsToDeactivate.forEach(card -> {
            card.setActive(false);
            log.info("Card with id: {} duration has expired. It was successfully deactivated", card.getId());
        });
        cardRepository.saveAll(cardsToDeactivate);
    }
}
