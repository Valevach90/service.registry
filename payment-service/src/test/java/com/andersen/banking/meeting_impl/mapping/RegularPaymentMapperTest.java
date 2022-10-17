package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.RegularPaymentRequestDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentResponseDto;
import com.andersen.banking.meeting_db.entities.RegularPayment;
import com.andersen.banking.meeting_impl.mapper.RegularPaymentMapper;
import com.andersen.banking.meeting_impl.mapper.RegularPaymentMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.andersen.banking.meeting_test.generators.RegularPaymentUnitTestGenerator.populateRegularPayment;
import static com.andersen.banking.meeting_test.generators.RegularPaymentUnitTestGenerator.populateRegularPaymentRequestDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RegularPaymentMapperImpl.class)
class RegularPaymentMapperTest {

    private final RegularPayment regularPayment = populateRegularPayment();
    private final RegularPaymentRequestDto regularPaymentRequestDto =
            populateRegularPaymentRequestDto();

    @Autowired RegularPaymentMapper regularPaymentMapper;

    @Test
    void whenRegularPaymentRequestDtoToRegularPayment_andOk() {
        var result = regularPaymentMapper.toRegularPayment(regularPaymentRequestDto);
        checkForEqualsRequest(result, regularPaymentRequestDto);
    }

    @Test
    void whenRegularPaymentToRegularPaymentResponseDto_AndOk() {
        var result = regularPaymentMapper.toRegularPaymentResponseDto(regularPayment);
        checkForEqualsResponse(result, regularPayment);
    }

    private void checkForEqualsRequest(RegularPayment first, RegularPaymentRequestDto second) {
        assertEquals(first.getDescription(), second.getDescription());
        assertEquals(first.getFirstDate(), second.getStartDate());
        assertEquals(first.getLastDate(), second.getLastDate());
        assertEquals(first.getSourceCard().getId(), second.getSourceCardId());
        assertEquals(first.getRecipientCard().getId(), second.getRecipientCardId());
        assertEquals(first.getAmount(), second.getAmount());
        assertEquals(first.getFrequency(), second.getFrequency());
    }

    private void checkForEqualsResponse(RegularPaymentResponseDto first, RegularPayment second) {
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getDescription(), second.getDescription());
        assertEquals(first.getStartDate(), second.getFirstDate());
        assertEquals(first.getNextDate(), second.getLastDate());
        assertEquals(first.getSourceCardId(), second.getSourceCard().getId());
        assertEquals(first.getRecipientCardId(), second.getRecipientCard().getId());
        assertEquals(first.getAmount(), second.getAmount());
        assertEquals(first.getFrequency(), second.getFrequency());
    }
}
