package com.andersen.banking.service.payment.meeting_impl.service.impl;

import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.repository.AccountRepository;
import com.andersen.banking.service.payment.meeting_impl.exception.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.service.AccountService;
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

    @Override
    @Transactional
    public Account create(Account account) {
        log.info("Creating account: {}", account);

        Account savedAccount = accountRepository.save(account);

        log.info("Created account: {}", savedAccount);
        return savedAccount;
    }

    @Override
    @Transactional(readOnly = true)
    public Account findById(Long id) {
        log.debug("Finding account by id: {}", id);

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Account.class, id));

        log.debug("Account with id {} was successfully found", id);
        return account;

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Account> findAll(Pageable pageable) {
        log.info("Find all accounts for pageable: {}", pageable);

        Page<Account> accounts = accountRepository.findAll(pageable);

        log.info("Found {} accounts", accounts.getContent().size());
        return accounts;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Account> findByOwnerId(Long id, Pageable pageable) {
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
    public Account deleteById(Long id) {
        log.info("Trying to delete account with id: {}", id);

        Account deletedAccount = findById(id);
        accountRepository.deleteById(id);

        log.info("Deleted account with id: {}", id);
        return deletedAccount;
    }
}
