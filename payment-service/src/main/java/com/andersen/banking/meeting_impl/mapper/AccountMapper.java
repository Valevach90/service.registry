package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.AccountDto;
import com.andersen.banking.meeting_api.dto.AccountRegistrationDto;
import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * This interface presents the basic contract for converting Account to AccountDto and vice versa.
 */
@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountDto toAccountResponseDto(Account account);

    Account toAccount(AccountDto accountDto);

    @Mapping(target = "openDate", ignore = true)
    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "active", ignore = true)
    Account toAccount(AccountRegistrationDto accountRegistrationDto);
}
