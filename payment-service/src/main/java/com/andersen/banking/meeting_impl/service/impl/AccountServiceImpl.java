package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.repository.AccountRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.AccountService;
import com.andersen.banking.meeting_impl.util.AccountNumberGenerator;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountService implementation
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    @Transactional
    public Account create(Account account) {
        log.info("Creating account: {}", account);

        account.setAccountNumber(AccountNumberGenerator.generateAccountNumber(account.getBankName(),
                account.getCurrency(), account.getOwnerId()));
        Account savedAccount = accountRepository.save(account);

        log.info("Created account: {}", savedAccount);
        return savedAccount;
    }

    @Override
    public Account findById(UUID id) {
        log.debug("Finding account by id: {}", id);

        Account account =
                accountRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException(Account.class, id));

        log.debug("Account with id {} was successfully found", id);
        return account;
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        log.info("Find all accounts for pageable: {}", pageable);

        Page<Account> accounts = accountRepository.findAll(pageable);

        log.info("Found {} accounts", accounts.getContent().size());
        return accounts;
    }

    @Override
    public Page<Account> findByOwnerId(UUID id, Pageable pageable) {
        log.info("Trying to find accounts with ownerId: {}", id);

        Page<Account> accounts = accountRepository.findAccountByOwnerId(id, pageable);

        log.info("Found {} accounts with ownerId {}", accounts.getContent().size(), id);
        return accounts;
    }

    @Override
    @Transactional
    public Account update(Account account) {
        log.debug("Trying to update account: {}", account);

        findById(account.getId());
        Account updateAccount = accountRepository.save(account);

        log.debug("Return updated account: {}", updateAccount);
        return updateAccount;
    }

    @Override
    @Transactional
    public Account deleteById(UUID id) {
        log.info("Trying to delete account with id: {}", id);

        Account deletedAccount = findById(id);
        accountRepository.deleteById(id);

        log.info("Deleted account with id: {}", id);
        return deletedAccount;
    }

    @Override
    @Transactional
    public boolean changeAccountBalance(UUID accountId, long amount) {
        log.info("Try to {} amount for account with id: {}",
                amount >= 0 ? "subtract" : "add", accountId);
        try {
            lock.lock();
            Account account = findById(accountId);
            long balanceAfter = account.getBalance() - amount;

            if (balanceAfter >= 0L) {

                account.setBalance(balanceAfter);

                accountRepository.save(account);

                log.info("Change amount in: {} successfully completed.",
                        account.getAccountNumber());

                return true;
            } else {
                log.info(
                        "Change amount in: {} not completed. Have not enough money on source account.",
                        account.getAccountNumber());
                return false;
            }
        } finally {
            lock.unlock();
        }
    }
}
