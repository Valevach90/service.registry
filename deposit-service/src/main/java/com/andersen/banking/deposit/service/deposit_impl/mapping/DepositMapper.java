package com.andersen.banking.deposit.service.deposit_impl.mapping;

import com.andersen.banking.deposit.service.deposit_api.dto.DepositDto;
import com.andersen.banking.deposit.service.deposit_db.entities.Deposit;
import com.andersen.banking.deposit.service.deposit_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for deposit.
 */
@Mapper(config = MapperConfig.class)
public interface DepositMapper {

    DepositDto toDepositDto(Deposit deposit);

    Deposit toDeposit(DepositDto depositDto);
}
