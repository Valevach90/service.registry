package com.andersen.banking.service.payment.meeting_impl.mapping;

import com.andersen.banking.service.payment.meeting_api.dto.AccountDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_impl.mapper.AccountMapper;
import com.andersen.banking.service.payment.meeting_impl.mapper.AccountMapperImpl;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AccountMapperImpl.class)
public class AccountMapperTest {

  @Autowired
  AccountMapper accountMapper;

  @Test
  void toCard_ShouldReturnCardDto_WhenCardIsCorrect() {
    Assertions.assertEquals(populateAccount(), accountMapper.toAccount(populateAccountDto()));
  }

  @Test
  void toCardDto_ShouldReturnCard_WhenCardDtoIsCorrect() {
    Assertions.assertEquals(populateAccountDto(), accountMapper.toAccountDto(populateAccount()));
  }

  private Account populateAccount() {
    Account account = new Account();
    account.setId(9L);
    account.setBalance(10000L);
    account.setBankName("Bank");
    account.setAccountNumber("1234567890");
    account.setCurrency("USD");
    account.setOwnerId(1L);
    account.setOpenDate(LocalDate.of(2021, 10, 23));
    account.setCloseDate(LocalDate.of(2024, 10, 23));
    return account;
  }

  private AccountDto populateAccountDto() {
    AccountDto accountDto = new AccountDto();
    accountDto.setId(9L);
    accountDto.setBalance(10000L);
    accountDto.setBankName("Bank");
    accountDto.setAccountNumber("1234567890");
    accountDto.setCurrency("USD");
    accountDto.setOwnerId(1L);
    accountDto.setOpenDate(LocalDate.of(2021, 10, 23));
    accountDto.setCloseDate(LocalDate.of(2024, 10, 23));
    return accountDto;
  }
}
