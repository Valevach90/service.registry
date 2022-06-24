package com.andersen.banking.service.payment.meeting_impl.mapper;

import com.andersen.banking.service.payment.meeting_api.dto.AccountDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * This interface presents the basic contract for converting Account to AccountDto and vice versa.
 */
@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {


    AccountDto toAccountDto(Account account);

    Account toAccount(AccountDto accountDto);
}

