package com.andersen.banking.deposit.service.deposit_impl.mapping;

import com.andersen.banking.deposit.service.deposit_api.dto.DepositTypeDto;
import com.andersen.banking.deposit.service.deposit_db.entities.DepositType;
import com.andersen.banking.deposit.service.deposit_impl.config.MapperConfig;
import org.mapstruct.Mapper;

/**
 * Mapper for deposit type.
 */
@Mapper(config = MapperConfig.class)
public interface DepositTypeMapper {

    DepositTypeDto toDepositTypeDto(DepositType depositType);

    DepositType toDepositType(DepositTypeDto depositTypeDto);
}