package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.TransferDto;
import com.andersen.banking.deposit_api.dto.kafka.RequestTransferKafkaMessage;
import com.andersen.banking.deposit_db.entities.Transfer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-15T04:07:31+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TransferMapperImpl implements TransferMapper {

    @Override
    public TransferDto toTransferDto(Transfer transfer) {
        if ( transfer == null ) {
            return null;
        }

        TransferDto transferDto = new TransferDto();

        transferDto.setTransferId( transfer.getTransferId() );
        transferDto.setUserId( transfer.getUserId() );
        transferDto.setSourceNumber( transfer.getSourceNumber() );
        transferDto.setSourceType( transfer.getSourceType() );
        transferDto.setDestinationNumber( transfer.getDestinationNumber() );
        transferDto.setDestinationType( transfer.getDestinationType() );
        transferDto.setAmount( transfer.getAmount() );
        transferDto.setCurrencyName( transfer.getCurrencyName() );
        transferDto.setTime( transfer.getTime() );
        transferDto.setResult( transfer.getResult() );
        transferDto.setStatusDescription( transfer.getStatusDescription() );

        return transferDto;
    }

    @Override
    public Transfer toTransfer(TransferDto transferDto) {
        if ( transferDto == null ) {
            return null;
        }

        Transfer transfer = new Transfer();

        transfer.setTransferId( transferDto.getTransferId() );
        transfer.setUserId( transferDto.getUserId() );
        transfer.setSourceNumber( transferDto.getSourceNumber() );
        transfer.setSourceType( transferDto.getSourceType() );
        transfer.setDestinationNumber( transferDto.getDestinationNumber() );
        transfer.setDestinationType( transferDto.getDestinationType() );
        transfer.setAmount( transferDto.getAmount() );
        transfer.setCurrencyName( transferDto.getCurrencyName() );
        transfer.setTime( transferDto.getTime() );
        transfer.setResult( transferDto.getResult() );
        transfer.setStatusDescription( transferDto.getStatusDescription() );

        return transfer;
    }

    @Override
    public Transfer toTransfer(RequestTransferKafkaMessage message) {
        if ( message == null ) {
            return null;
        }

        Transfer transfer = new Transfer();

        transfer.setTransferId( message.getTransferId() );
        transfer.setUserId( message.getUserId() );
        transfer.setSourceNumber( message.getSourceNumber() );
        transfer.setSourceType( message.getSourceType() );
        transfer.setDestinationNumber( message.getDestinationNumber() );
        transfer.setDestinationType( message.getDestinationType() );
        transfer.setAmount( message.getAmount() );
        transfer.setCurrencyName( message.getCurrencyName() );

        return transfer;
    }

    @Override
    public List<Transfer> toTransfersDto(List<TransferDto> transfersDto) {
        if ( transfersDto == null ) {
            return null;
        }

        List<Transfer> list = new ArrayList<Transfer>( transfersDto.size() );
        for ( TransferDto transferDto : transfersDto ) {
            list.add( toTransfer( transferDto ) );
        }

        return list;
    }

    @Override
    public List<TransferDto> toTransfers(List<Transfer> transfers) {
        if ( transfers == null ) {
            return null;
        }

        List<TransferDto> list = new ArrayList<TransferDto>( transfers.size() );
        for ( Transfer transfer : transfers ) {
            list.add( toTransferDto( transfer ) );
        }

        return list;
    }
}
