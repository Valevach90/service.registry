package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.AccountDto;
import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_impl.mapper.AccountMapper;
import com.andersen.banking.meeting_impl.mapper.AccountMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest(classes = AccountMapperImpl.class)
class AccountMapperTest {

    final UUID ACCOUNT_ID = UUID.randomUUID();
    final UUID OWNER_ID = UUID.randomUUID();

    @Autowired AccountMapper accountMapper;

    @Test
    void toAccount_ShouldReturnAccountDto_WhenAccountIsCorrect() {
        Assertions.assertEquals(populateAccount(), accountMapper.toAccount(populateAccountDto()));
    }

    @Test
    void toAccountDto_ShouldReturnAccount_WhenAccountDtoIsCorrect() {
        Assertions.assertEquals(
                populateAccountDto(), accountMapper.toAccountDto(populateAccount()));
    }

    @Test
    void toAccount_ShouldReturnLongBalance_WhenAccountDtoDoubleBalance() {
        AccountDto accountDto = populateAccountDto();
        accountDto.setBalance(100.95);
        Assertions.assertEquals(10095, accountMapper.toAccount(accountDto).getBalance());
    }

    @Test
    void toAccountDto_ShouldReturnDoubleBalance_WhenAccountLongBalance() {
        Account account = populateAccount();
        account.setBalance(10095);
        Assertions.assertEquals(100.95, accountMapper.toAccountDto(account).getBalance());
    }

    private Account populateAccount() {
        Account account = new Account();
        account.setId(ACCOUNT_ID);
        account.setBalance(1_000_000);
        account.setBankName("Bank");
        account.setAccountNumber("1234567890");
        account.setCurrency("USD");
        account.setOwnerId(OWNER_ID);
        account.setOpenDate(LocalDate.of(2021, 10, 23));
        account.setCloseDate(LocalDate.of(2024, 10, 23));
        return account;
    }

    private AccountDto populateAccountDto() {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(ACCOUNT_ID);
        accountDto.setBalance(10000);
        accountDto.setBankName("Bank");
        accountDto.setAccountNumber("1234567890");
        accountDto.setCurrency("USD");
        accountDto.setOwnerId(OWNER_ID);
        accountDto.setOpenDate(LocalDate.of(2021, 10, 23));
        accountDto.setCloseDate(LocalDate.of(2024, 10, 23));
        return accountDto;
    }
}
