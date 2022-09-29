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
     * This method deactivates the Account with the given id.
     *
     * @param id
     * @return Account
     */
    Account deactivateById(UUID id);

    /**
     * This method deactivates some amount of accounts that were expired. Returns true if deactivated some cards.
     *
     * @param
     * @return
     */
    boolean deactivateSomeExpiredAccounts();

    /**
     * This method transfers money between an accounts with the given amount and currency of money and.
     *
     * @param source
     * @param target
     * @param amount
     * @return true or false
     */

    boolean transfer(Account source, Account target, long amount);

}
