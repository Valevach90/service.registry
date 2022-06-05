package com.andersen.banking.service.payment.meeting_impl.service.impl;

import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.repositories.AccountRepository;
import com.andersen.banking.service.payment.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Override
  @Transactional(readOnly = true)
  public Account findById(Long id) {
    log.debug("Finding account by id: {}", id);

    Account account = accountRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Account.class, id));

    log.debug("Account with id {} was successfully found", id);
    return account;

  }
}
