package com.andersen.banking.service.payment.meeting_impl.mapper;

import com.andersen.banking.service.payment.meeting_api.dto.AccountDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T09:35:12+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDto toAccountDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setId( account.getId() );
        accountDto.setAccountNumber( account.getAccountNumber() );
        accountDto.setOpenDate( account.getOpenDate() );
        accountDto.setCloseDate( account.getCloseDate() );
        accountDto.setOwnerId( account.getOwnerId() );
        accountDto.setCurrency( account.getCurrency() );
        accountDto.setBankName( account.getBankName() );
        accountDto.setBalance( account.getBalance() );

        return accountDto;
    }

    @Override
    public Account toAccount(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( accountDto.getId() );
        account.setAccountNumber( accountDto.getAccountNumber() );
        account.setOpenDate( accountDto.getOpenDate() );
        account.setCloseDate( accountDto.getCloseDate() );
        account.setOwnerId( accountDto.getOwnerId() );
        account.setCurrency( accountDto.getCurrency() );
        account.setBankName( accountDto.getBankName() );
        account.setBalance( accountDto.getBalance() );

        return account;
    }
}
