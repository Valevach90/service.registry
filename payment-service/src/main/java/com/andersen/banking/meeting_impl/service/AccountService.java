package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.Account;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    /**
     * This method registers new Account.
     *
     * @param account
     * @return Account
     */
    Account create(Account account);

    /**
     * This method returns page of Account entities.
     *
     * @param pageable
     * @return
     */
    Page<Account> findAll(Pageable pageable);

    /**
     * Return accounts by ownerId
     *
     * @param id
     * @param pageable
     * @return
     */
    Page<Account> findByOwnerId(UUID id, Pageable pageable);

    /**
     * Return Account by id.
     *
     * @param id of the account
     * @return Account
     */
    Account findById(UUID id);

    /**
     * Update accounts.
     *
     * @param updateAccount update account
     * @return Account
     */
    Account update(Account updateAccount);

    /**
     * This method deletes the Account with the given id.
     *
     * @param id
     * @return Account
     */
    Account deleteById(UUID id);

    /**
     * This method changes the account balance value. If the balance is positive,
     * then it subtracts this value from the account, if it is negative, then it adds.
     *
     * @param accountId
     * @param amount
     * @return true or false
     */
    boolean changeAccountBalance(UUID accountId, long amount);
}
