package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.DepositTypeDto;
import com.andersen.banking.deposit_db.entities.DepositType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2022-07-29T01:41:43+0300",
=======
    date = "2022-08-25T11:44:06+0300",
>>>>>>> add_money_transfers_to_deposit_service
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class DepositTypeMapperImpl implements DepositTypeMapper {

    @Override
    public DepositTypeDto toDepositTypeDto(DepositType depositType) {
        if ( depositType == null ) {
            return null;
        }

        DepositTypeDto depositTypeDto = new DepositTypeDto();

        depositTypeDto.setId( depositType.getId() );
        depositTypeDto.setName( depositType.getName() );

        return depositTypeDto;
    }

    @Override
    public DepositType toDepositType(DepositTypeDto depositTypeDto) {
        if ( depositTypeDto == null ) {
            return null;
        }

        DepositType depositType = new DepositType();

        depositType.setId( depositTypeDto.getId() );
        depositType.setName( depositTypeDto.getName() );

        return depositType;
    }
}
