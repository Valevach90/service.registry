package com.andersen.banking.meeting_impl.sceduler;

import com.andersen.banking.meeting_impl.service.RegularPaymentService;
import com.andersen.banking.meeting_impl.service.impl.RegularPaymentServiceImpl;
import com.andersen.banking.meeting_impl.util.TransferMapsContainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Slf4j
@RequiredArgsConstructor
@Component
public class RegularPaymentScheduler {

    @Inject
    private final RegularPaymentService regularPaymentService;

    private final TransferMapsContainer transferMapsContainer;

    @Scheduled(cron = "${scheduler.regular}")
    public void executeRegularPayments() {
        log.info("RegularPaymentScheduler started working");
        transferMapsContainer.setUpMaps();

        boolean paymentsWereDone = regularPaymentService.executeSomeAmountOfRegularPayments();

        while (paymentsWereDone) {
            paymentsWereDone = regularPaymentService.executeSomeAmountOfRegularPayments();
        }

        log.info("RegularPaymentScheduler ended working");
    }
}
