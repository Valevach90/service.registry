package com.andersen.banking.service.payment.meeting_impl.mapper;

import com.andersen.banking.service.payment.meeting_api.dto.AccountDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-01T16:57:41+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
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
        accountDto.setUserId( account.getUserId() );
        accountDto.setIssueDate( account.getIssueDate() );
        accountDto.setTerminationDate( account.getTerminationDate() );
        accountDto.setBankName( account.getBankName() );
        accountDto.setAccountNumber( account.getAccountNumber() );
        accountDto.setCurrency( account.getCurrency() );

        return accountDto;
    }

    @Override
    public Account toAccount(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( accountDto.getId() );
        account.setUserId( accountDto.getUserId() );
        account.setIssueDate( accountDto.getIssueDate() );
        account.setTerminationDate( accountDto.getTerminationDate() );
        account.setBankName( accountDto.getBankName() );
        account.setAccountNumber( accountDto.getAccountNumber() );
        account.setCurrency( accountDto.getCurrency() );

        return account;
    }
}
