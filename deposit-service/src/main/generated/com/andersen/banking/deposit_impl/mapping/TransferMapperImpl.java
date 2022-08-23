package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.TransferDto;
import com.andersen.banking.deposit_db.entities.Transfer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-23T06:46:31+0300",
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

        transferDto.setId( transfer.getId() );
        transferDto.setFromCardNumber( transfer.getFromCardNumber() );
        transferDto.setToCardNumber( transfer.getToCardNumber() );
        transferDto.setAmount( transfer.getAmount() );
        transferDto.setDate( transfer.getDate() );
        transferDto.setSuccessStatus( transfer.getSuccessStatus() );

        return transferDto;
    }

    @Override
    public Transfer toTransfer(TransferDto transferDto) {
        if ( transferDto == null ) {
            return null;
        }

        Transfer transfer = new Transfer();

        transfer.setId( transferDto.getId() );
        transfer.setFromCardNumber( transferDto.getFromCardNumber() );
        transfer.setToCardNumber( transferDto.getToCardNumber() );
        transfer.setAmount( transferDto.getAmount() );
        transfer.setDate( transferDto.getDate() );
        transfer.setSuccessStatus( transferDto.getSuccessStatus() );

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
