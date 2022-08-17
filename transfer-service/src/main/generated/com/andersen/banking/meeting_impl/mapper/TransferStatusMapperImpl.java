package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-17T15:12:49+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TransferStatusMapperImpl implements TransferStatusMapper {

    @Override
    public TransferStatusResponseDto transferStatus2TransferStatusResponseDto(TransferStatus transferStatus) {
        if ( transferStatus == null ) {
            return null;
        }

        TransferStatusResponseDto transferStatusResponseDto = new TransferStatusResponseDto();

        transferStatusResponseDto.setId( transferStatus.getId() );
        transferStatusResponseDto.setName( transferStatus.getName() );
        transferStatusResponseDto.setDescription( transferStatus.getDescription() );

        return transferStatusResponseDto;
    }

    @Override
    public TransferStatus TransferStatusResponseDto2transferStatus(TransferStatusResponseDto transferStatusResponseDto) {
        if ( transferStatusResponseDto == null ) {
            return null;
        }

        TransferStatus transferStatus = new TransferStatus();

        transferStatus.setId( transferStatusResponseDto.getId() );
        transferStatus.setName( transferStatusResponseDto.getName() );
        transferStatus.setDescription( transferStatusResponseDto.getDescription() );

        return transferStatus;
    }
}
