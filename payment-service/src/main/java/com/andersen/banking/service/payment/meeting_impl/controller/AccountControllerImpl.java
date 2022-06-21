package com.andersen.banking.service.payment.meeting_impl.controller;

import com.andersen.banking.service.payment.meeting_api.controller.AccountController;
import com.andersen.banking.service.payment.meeting_api.dto.AccountDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_impl.mapper.AccountMapper;
import com.andersen.banking.service.payment.meeting_impl.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for AccountController
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    /**
     * Controller to register new Account entity.
     *
     * @param accountDto - AccountDto to register
     * @return accountDto - created AccountDto
     */


    @Override
    @Transactional
    public AccountDto create(AccountDto accountDto) {
        log.trace("Receiving request for creating account: {}", accountDto);

        Account account2Create = accountMapper.toAccount(accountDto);
        AccountDto savedAccount = accountMapper.toAccountDto(accountService.create(account2Create));

        log.trace("Returning created account: {}", savedAccount);
        return savedAccount;
    }

    /**
     * End-point to find all Accounts entities.
     *
     * @param pageable
     * @return accountDtoPage
     */


    @Override
    public Page<AccountDto> findAll(Pageable pageable) {
        log.trace("Receiving request for all accounts");

        Page<AccountDto> accountDtoPage = accountService.findAll(pageable)
                .map(accountMapper::toAccountDto);

        log.trace("Returning list of accounts: {}", accountDtoPage.getContent());
        return accountDtoPage;
    }

    /**
     * End-point to find page of accounts with ownerId
     *
     * @param id - ownerId
     * @param pageable
     * @return accountPageDto
     */

    @Override
    public Page<AccountDto> findByOwnerId(Long id, Pageable pageable) {
        log.trace("Receiving request for account with ownerId: {}", id);

        Page<AccountDto> accountDtoPage = accountService.findByOwnerId(id, pageable)
                .map(accountMapper::toAccountDto);

        log.trace("Returning page of accounts: {}", accountDtoPage.getContent());
        return accountDtoPage;
    }

    /**
     * End-point to find Account entity by id.
     *
     * @param id - Account id
     * @return accountDto - account with required id
     */


    @Override
    public AccountDto findById(Long id) {
        log.trace("Receiving account id: {}", id);

        AccountDto accountDto = accountMapper.toAccountDto(accountService.findById(id));

        log.trace("Returning account with id: {}", id);
        return accountDto;

    }

    /**
     * End-point to update existing Account entity.
     *
     * @param accountDto - dto with require updating fields
     * @return updatedAccountDto - updated entity dto
     */


    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        log.trace("Receiving account: {}", accountDto);

        Account account2Update = accountMapper.toAccount(accountDto);
        AccountDto updatedAccountDto = accountMapper.toAccountDto(accountService.update(account2Update));

        log.trace("Returning updated account: {}", updatedAccountDto);
        return updatedAccountDto;

    }

    /**
     * End-point to delete Account entity.
     *
     * @param id - Account id
     * @return deletedAccountDto - deleted entity dto
     */


    @Override
    public AccountDto deleteById(Long id) {
        log.trace("Receiving account id: {}", id);

        Account deletedAccount = accountService.deleteById(id);
        AccountDto deletedAccountDto = accountMapper.toAccountDto(deletedAccount);

        log.trace("Returning deleted account with id: {}", id);
        return deletedAccountDto;

    }

}
