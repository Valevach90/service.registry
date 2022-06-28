package com.andersen.banking.service.payment.meeting_impl.mapper;

import com.andersen.banking.service.payment.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.service.payment.meeting_api.dto.CardResponseDto;
import com.andersen.banking.service.payment.meeting_api.dto.CardUpdateDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T10:37:17+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardResponseDto toCardResponseDto(Card card) {
        if ( card == null ) {
            return null;
        }

        CardResponseDto cardResponseDto = new CardResponseDto();

        cardResponseDto.setAccountId( cardAccountId( card ) );
        cardResponseDto.setId( card.getId() );
        cardResponseDto.setLastFourNumbers( card.getLastFourNumbers() );
        cardResponseDto.setValidFromDate( card.getValidFromDate() );
        cardResponseDto.setExpireDate( card.getExpireDate() );
        cardResponseDto.setHolderName( card.getHolderName() );

        return cardResponseDto;
    }

    @Override
    public Card toCard(CardUpdateDto cardUpdateDto) {
        if ( cardUpdateDto == null ) {
            return null;
        }

        Card card = new Card();

        card.setAccount( cardUpdateDtoToAccount( cardUpdateDto ) );
        card.setId( cardUpdateDto.getId() );
        card.setValidFromDate( cardUpdateDto.getValidFromDate() );
        card.setExpireDate( cardUpdateDto.getExpireDate() );
        card.setFirstTwelveNumbers( cardUpdateDto.getFirstTwelveNumbers() );
        card.setLastFourNumbers( cardUpdateDto.getLastFourNumbers() );
        card.setHolderName( cardUpdateDto.getHolderName() );

        return card;
    }

    @Override
    public Card toCard(CardRegistrationDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        Card card = new Card();

        card.setAccount( cardRegistrationDtoToAccount( cardDto ) );
        card.setValidFromDate( cardDto.getValidFromDate() );
        card.setExpireDate( cardDto.getExpireDate() );
        card.setFirstTwelveNumbers( cardDto.getFirstTwelveNumbers() );
        card.setLastFourNumbers( cardDto.getLastFourNumbers() );
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

    protected Account cardUpdateDtoToAccount(CardUpdateDto cardUpdateDto) {
        if ( cardUpdateDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( cardUpdateDto.getAccountId() );

        return account;
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
