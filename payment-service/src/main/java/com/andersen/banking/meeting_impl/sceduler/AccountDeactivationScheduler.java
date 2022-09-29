package com.andersen.banking.meeting_impl.sceduler;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.repository.AccountRepository;
import com.andersen.banking.meeting_impl.service.AccountService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountDeactivationScheduler {

    private final AccountService accountService;

    @Transactional
    @Scheduled(cron = "${scheduler.card}")
    public void deactivateExpiredAccounts() {
        log.info("AccountDeactivationScheduler started working");

        boolean accountsWereDeactivated = accountService.deactivateSomeExpiredAccounts();

        while (accountsWereDeactivated) {
            accountsWereDeactivated = accountService.deactivateSomeExpiredAccounts();
        }

        log.info("AccountDeactivationScheduler ended working");
    }

}
