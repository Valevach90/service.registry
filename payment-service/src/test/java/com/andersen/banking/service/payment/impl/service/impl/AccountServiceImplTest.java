package com.andersen.banking.service.payment.impl.service.impl;

import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.repository.AccountRepository;
import com.andersen.banking.service.payment.meeting_impl.exception.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.service.AccountService;
import com.andersen.banking.service.payment.meeting_impl.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = AccountServiceImpl.class)
public class AccountServiceImplTest {

    private static final Long ID = 99L;
    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "userId";
    private static final int TIME_IN_MILLIS = 12345;


    private Account receivedAccount;
    private Account returnedAccount;

    @SpyBean
    AccountService accountService;

    @MockBean
    AccountRepository accountRepository;

    @BeforeEach
    void init() {
        receivedAccount = new Account();
        returnedAccount = new Account();
    }

    @Test
    void findById_ThrowNotFoundException_WhenIdIsIncorrect() {
        Assertions.assertThrows(NotFoundException.class, () -> accountService.findById(ID));
    }

    @Test
    void findById_ReturnAccount_WhenIdIsCorrect() {
        Mockito.when(accountRepository.findById(ID)).thenReturn(Optional.ofNullable(returnedAccount));
        Assertions.assertEquals(returnedAccount, accountService.findById(ID));
    }

    @Test
    void findAll_ReturnPageOfAccounts() {
        List<Account> accounts = generateAccounts();
        Pageable pageable = createPageable();
        Page<Account> page = new PageImpl<>(accounts, pageable, SIZE_PAGE);

        Mockito.when(accountRepository.findAll(pageable)).thenReturn(page);

        Page<Account> result = accountService.findAll(pageable);

        Assertions.assertEquals(page, result);
    }

    @Test
    void deleteById_ReturnDeletedAccount_WhenAccountIdIsCorrect() {
        Mockito.when(accountRepository.findById(ID)).thenReturn(Optional.of(returnedAccount));

        Assertions.assertEquals(returnedAccount, accountService.deleteById(ID));
    }

    @Test
    void deleteById_ThrowNotFoundException_WhenAccountIdIsIncorrect() {
        Mockito.when(accountRepository.findById(ID)).thenThrow(new NotFoundException(Account.class, returnedAccount.getId()));

        Assertions.assertThrows(NotFoundException.class, () -> accountService.deleteById(ID));
    }

    @Test
    void update_ReturnAccount_WhenReceivedAccountIsCorrect() {
        receivedAccount = getAccountExample();
        returnedAccount = getAccountExample();

        Mockito.when(accountRepository.findById(receivedAccount.getId())).thenReturn(Optional.of(returnedAccount));
        Mockito.when(accountRepository.save(receivedAccount)).thenReturn(returnedAccount);

        Assertions.assertEquals(returnedAccount, accountService.update(receivedAccount));
    }

    @Test
    void update_ThrowNotFoundException_WhenReceivedAccountHasIncorrectId() {
        receivedAccount = getAccountExample();

        Mockito.when(accountRepository.findById(receivedAccount.getId()))
                .thenThrow(new NotFoundException(Account.class, returnedAccount.getId()));

        Assertions.assertThrows(NotFoundException.class, () -> accountService.update(receivedAccount));
    }


    private List<Account> generateAccounts() {
        return Stream
                .generate(Account::new)
                .limit(20)
                .collect(Collectors.toList());
    }

    private Pageable createPageable() {
        Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
        return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
    }


    private Account getAccountExample() {
        Account account = new Account();
        account.setId(ID);
        account.setUserId(1L);
        account.setAccountNumber("123456");
        account.setBankName("OOO Bank");
        account.setIssueDate(new Date(TIME_IN_MILLIS));
        account.setTerminationDate(new Date(TIME_IN_MILLIS));
        account.setCurrency("EUR");
        return account;
    }


}
