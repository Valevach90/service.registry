package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.TransferDto;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_db.entities.Transfer;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for transfer.
 */
@Mapper(config = MapperConfig.class)
public interface TransferMapper {

    TransferDto toTransferDto(Transfer transfer);

    Transfer toTransfer(TransferDto transferDto);

    @Mapping(target = "time", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "statusDescription", ignore = true)
    Transfer toTransfer(RequestTransferMessage message);

    List<Transfer> toTransfersDto(List<TransferDto> transfersDto);

    List<TransferDto> toTransfers(List<Transfer> transfers);
}
