package com.andersen.banking.service.payment.meeting_test.generators;

import com.andersen.banking.service.payment.meeting_api.dto.RegularPaymentRequestDto;
import com.andersen.banking.service.payment.meeting_api.dto.RegularPaymentResponseDto;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_db.entities.RegularPayment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegularPaymentUnitTestGenerator {

    public static RegularPayment populateRegularPayment() {
        RegularPayment regularPayment = new RegularPayment();

        regularPayment.setId(1L);
        regularPayment.setDescription("Mobile phone payment");
        regularPayment.setFirstDate(LocalDate.of(2022, 9, 1));
        regularPayment.setLastDate(LocalDate.of(2023, 9, 1));
        regularPayment.setSourceCard(populateSourceCard());
        regularPayment.setRecipientCard(populateRecipientCard());
        regularPayment.setAmount(500L);
        regularPayment.setFrequency("Monthly");

        return regularPayment;
    }

    public static RegularPaymentRequestDto populateRegularPaymentRequestDto () {
        RegularPaymentRequestDto regularPaymentRequestDto = new RegularPaymentRequestDto();

        regularPaymentRequestDto.setDescription("Mobile phone payment");
        regularPaymentRequestDto.setFirstDate(LocalDate.of(2022, 9, 1));
        regularPaymentRequestDto.setLastDate(LocalDate.of(2023, 9, 1));
        regularPaymentRequestDto.setSourceCardId(25L);
        regularPaymentRequestDto.setRecipientCardId(35L);
        regularPaymentRequestDto.setAmount(500L);
        regularPaymentRequestDto.setFrequency("Monthly");

        return regularPaymentRequestDto;
    }

    public static RegularPaymentResponseDto populateRegularPaymentResponseDto () {
        RegularPaymentResponseDto regularPaymentResponseDto = new RegularPaymentResponseDto();

        regularPaymentResponseDto.setId(1L);
        regularPaymentResponseDto.setDescription("Mobile phone payment");
        regularPaymentResponseDto.setFirstDate(LocalDate.of(2022, 9, 1));
        regularPaymentResponseDto.setLastDate(LocalDate.of(2023, 9, 1));
        regularPaymentResponseDto.setSourceCardId(25L);
        regularPaymentResponseDto.setRecipientCardId(35L);
        regularPaymentResponseDto.setAmount(500L);
        regularPaymentResponseDto.setFrequency("Monthly");

        return regularPaymentResponseDto;
    }

    public static Card populateSourceCard() {
        Card card = new Card();
        card.setId(25L);

        return card;
    }

    public static Card populateRecipientCard() {
        Card card = new Card();
        card.setId(35L);

        return card;
    }
}
