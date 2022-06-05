package com.andersen.banking.service.payment.meeting_impl.service;

import com.andersen.banking.service.payment.meeting_db.entities.Account;

public interface AccountService {

  /**
   * Return Account by id.
   *
   * @param id of the account
   * @return Account
   */
  Account findById(Long id);
}
