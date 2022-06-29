package com.andersen.banking.deposit.service.deposit_impl.mapping;

import com.andersen.banking.deposit.service.deposit_api.dto.TransferDto;
import com.andersen.banking.deposit.service.deposit_db.entities.Transfer;
import com.andersen.banking.deposit.service.deposit_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for transfer.
 */
@Mapper(config = MapperConfig.class)
public interface TransferMapper {

    @Mapping(target = "depositId", source = "deposit.id")
    TransferDto toTransferDto(Transfer transfer);

    @Mapping(target = "deposit", ignore = true)
    Transfer toTransfer(TransferDto transferDto);
}
