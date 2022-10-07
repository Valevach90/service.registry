package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.DepositProductDescriptionDto;
import com.andersen.banking.meeting_db.entities.DepositProductDescription;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for deposit product description.
 */
@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepositProductDescriptionMapper {

    DepositProductDescriptionDto toDepositProductDescriptionDto(
            DepositProductDescription depositProductDescription);

    DepositProductDescription toDepositProductDescription(
            DepositProductDescriptionDto depositProductDescriptionDto);
}
