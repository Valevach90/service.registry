package com.andersen.banking.meeting_impl.sceduler;

import com.andersen.banking.meeting_impl.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CardDeactivationScheduler {

    private final CardService cardService;

    @Scheduled(cron = "${scheduler.card}")
    public void deactivateExpiredCards() {
        log.info("CardDeactivationScheduler started working");

        boolean cardsWereDeactivated = cardService.deactivateSomeExpiredCards();

        while (cardsWereDeactivated) {
            cardsWereDeactivated = cardService.deactivateSomeExpiredCards();
        }

        log.info("CardDeactivationScheduler ended working");
    }

}
