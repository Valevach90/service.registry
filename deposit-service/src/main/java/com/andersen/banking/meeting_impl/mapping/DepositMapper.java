package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.DepositCreateRequestDto;
import com.andersen.banking.meeting_api.dto.DepositDto;
import com.andersen.banking.meeting_api.dto.DepositRequestDto;
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

    @Mapping(target = "productId", source = "depositProduct.id")
    @Mapping(target = "typeId", source = "type.id")
    @Mapping(target = "currencyId", source = "currency.id")
    DepositRequestDto toDepositRequest(Deposit deposit);

    @Mapping(target = "depositProduct.id", source = "depositProductId")
    @Mapping(target = "type.id", source = "typeId")
    @Mapping(target = "currency.id", source = "currencyId")
    Deposit toDeposit(DepositCreateRequestDto depositDto);
}
