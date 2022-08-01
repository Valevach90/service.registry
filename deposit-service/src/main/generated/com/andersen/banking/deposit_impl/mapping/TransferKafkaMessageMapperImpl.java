package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.kafka.TransferKafkaMessageDto;
import com.andersen.banking.deposit_db.entities.TransferKafkaMessage;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-01T17:52:56+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TransferKafkaMessageMapperImpl implements TransferKafkaMessageMapper {

    @Override
    public TransferKafkaMessageDto toTransferKafkaMessageDto(TransferKafkaMessage message) {
        if ( message == null ) {
            return null;
        }

        TransferKafkaMessageDto transferKafkaMessageDto = new TransferKafkaMessageDto();

        transferKafkaMessageDto.setId( message.getId() );
        transferKafkaMessageDto.setUserId( message.getUserId() );
        transferKafkaMessageDto.setSourceNumber( message.getSourceNumber() );
        transferKafkaMessageDto.setSourceType( message.getSourceType() );
        transferKafkaMessageDto.setDestinationNumber( message.getDestinationNumber() );
        transferKafkaMessageDto.setDestinationType( message.getDestinationType() );
        transferKafkaMessageDto.setAmount( message.getAmount() );
        transferKafkaMessageDto.setCurrencyName( message.getCurrencyName() );
        transferKafkaMessageDto.setStatusName( message.getStatusName() );
        transferKafkaMessageDto.setStatusDescription( message.getStatusDescription() );

        return transferKafkaMessageDto;
    }

    @Override
    public TransferKafkaMessage toTransferKafkaMessage(TransferKafkaMessageDto message) {
        if ( message == null ) {
            return null;
        }

        TransferKafkaMessage transferKafkaMessage = new TransferKafkaMessage();

        transferKafkaMessage.setId( message.getId() );
        transferKafkaMessage.setUserId( message.getUserId() );
        transferKafkaMessage.setSourceNumber( message.getSourceNumber() );
        transferKafkaMessage.setSourceType( message.getSourceType() );
        transferKafkaMessage.setDestinationNumber( message.getDestinationNumber() );
        transferKafkaMessage.setDestinationType( message.getDestinationType() );
        transferKafkaMessage.setAmount( message.getAmount() );
        transferKafkaMessage.setCurrencyName( message.getCurrencyName() );
        transferKafkaMessage.setStatusName( message.getStatusName() );
        transferKafkaMessage.setStatusDescription( message.getStatusDescription() );

        return transferKafkaMessage;
    }
}
