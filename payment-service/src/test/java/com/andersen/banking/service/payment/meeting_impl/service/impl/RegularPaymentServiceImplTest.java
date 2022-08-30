package com.andersen.banking.service.payment.meeting_impl.service.impl;

import com.andersen.banking.service.payment.meeting_db.entities.RegularPayment;
import com.andersen.banking.service.payment.meeting_db.repository.RegularPaymentRepository;
import com.andersen.banking.service.payment.meeting_impl.service.CardService;
import com.andersen.banking.service.payment.meeting_impl.service.RegularPaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static com.andersen.banking.service.payment.meeting_test.generators.RegularPaymentUnitTestGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RegularPaymentServiceImpl.class)
public class RegularPaymentServiceImplTest {

    private final RegularPayment returnedRegularPayment = populateRegularPayment();
    private final RegularPayment receivedRegularPayment = populateRegularPayment();

    @MockBean
    CardService cardService;

    @SpyBean
    RegularPaymentService regularPaymentService;

    @MockBean
    RegularPaymentRepository regularPaymentRepository;

    @Test
    void create_ShouldReturnRegularPayment_WhenReceivedRegularPaymentIsCorrect() {
        Mockito.when(cardService.findById(25L)).thenReturn(populateSourceCard());
        Mockito.when(regularPaymentRepository.save(receivedRegularPayment)).thenReturn(returnedRegularPayment);

        assertEquals(returnedRegularPayment, regularPaymentService.create(receivedRegularPayment));

    }
}
