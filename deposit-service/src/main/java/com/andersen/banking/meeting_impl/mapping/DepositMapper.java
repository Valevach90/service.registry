package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.deposit.DepositCreateRequestDto;
import com.andersen.banking.meeting_api.dto.deposit.DepositDto;
import com.andersen.banking.meeting_api.dto.deposit.DepositRequestDto;
import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for deposit.
 */
@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepositMapper {

    DepositDto toDepositDto(Deposit deposit);

    @InheritInverseConfiguration
    @Mapping(target = "isActive", ignore = true)
    Deposit toDeposit(DepositDto depositDto);

    @Mapping(target = "depositProduct.id", source = "depositProductId")
    @Mapping(target = "type.id", source = "typeId")
    @Mapping(target = "currency.id", source = "currencyId")
    Deposit toDeposit(DepositCreateRequestDto depositDto);

    @Mapping(target = "depositProduct", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "currency", ignore = true)
    Deposit toDeposit(DepositRequestDto depositDto);
}
