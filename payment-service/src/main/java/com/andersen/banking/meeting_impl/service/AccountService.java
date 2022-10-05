package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.AccountChangesResponseDto;
import com.andersen.banking.meeting_db.entities.Account;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

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
     * This method changes the account balance value. If the balance is positive,
     * then it subtracts this value from the account, if it is negative, then it adds.
     *
     * @param accountId
     * @param amount
     * @return true or false
     */
    boolean changeAccountBalance(UUID accountId, long amount);

    /**
     * This method get all changes account by id
     *
     * @param id
     * @return list changes for account as {@code AccountChangesResponseDto}
     */
    List<AccountChangesResponseDto> changes(UUID id);
}
