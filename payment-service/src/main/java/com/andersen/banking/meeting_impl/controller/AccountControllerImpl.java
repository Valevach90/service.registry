package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.AccountController;
import com.andersen.banking.meeting_api.dto.AccountChangesResponseDto;
import com.andersen.banking.meeting_api.dto.AccountDto;
import com.andersen.banking.meeting_api.dto.AccountRegistrationDto;
import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_impl.mapper.AccountMapper;
import com.andersen.banking.meeting_impl.service.AccountService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * @param accountRegistrationDto - AccountDto to register
     * @return accountDto - created AccountDto
     */
    @Override
    public AccountDto create(AccountRegistrationDto accountRegistrationDto) {
        log.trace("Receiving request for creating account: {}", accountRegistrationDto);

        Account account2Create = accountMapper.toAccount(accountRegistrationDto);
        AccountDto savedAccount = accountMapper.toAccountResponseDto(
                accountService.create(account2Create));

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

        Page<AccountDto> accountDtoPage =
                accountService.findAll(pageable).map(accountMapper::toAccountResponseDto);

        log.trace("Returning list of accounts: {}", accountDtoPage.getContent());
        return accountDtoPage;
    }

    /**
     * End-point to find page of accounts with ownerId
     *
     * @param id       - ownerId
     * @param pageable
     * @return accountPageDto
     */
    @Override
    public Page<AccountDto> findByOwnerId(UUID id, Pageable pageable) {
        log.trace("Receiving request for account with ownerId: {}", id);

        Page<AccountDto> accountDtoPage =
                accountService.findByOwnerId(id, pageable).map(accountMapper::toAccountResponseDto);

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
    public AccountDto findById(UUID id) {
        log.trace("Receiving account id: {}", id);

        AccountDto accountDto = accountMapper.toAccountResponseDto(accountService.findById(id));

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
        AccountDto updatedAccountDto =
                accountMapper.toAccountResponseDto(accountService.update(account2Update));

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
    public AccountDto deactivateById(UUID id) {
        log.trace("Receiving account id: {}", id);

        Account accountToDeactivate = accountService.deactivateById(id);
        AccountDto deactivatedAccountDto = accountMapper.toAccountResponseDto(accountToDeactivate);

        log.trace("Returning deleted account with id: {}", id);
        return deactivatedAccountDto;
    }

    /**
     * Endpoint for getting changes account by id
     *
     * @param id - Account {@code UUID} id
     * @return - list by all changes with this account as {@code AccountChangesResponseDto}
     */
    @Override
    public List<AccountChangesResponseDto> changes(UUID id) {
        log.info("Controller account for getting changes for account with id: {}", id);
        return accountService.changes(id);
    }
}
