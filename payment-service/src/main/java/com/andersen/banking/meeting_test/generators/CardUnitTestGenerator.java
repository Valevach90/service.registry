package com.andersen.banking.meeting_test.generators;

import com.andersen.banking.meeting_api.dto.*;
import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.TypeCard;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardUnitTestGenerator {

    private static final UUID CARD_ID = UUID.randomUUID();
    private static final UUID ACCOUNT_ID = UUID.randomUUID();
    private static final UUID TYPE_ID = UUID.randomUUID();

    public static Card populateCard() {
        Card card = new Card();
        card.setId(CARD_ID);
        card.setAccount(populateAccount(new Account()));
        card.setValidFromDate(LocalDate.of(2021, 10, 23));
        card.setExpireDate(LocalDate.of(2024, 10, 23));
        card.setFirstTwelveNumbers("123456789012");
        card.setLastFourNumbers("4567");
        card.setHolderName("Ivanov Ivan Ivanovich");
        card.setTypeCard(populateTypeCard(new TypeCard()));

        return card;
    }

    public static Card populateCardIncludeAccountWithBalanceAndCurrency() {
        Card card = populateCard();
        card.setAccount(populateAccountWithBalanceAndCurrency(new Account()));

        return card;
    }

    public static CardUpdateDto populateCardUpdateDto() {
        CardUpdateDto cardUpdateDto = new CardUpdateDto();
        cardUpdateDto.setId(CARD_ID);
        cardUpdateDto.setAccountId(ACCOUNT_ID);
        cardUpdateDto.setFirstTwelveNumbers("123456789012");
        cardUpdateDto.setLastFourNumbers("4567");
        cardUpdateDto.setValidFromDate(LocalDate.of(2021, 10, 23));
        cardUpdateDto.setExpireDate(LocalDate.of(2024, 10, 23));
        cardUpdateDto.setHolderName("Ivanov Ivan Ivanovich");
        cardUpdateDto.setPaymentSystem("VISA");
        cardUpdateDto.setTypeName("SILVER");
        return cardUpdateDto;
    }

    public static CardRegistrationDto populateCardRegistrationDto() {
        CardRegistrationDto cardRegistrationDto = new CardRegistrationDto();
        cardRegistrationDto.setAccountId(ACCOUNT_ID);
        cardRegistrationDto.setFirstTwelveNumbers("123456789012");
        cardRegistrationDto.setLastFourNumbers("4567");
        cardRegistrationDto.setValidFromDate(LocalDate.of(2021, 10, 23));
        cardRegistrationDto.setExpireDate(LocalDate.of(2024, 10, 23));
        cardRegistrationDto.setHolderName("Ivanov Ivan Ivanovich");
        cardRegistrationDto.setPaymentSystem("VISA");
        cardRegistrationDto.setTypeName("SILVER");
        return cardRegistrationDto;
    }

    public static CardResponseDto populateCardResponseDto() {
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setId(CARD_ID);
        cardResponseDto.setAccountId(ACCOUNT_ID);
        cardResponseDto.setFirstTwelveNumbersHash("123456789012");
        cardResponseDto.setLastFourNumbers("4567");
        cardResponseDto.setValidFromDate(LocalDate.of(2021, 10, 23));
        cardResponseDto.setExpireDate(LocalDate.of(2024, 10, 23));
        cardResponseDto.setHolderName("Ivanov Ivan Ivanovich");
        cardResponseDto.setPaymentSystem("VISA");
        cardResponseDto.setTypeName("SILVER");
        cardResponseDto.setBalance(0);
        cardResponseDto.setCurrency("BEL");

        return cardResponseDto;
    }

    public static Account populateAccount(Account account) {
        account.setId(ACCOUNT_ID);
        return account;
    }

    public static Account populateAccountWithBalanceAndCurrency(Account a) {
        Account account = populateAccount(a);
        account.setBalance(0);
        account.setCurrency("BEL");

        return account;
    }

    public static TypeCard populateTypeCard(TypeCard typeCard) {
        typeCard.setId(TYPE_ID);
        typeCard.setPaymentSystem("VISA");
        typeCard.setTypeName("SILVER");

        return typeCard;
    }

    public static TypeCard populateTypeCard() {
        TypeCard typeCard = new TypeCard();
        typeCard.setId(TYPE_ID);
        typeCard.setPaymentSystem("VISA");
        typeCard.setTypeName("SILVER");
        return typeCard;
    }

    public static TypeCardResponseDto populateTypeCardResponseDto() {
        TypeCardResponseDto typeCardResponseDto = new TypeCardResponseDto();
        typeCardResponseDto.setId(TYPE_ID);
        typeCardResponseDto.setPaymentSystem("VISA");
        typeCardResponseDto.setTypeName("SILVER");
        return typeCardResponseDto;
    }

    public static TypeCardUpdateDto populateTypeCardUpdateDto() {
        TypeCardUpdateDto typeCardUpdateDto = new TypeCardUpdateDto();
        typeCardUpdateDto.setId(TYPE_ID);
        typeCardUpdateDto.setPaymentSystem("VISA");
        typeCardUpdateDto.setTypeName("SILVER");
        return typeCardUpdateDto;
    }
}
