package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.TransferDto;
import com.andersen.banking.deposit_api.dto.kafka.RequestTransferKafkaMessage;
import com.andersen.banking.deposit_db.entities.Transfer;
import com.andersen.banking.deposit_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for transfer.
 */
@Mapper(config = MapperConfig.class)
public interface TransferMapper {

    @Mapping(target = "deposit", ignore = true)
    TransferDto toTransferDto(Transfer transfer);

    @Mapping(target = "deposit", ignore = true)
    Transfer toTransfer(TransferDto transferDto);

    @Mapping(target = "deposit", ignore = true)
    @Mapping(target = "time", ignore = true)
    @Mapping(target = "result", ignore = true)
    @Mapping(target = "statusDescription", ignore = true)
    Transfer toTransfer(RequestTransferKafkaMessage message);

    List<Transfer> toTransfersDto(List<TransferDto> transfersDto);
    List<TransferDto> toTransfers(List<Transfer> transfers);
}
