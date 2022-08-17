package com.andersen.banking.service.payment.meeting_impl.mapper;

import com.andersen.banking.service.payment.meeting_api.dto.TypeCardResponseDto;
import com.andersen.banking.service.payment.meeting_api.dto.TypeCardUpdateDto;
import com.andersen.banking.service.payment.meeting_db.entities.TypeCard;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-17T15:16:10+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TypeCardMapperImpl implements TypeCardMapper {

    @Override
    public TypeCardResponseDto typeCard2TypeCardResponseDto(TypeCard typeCard) {
        if ( typeCard == null ) {
            return null;
        }

        TypeCardResponseDto typeCardResponseDto = new TypeCardResponseDto();

        typeCardResponseDto.setId( typeCard.getId() );
        typeCardResponseDto.setPaymentSystem( typeCard.getPaymentSystem() );
        typeCardResponseDto.setTypeName( typeCard.getTypeName() );

        return typeCardResponseDto;
    }

    @Override
    public TypeCard typeCardUpdateDto2TypeCard(TypeCardUpdateDto typeCardUpdateDto) {
        if ( typeCardUpdateDto == null ) {
            return null;
        }

        TypeCard typeCard = new TypeCard();

        typeCard.setId( typeCardUpdateDto.getId() );
        typeCard.setPaymentSystem( typeCardUpdateDto.getPaymentSystem() );
        typeCard.setTypeName( typeCardUpdateDto.getTypeName() );

        return typeCard;
    }
}
