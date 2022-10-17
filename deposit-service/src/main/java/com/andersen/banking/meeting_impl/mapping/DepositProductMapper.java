package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductRequestCreateDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductRequestDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductResponseDto;
import com.andersen.banking.meeting_db.entities.DepositProduct;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for deposit product.
 */
@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepositProductMapper {

    DepositProductResponseDto toDepositProductDto(DepositProduct depositProduct);

    DepositProduct toDepositProduct(
            DepositProductResponseDto depositProductResponseDto);

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "currency", ignore = true)
    DepositProduct toDepositProduct(
            DepositProductRequestDto depositProductRequestDto);

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "currency", ignore = true)
    @Mapping(target = "id", ignore = true)
    DepositProduct toDepositProduct(
            DepositProductRequestCreateDto depositProductRequestDto);
}
