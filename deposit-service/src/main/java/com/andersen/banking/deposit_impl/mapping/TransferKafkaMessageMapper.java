package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.kafka.TransferKafkaMessageDto;
import com.andersen.banking.deposit_db.entities.TransferKafkaMessage;
import com.andersen.banking.deposit_impl.config.MapperConfig;
import org.mapstruct.Mapper;

/**
 * Mapper for transfer kafka messages.
 */
@Mapper(config = MapperConfig.class)
public interface TransferKafkaMessageMapper {

    TransferKafkaMessageDto toTransferKafkaMessageDto(TransferKafkaMessage message);

    TransferKafkaMessage toTransferKafkaMessage(TransferKafkaMessageDto messageDto);
}
