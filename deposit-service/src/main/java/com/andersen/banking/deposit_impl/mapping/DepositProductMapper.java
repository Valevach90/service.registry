package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.DepositProductDto;
import com.andersen.banking.deposit_db.entities.DepositProduct;
import com.andersen.banking.deposit_impl.config.MapperConfig;
import org.mapstruct.Mapper;

/**
 * Mapper for deposit product.
 */
@Mapper(config = MapperConfig.class)
public interface DepositProductMapper {

    DepositProductDto toDepositProductDto(DepositProduct depositProduct);

    DepositProduct toDepositProduct(DepositProductDto depositProductDto);
}
