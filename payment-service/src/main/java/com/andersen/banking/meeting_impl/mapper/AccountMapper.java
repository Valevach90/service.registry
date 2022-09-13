package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.AccountDto;
import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

/**
 * This interface presents the basic contract for converting Account to AccountDto and vice versa.
 */
@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    @Mapping(source = "balance", target = "balance", qualifiedByName = "convertFromCents")
    AccountDto toAccountDto(Account account);

    @Mapping(source = "balance", target = "balance", qualifiedByName = "convertToCents")
    @Mapping(target = "accountNumber", ignore = true)
    Account toAccount(AccountDto accountDto);

    @Named("convertToCents")
    static Long convertToCents(Double balance) {
        Double balanceInCents = balance * 100;
        return balanceInCents.longValue();
    }

    @Named("convertFromCents")
    static Double convertFromCents(Long balance) {
        return balance.doubleValue() / 100;
    }
}
