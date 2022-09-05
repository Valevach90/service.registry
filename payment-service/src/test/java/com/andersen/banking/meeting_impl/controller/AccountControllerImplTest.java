package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.AccountController;
import com.andersen.banking.meeting_api.dto.AccountDto;
import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_impl.exception.MapperException;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.AccountMapper;
import com.andersen.banking.meeting_impl.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = AccountControllerImpl.class)
public class AccountControllerImplTest {

    final UUID ID = UUID.randomUUID();
    final UUID OWNER_ID = UUID.randomUUID();

    private static final UUID ACCOUNT_ID = UUID.randomUUID();
    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "accountId";

    private Account account;
    private AccountDto accountDto;

    @BeforeEach
    void init() {
        account = new Account();
        accountDto = new AccountDto();
    }

    @Autowired
    AccountController accountController;

    @MockBean
    AccountService accountService;
    @MockBean
    AccountMapper accountMapper;

    @Test
    void findById_ShouldReturnAccountDto_WhenIdISCorrect() {

        Mockito.when(accountService.findById(OWNER_ID)).thenReturn(account);
        Mockito.when(accountMapper.toAccountDto(account)).thenReturn(accountDto);
        Assertions.assertEquals(accountDto, accountController.findById(OWNER_ID));
    }

    @Test
    void findById_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
        Mockito.when(accountService.findById(OWNER_ID)).thenThrow(new NotFoundException(Account.class, OWNER_ID));
        Assertions.assertThrows(NotFoundException.class, () -> accountController.findById(OWNER_ID));
    }

    @Test
    void findAll_ShouldReturnPageOfAccounts() {
        List<AccountDto> accountDtos = generateAccountDtos();
        List<Account> accounts = generateAccounts();
        Pageable pageable = createPageable();
        Page<AccountDto> pageOfDto = new PageImpl<>(accountDtos, pageable, SIZE_PAGE);
        Page<Account> pageOfCards = new PageImpl<>(accounts, pageable, SIZE_PAGE);

        Mockito.when(accountService.findAll(pageable)).thenReturn(pageOfCards);
        Mockito.when(accountMapper.toAccountDto(Mockito.any(Account.class))).thenReturn(new AccountDto());

        Page<AccountDto> result = accountController.findAll(pageable);

        Assertions.assertEquals(pageOfDto, result);
    }

    @Test
    void updateAccount_ShouldUpdateAccount_WhenAccountDtoISCorrect() {
        account = populateAccount(account);
        accountDto = populateAccountDto(accountDto);

        Mockito.when(accountMapper.toAccount(accountDto)).thenReturn(account);
        Mockito.when(accountService.update(account)).thenReturn(account);
        Mockito.when(accountMapper.toAccountDto(account)).thenReturn(accountDto);

        Assertions.assertEquals(accountDto, accountController.updateAccount(accountDto));
    }

    @Test
    void updateAccount_ShouldThrowMapperException_WhenAccountDtoISIncorrect() {
        accountDto = populateAccountDto(accountDto);

        Mockito.when(accountMapper.toAccount(accountDto)).thenThrow(new MapperException());

        Assertions.assertThrows(MapperException.class, () -> accountController.updateAccount(accountDto));
    }

    @Test
    void updateAccount_ShouldThrowNotFoundException_WhenAccountDtoIsIncorrect() {
        account = populateAccount(account);
        accountDto = populateAccountDto(accountDto);

        Mockito.when(accountMapper.toAccount(accountDto)).thenReturn(account);
        Mockito.when(accountService.update(account)).thenThrow(new NotFoundException(Account.class, account.getId()));

        Assertions.assertThrows(NotFoundException.class, () -> accountController.updateAccount(accountDto));
    }

    @Test
    void deleteById_ShouldReturnDeletedObject_WhenIdIsCorrect() {
        Mockito.when(accountService.deleteById(OWNER_ID)).thenReturn(account);
        Mockito.when(accountMapper.toAccountDto(account)).thenReturn(accountDto);

        Assertions.assertEquals(accountDto, accountController.deleteById(OWNER_ID));
    }

    @Test
    void deleteById_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
        Mockito.when(accountService.deleteById(OWNER_ID)).thenThrow(new NotFoundException(Account.class, OWNER_ID));

        Assertions.assertThrows(NotFoundException.class, () -> accountController.deleteById(OWNER_ID));
    }

    @Test
    void create_ShouldReturnAccountDto_WhenAccountDtoIsCorrect() {

        account = populateAccount(account);
        accountDto = populateAccountDto(accountDto);

        Mockito.when(accountMapper.toAccount(accountDto)).thenReturn(account);
        Mockito.when(accountService.create(account)).thenReturn(account);
        Mockito.when(accountMapper.toAccountDto(account)).thenReturn(accountDto);

        Assertions.assertEquals(accountDto, accountController.create(accountDto));
    }

    @Test
    void create_ShouldThrowMapperException_WhenAccountDtoIsIncorrect() {
        accountDto = populateAccountDto(accountDto);
        Mockito.when(accountMapper.toAccount(accountDto)).thenThrow(new MapperException());

        Assertions.assertThrows(MapperException.class, () -> accountController.create(accountDto));
    }

    @Test
    void findByOwnerId_ShouldReturnPage_WhenOwnerIdIsCorrect() {
        List<AccountDto> accountDtos = generateAccountDtos();
        List<Account> accounts = generateAccounts();
        Pageable pageable = createPageable();
        Page<AccountDto> pageOfDto = new PageImpl<>(accountDtos, pageable, SIZE_PAGE);
        Page<Account> pageOfCards = new PageImpl<>(accounts, pageable, SIZE_PAGE);

        Mockito.when(accountService.findByOwnerId(OWNER_ID, pageable)).thenReturn(pageOfCards);
        Mockito.when(accountMapper.toAccountDto(Mockito.any(Account.class))).thenReturn(new AccountDto());

        Page<AccountDto> result = accountController.findByOwnerId(OWNER_ID, pageable);

        Assertions.assertEquals(pageOfDto, result);
    }

    private Account populateAccount(Account account) {
        account.setId(ACCOUNT_ID);
        account.setBalance(10000L);
        account.setBankName("Bank");
        account.setAccountNumber("1234567890");
        account.setCurrency("USD");
        account.setOwnerId(OWNER_ID);
        account.setOpenDate(LocalDate.of(2021, 10, 23));
        account.setCloseDate(LocalDate.of(2024, 10, 23));
        return account;
    }

    private AccountDto populateAccountDto(AccountDto accountDto) {
        accountDto.setId(ACCOUNT_ID);
        accountDto.setBalance(10000L);
        accountDto.setBankName("Bank");
        accountDto.setAccountNumber("1234567890");
        accountDto.setCurrency("USD");
        accountDto.setOwnerId(OWNER_ID);
        accountDto.setOpenDate(LocalDate.of(2021, 10, 23));
        accountDto.setCloseDate(LocalDate.of(2024, 10, 23));
        return accountDto;
    }

    private List<Account> generateAccounts() {
        return Stream
                .generate(Account::new)
                .limit(27)
                .collect(Collectors.toList());
    }

    private List<AccountDto> generateAccountDtos() {
        return Stream
                .generate(AccountDto::new)
                .limit(27)
                .collect(Collectors.toList());
    }

    private Pageable createPageable() {
        Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
        return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
    }
}
