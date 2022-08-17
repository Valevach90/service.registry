package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_db.entity.PaymentType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-17T15:12:49+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class PaymentTypeMapperImpl implements PaymentTypeMapper {

    @Override
    public PaymentTypeResponseDto paymentType2PaymentTypeResponseDto(PaymentType paymentType) {
        if ( paymentType == null ) {
            return null;
        }

        PaymentTypeResponseDto paymentTypeResponseDto = new PaymentTypeResponseDto();

        paymentTypeResponseDto.setId( paymentType.getId() );
        paymentTypeResponseDto.setName( paymentType.getName() );
        paymentTypeResponseDto.setDescription( paymentType.getDescription() );

        return paymentTypeResponseDto;
    }
}
