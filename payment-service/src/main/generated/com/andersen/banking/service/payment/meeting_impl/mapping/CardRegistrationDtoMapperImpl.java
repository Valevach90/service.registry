package com.andersen.banking.service.payment.meeting_impl.mapping;

import com.andersen.banking.service.payment.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-25T14:16:53+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class CardRegistrationDtoMapperImpl implements CardRegistrationDtoMapper {

    @Override
    public Card toCard(CardRegistrationDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        Card card = new Card();

        card.setAccount( cardRegistrationDtoToAccount( cardDto ) );
        card.setCardNumber( cardDto.getCardNumber() );
        card.setExpirationDate( cardDto.getExpirationDate() );
        card.setPinCode( cardDto.getPinCode() );
        card.setHolderName( cardDto.getHolderName() );

        return card;
    }

    protected Account cardRegistrationDtoToAccount(CardRegistrationDto cardRegistrationDto) {
        if ( cardRegistrationDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( cardRegistrationDto.getAccountId() );

        return account;
    }
}
