package com.andersen.banking.deposit.service.deposit_impl.mapping;

import com.andersen.banking.deposit.service.deposit_api.dto.DepositProductDto;
import com.andersen.banking.deposit.service.deposit_db.entities.DepositProduct;
import com.andersen.banking.deposit.service.deposit_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for deposit product.
 */
@Mapper(config = MapperConfig.class)
public interface DepositProductMapper {

    DepositProductDto toDepositProductDto(DepositProduct depositProduct);

    DepositProduct toDepositProduct(DepositProductDto depositProductDto);
}
