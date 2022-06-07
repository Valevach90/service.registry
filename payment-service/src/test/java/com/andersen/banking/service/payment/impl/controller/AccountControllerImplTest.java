package com.andersen.banking.service.payment.impl.controller;

import com.andersen.banking.service.payment.meeting_api.controller.AccountController;
import com.andersen.banking.service.payment.meeting_api.dto.AccountDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_impl.controller.AccountControllerImpl;
import com.andersen.banking.service.payment.meeting_impl.exception.MapperException;
import com.andersen.banking.service.payment.meeting_impl.exception.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.mapper.AccountMapper;
import com.andersen.banking.service.payment.meeting_impl.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = AccountControllerImpl.class)
public class AccountControllerImplTest {

    private static final Long ID = 1L;
    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "userId";
    private static final int TIME_IN_MILLIS = 12345;
    private static final int LIMIT_GENERATED_ITEM_IN_LIST = 20;

    @Autowired
    AccountController accountController;

    private Account account;
    private AccountDto accountDto;

    @BeforeEach
    void init() {
        account = new Account();
        accountDto = new AccountDto();
    }

    @MockBean
    AccountService accountService;

    @MockBean
    AccountMapper accountMapper;

    @Test
    void findAll_ShouldReturnPageOfAccounts() {
        List<AccountDto> accountDtos = getGeneratedListOfAccountDto();
        List<Account> accounts = getGeneratedListOfAccount();
        Pageable pageable = getPageable();
        Page<AccountDto> pageOfDto = new PageImpl<>(accountDtos, pageable, SIZE_PAGE);
        Page<Account> pageOfAccounts = new PageImpl<>(accounts, pageable, SIZE_PAGE);

        Mockito.when(accountService.findAll(pageable)).thenReturn(pageOfAccounts);
        Mockito.when(accountMapper.toAccountDto(Mockito.any(Account.class))).thenReturn(new AccountDto());

        Page<AccountDto> result = accountController.findAll(pageable);

        Assertions.assertEquals(pageOfDto, result);
    }

    @Test
    void findById_ReturnAccountDto_WhenIdISCorrect() {

        Mockito.when(accountService.findById(ID)).thenReturn(account);
        Mockito.when(accountMapper.toAccountDto(account)).thenReturn(accountDto);
        Assertions.assertEquals(accountDto, accountController.findById(ID));
    }

    @Test
    void findById_ThrowNotFoundException_WhenIdIsIncorrect() {
        Mockito.when(accountService.findById(ID)).thenThrow(new NotFoundException(Account.class, ID));
        Assertions.assertThrows(NotFoundException.class, () -> accountController.findById(ID));
    }


    @Test
    void updateAccount_UpdateAccount_WhenAccountDtoISCorrect() {
        account = getAccountExample();
        accountDto = getAccountExampleDto();

        Mockito.when(accountMapper.toAccount(accountDto)).thenReturn(account);
        Mockito.when(accountService.update(account)).thenReturn(account);
        Mockito.when(accountMapper.toAccountDto(account)).thenReturn(accountDto);

        Assertions.assertEquals(accountDto, accountController.updateAccount(accountDto));
    }

    @Test
    void updateAccount_ThrowMapperException_WhenAccountDtoISIncorrect() {
        accountDto = getAccountExampleDto();

        Mockito.when(accountMapper.toAccount(accountDto)).thenThrow(new MapperException());

        Assertions.assertThrows(MapperException.class, () -> accountController.updateAccount(accountDto));
    }

    @Test
    void updateAccount_ThrowNotFoundException_WhenAccountDtoISIncorrect() {
        account = getAccountExample();
        accountDto = getAccountExampleDto();

        Mockito.when(accountMapper.toAccount(accountDto)).thenReturn(account);
        Mockito.when(accountService.update(account)).thenThrow(new NotFoundException(Account.class, account.getId()));

        Assertions.assertThrows(NotFoundException.class, () -> accountController.updateAccount(accountDto));
    }


    @Test
    void deleteById_ReturnDeletedAccount_WhenIdIsCorrect() {
        Mockito.when(accountService.deleteById(ID)).thenReturn(account);
        Mockito.when(accountMapper.toAccountDto(account)).thenReturn(accountDto);

        Assertions.assertEquals(accountDto, accountController.deleteById(ID));
    }

    @Test
    void deleteById_ThrowNotFoundException_WhenIdIsIncorrect() {
        Mockito.when(accountService.deleteById(ID)).thenThrow(new NotFoundException(Account.class, ID));

        Assertions.assertThrows(NotFoundException.class, () -> accountController.deleteById(ID));
    }

    @Test
    void create_ReturnAccountDto_WhenAccountDtoIsCorrect() {
        accountDto = getAccountExampleDto();
        account = getAccountExample();

        Mockito.when(accountMapper.toAccount(accountDto)).thenReturn(account);
        Mockito.when(accountService.create(account)).thenReturn(account);
        Mockito.when(accountMapper.toAccountDto(account)).thenReturn(accountDto);

        Assertions.assertEquals(accountDto, accountController.create(accountDto));
    }

    @Test
    void create_ThrowMapperException_WhenAccountDtoIsIncorrect() {
        accountDto = getAccountExampleDto();
        Mockito.when(accountMapper.toAccount(accountDto)).thenThrow(new MapperException());

        Assertions.assertThrows(MapperException.class, () -> accountController.create(accountDto));
    }

    private Account getAccountExample() {
        Account account = new Account();
        account.setId(1L);
        account.setUserId(1L);
        account.setAccountNumber("123456");
        account.setBankName("OOO Bank");
        account.setIssueDate(new Date(TIME_IN_MILLIS));
        account.setTerminationDate(new Date(TIME_IN_MILLIS));
        account.setCurrency("EUR");
        return account;
    }

    private List<Account> getGeneratedListOfAccount() {
        return Stream
                .generate(Account::new)
                .limit(LIMIT_GENERATED_ITEM_IN_LIST)
                .collect(Collectors.toList());
    }

    private AccountDto getAccountExampleDto() {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(1L);
        accountDto.setUserId(1L);
        accountDto.setAccountNumber("123456");
        accountDto.setBankName("OOO Bank");
        accountDto.setIssueDate(new Date(TIME_IN_MILLIS));
        accountDto.setTerminationDate(new Date(TIME_IN_MILLIS));
        accountDto.setCurrency("EUR");
        return accountDto;
    }

    private List<AccountDto> getGeneratedListOfAccountDto() {
        return Stream
                .generate(AccountDto::new)
                .limit(LIMIT_GENERATED_ITEM_IN_LIST)
                .collect(Collectors.toList());
    }

    private Pageable getPageable() {
        Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
        return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
    }


}
