package com.andersen.banking.service.payment.impl.mapping;

import com.andersen.banking.service.payment.meeting_api.dto.AccountDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_impl.mapper.AccountMapper;
import com.andersen.banking.service.payment.meeting_impl.mapper.AccountMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest(classes = AccountMapperImpl.class)
public class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;
    private static final int TIME_IN_MILLIS = 12345;


    @Test
    void AccountDto2Account_ReturnAccountDto() {
        Assertions.assertEquals(getAccountExample(), accountMapper.toAccount(getAccountDtoExample()));
    }

    @Test
    void Account2AccountDto_ShouldReturnAccount() {
        Assertions.assertEquals(getAccountDtoExample(), accountMapper.toAccountDto(getAccountExample()));
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

    private AccountDto getAccountDtoExample() {
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


}
