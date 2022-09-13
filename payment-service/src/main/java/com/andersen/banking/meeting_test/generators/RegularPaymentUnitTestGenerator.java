package com.andersen.banking.meeting_test.generators;

import com.andersen.banking.meeting_api.dto.RegularPaymentRequestDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentResponseDto;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.RegularPayment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegularPaymentUnitTestGenerator {

    public static UUID PAYMENT_ID = UUID.randomUUID();

    public static UUID SOURCE_CARD_ID = UUID.randomUUID();

    public static UUID RECEPIENT_CARD_ID = UUID.randomUUID();

    public static RegularPayment populateRegularPayment() {
        RegularPayment regularPayment = new RegularPayment();

        regularPayment.setId(PAYMENT_ID);
        regularPayment.setDescription("Mobile phone payment");
        regularPayment.setFirstDate(LocalDate.of(2022, 9, 1));
        regularPayment.setLastDate(LocalDate.of(2023, 9, 1));
        regularPayment.setSourceCard(populateSourceCard());
        regularPayment.setRecipientCard(populateRecipientCard());
        regularPayment.setAmount(500L);
        regularPayment.setFrequency("Monthly");

        return regularPayment;
    }

    public static RegularPaymentRequestDto populateRegularPaymentRequestDto() {
        RegularPaymentRequestDto regularPaymentRequestDto = new RegularPaymentRequestDto();

        regularPaymentRequestDto.setDescription("Mobile phone payment");
        regularPaymentRequestDto.setFirstDate(LocalDate.of(2022, 9, 1));
        regularPaymentRequestDto.setLastDate(LocalDate.of(2023, 9, 1));
        regularPaymentRequestDto.setSourceCardId(SOURCE_CARD_ID);
        regularPaymentRequestDto.setRecipientCardId(RECEPIENT_CARD_ID);
        regularPaymentRequestDto.setAmount(500L);
        regularPaymentRequestDto.setFrequency("Monthly");

        return regularPaymentRequestDto;
    }

    public static RegularPaymentResponseDto populateRegularPaymentResponseDto() {
        RegularPaymentResponseDto regularPaymentResponseDto = new RegularPaymentResponseDto();

        regularPaymentResponseDto.setId(PAYMENT_ID);
        regularPaymentResponseDto.setDescription("Mobile phone payment");
        regularPaymentResponseDto.setFirstDate(LocalDate.of(2022, 9, 1));
        regularPaymentResponseDto.setLastDate(LocalDate.of(2023, 9, 1));
        regularPaymentResponseDto.setSourceCardId(SOURCE_CARD_ID);
        regularPaymentResponseDto.setRecipientCardId(RECEPIENT_CARD_ID);
        regularPaymentResponseDto.setAmount(500L);
        regularPaymentResponseDto.setFrequency("Monthly");

        return regularPaymentResponseDto;
    }

    public static Card populateSourceCard() {
        Card card = new Card();
        card.setId(SOURCE_CARD_ID);

        return card;
    }

    public static Card populateRecipientCard() {
        Card card = new Card();
        card.setId(RECEPIENT_CARD_ID);

        return card;
    }
}
