package com.andersen.banking.meeting_impl.sceduler;

import com.andersen.banking.meeting_impl.service.RegularPaymentService;
import com.andersen.banking.meeting_impl.service.impl.RegularPaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RegularPaymentScheduler {

    private final RegularPaymentServiceImpl regularPaymentService;

    @Scheduled(cron = "${scheduler.regular}")
    public void executeRegularPayments() {
        log.info("RegularPaymentScheduler started working");

        regularPaymentService.startRegularSchedulerWork();

        log.info("RegularPaymentScheduler ended working");
    }
}
