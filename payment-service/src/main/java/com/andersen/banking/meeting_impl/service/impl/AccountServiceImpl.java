package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.AccountChangesResponseDto;
import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.AccountRevision;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.repository.AccountRepository;
import com.andersen.banking.meeting_db.repository.CardRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.AccountService;
import com.andersen.banking.meeting_impl.util.AccountNumberGenerator;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionMetadata;
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

    private final CardRepository cardRepository;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    @Transactional
    public Account create(Account account) {
        log.info("Creating account: {}", account);

        setUpAdditionalAccountInfo(account);
        Account savedAccount = accountRepository.save(account);

        log.info("Created account: {}", savedAccount);
        return savedAccount;
    }

    private void setUpAdditionalAccountInfo(Account account) {
        account.setAccountNumber(AccountNumberGenerator.generateAccountNumber(account.getBankName(),
                account.getCurrency(), account.getOwnerId()));
        account.setOpenDate(LocalDate.now());
        account.setActive(true);
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

        Account savedAccount = findById(account.getId());
        account.setAccountNumber(savedAccount.getAccountNumber());
        Account updateAccount = accountRepository.save(account);

        log.debug("Return updated account: {}", updateAccount);
        return updateAccount;
    }

    @Override
    @Transactional
    public boolean deactivateSomeExpiredAccounts() {
        List<Account> accountsToDeactivate = accountRepository.findAccountsToDeactivate();

        if (!accountsToDeactivate.isEmpty()) {
            accountsToDeactivate.forEach(account -> {
                account.setActive(false);
                deactivateAllCardsByAccountId(account.getId());
            });

            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public Account deactivateById(UUID id) {
        log.info("Trying to deactivate account with id: {}", id);

        Account accountToDeactivate = findById(id);
        accountToDeactivate.setActive(false);

        deactivateAllCardsByAccountId(id);
        accountRepository.save(accountToDeactivate);

        log.info("Deactivated account with id: {}", id);
        return accountToDeactivate;
    }

    private void deactivateAllCardsByAccountId(UUID accountId) {
        List<Card> cardsToDeactivate = cardRepository.findAllByAccount_Id(accountId);
        cardsToDeactivate.forEach(card -> {
            card.setIsActive(false);
            log.info("Card with id: {} was successfully deactivated", card.getId());
        });
        cardRepository.saveAll(cardsToDeactivate);
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

    @Override
    public List<AccountChangesResponseDto> changes(UUID id) {
        log.info("Get all changes for account with id: {}", id);
        return accountRepository.findRevisions(id)
                .get()
                .toList().stream()
                .map(this::getResponse)
                .toList();
    }

    private AccountChangesResponseDto getResponse(Revision<Integer, Account> revision) {
        Account entity = revision.getEntity();
        RevisionMetadata<Integer> metadata = revision.getMetadata();
        AccountRevision delegate = metadata.getDelegate();
        return AccountChangesResponseDto.builder()
                .id(entity.getId())
                .ownerId(entity.getOwnerId())
                .openDate(entity.getOpenDate())
                .closeDate(entity.getCloseDate())
                .bankName(entity.getBankName())
                .balance(entity.getBalance())
                .currency(entity.getCurrency())
                .revisionType(metadata.getRevisionType().toString())
                .timestamp(delegate.getTimestamp())
                .build();
    }
}
