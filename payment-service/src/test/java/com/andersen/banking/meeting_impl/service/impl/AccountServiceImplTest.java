package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.repository.AccountRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = AccountServiceImpl.class)
public class AccountServiceImplTest {

    final UUID ACCOUNT_ID = UUID.randomUUID();
    final UUID OWNER_ID = UUID.randomUUID();

    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "accountId";

    private Account receivedAccount;
    private Account returnedAccount;

    @SpyBean AccountService accountService;
    @MockBean AccountRepository accountRepository;

    @BeforeEach
    void initArguments() {
        receivedAccount = new Account();
        returnedAccount = new Account();
    }

    @Test
    void findById_ShouldReturnAccount_WhenIdIsCorrect() {
        Mockito.when(accountRepository.findById(ACCOUNT_ID))
                .thenReturn(Optional.of(returnedAccount));
        Assertions.assertEquals(returnedAccount, accountService.findById(ACCOUNT_ID));
    }

    @Test
    void findById_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
        final UUID randomID = UUID.randomUUID();

        Assertions.assertThrows(NotFoundException.class, () -> accountService.findById(randomID));
    }

    @Test
    void findAll_ShouldReturnPageOfAccounts() {
        List<Account> accounts = generateAccounts();
        Pageable pageable = createPageable();
        Page<Account> page = new PageImpl<>(accounts, pageable, SIZE_PAGE);

        Mockito.when(accountRepository.findAll(pageable)).thenReturn(page);

        Page<Account> result = accountService.findAll(pageable);

        Assertions.assertEquals(page, result);
    }

    @Test
    void update_ShouldReturnAccount_WhenReceivedAccountIsCorrect() {
        receivedAccount = populateAccount(receivedAccount);
        returnedAccount = populateAccount(returnedAccount);

        Mockito.when(accountRepository.findById(receivedAccount.getId()))
                .thenReturn(Optional.of(returnedAccount));
        Mockito.when(accountRepository.save(receivedAccount)).thenReturn(returnedAccount);

        Assertions.assertEquals(returnedAccount, accountService.update(receivedAccount));
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenReceivedAccountHasIncorrectId() {
        receivedAccount = populateAccount(receivedAccount);

        Mockito.when(accountRepository.findById(receivedAccount.getId()))
                .thenThrow(new NotFoundException(Account.class, receivedAccount.getId()));

        Assertions.assertThrows(
                NotFoundException.class, () -> accountService.update(receivedAccount));
    }

    @Test
    void deleteById_ShouldReturnDeletedAccount_WhenAccountIdIsCorrect() {
        Mockito.when(accountRepository.findById(ACCOUNT_ID))
                .thenReturn(Optional.of(returnedAccount));

        Assertions.assertEquals(returnedAccount, accountService.deleteById(ACCOUNT_ID));
    }

    @Test
    void deleteById_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
        Mockito.when(accountRepository.findById(ACCOUNT_ID))
                .thenThrow(new NotFoundException(Card.class, receivedAccount.getId()));

        Assertions.assertThrows(
                NotFoundException.class, () -> accountService.deleteById(ACCOUNT_ID));
    }

    @Test
    void create_ShouldReturnCard_WhenReceivedCardIsCorrect() {
        receivedAccount = populateAccount(receivedAccount);
        returnedAccount = populateAccount(returnedAccount);

        Mockito.when(accountRepository.save(receivedAccount)).thenReturn(returnedAccount);

        Assertions.assertEquals(returnedAccount, accountService.create(receivedAccount));
    }

    @Test
    void findByOwnerId_ShouldReturnPage_WhenOwnerIdIsCorrect() {
        List<Account> accounts = generateAccounts();
        Pageable pageable = createPageable();
        Page<Account> page = new PageImpl<>(accounts, pageable, SIZE_PAGE);

        Mockito.when(accountRepository.findAccountByOwnerId(ACCOUNT_ID, pageable)).thenReturn(page);

        Page<Account> result = accountService.findByOwnerId(ACCOUNT_ID, pageable);

        Assertions.assertEquals(page, result);
    }

    private List<Account> generateAccounts() {
        return Stream.generate(Account::new).limit(27).collect(Collectors.toList());
    }

    private Pageable createPageable() {
        Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
        return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
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
}
