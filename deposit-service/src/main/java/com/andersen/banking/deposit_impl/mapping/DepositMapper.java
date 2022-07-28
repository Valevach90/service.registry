package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.DepositDto;
import com.andersen.banking.deposit_db.entities.Deposit;
import com.andersen.banking.deposit_impl.config.MapperConfig;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for deposit.
 */
@Mapper(config = MapperConfig.class)
public interface DepositMapper {

    @Mapping(target = "transfersDto", source = "transfers")
    DepositDto toDepositDto(Deposit deposit);

    @InheritInverseConfiguration
    Deposit toDeposit(DepositDto depositDto);
}
