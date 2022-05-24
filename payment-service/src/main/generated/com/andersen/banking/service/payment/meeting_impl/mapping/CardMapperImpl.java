package com.andersen.banking.service.payment.meeting_impl.mapping;

import com.andersen.banking.service.payment.meeting_api.dto.CardDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-24T12:12:53+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDto toCardDto(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDto cardDto = new CardDto();

        cardDto.setAccountId( cardAccountId( card ) );
        cardDto.setId( card.getId() );
        cardDto.setCardNumber( card.getCardNumber() );
        cardDto.setExpirationDate( card.getExpirationDate() );
        cardDto.setPinCode( card.getPinCode() );
        cardDto.setHolderName( card.getHolderName() );

        return cardDto;
    }

    @Override
    public Card toCard(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        Card card = new Card();

        card.setAccount( cardDtoToAccount( cardDto ) );
        card.setId( cardDto.getId() );
        card.setCardNumber( cardDto.getCardNumber() );
        card.setExpirationDate( cardDto.getExpirationDate() );
        card.setPinCode( cardDto.getPinCode() );
        card.setHolderName( cardDto.getHolderName() );

        return card;
    }

    private Long cardAccountId(Card card) {
        if ( card == null ) {
            return null;
        }
        Account account = card.getAccount();
        if ( account == null ) {
            return null;
        }
        Long id = account.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Account cardDtoToAccount(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( cardDto.getAccountId() );

        return account;
    }
}
