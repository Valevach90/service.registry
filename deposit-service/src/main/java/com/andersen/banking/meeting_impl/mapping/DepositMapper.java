package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.DepositDto;
import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_impl.config.MapperConfig;
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