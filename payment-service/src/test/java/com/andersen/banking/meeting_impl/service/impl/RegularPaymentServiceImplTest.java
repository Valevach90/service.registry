package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.RegularPayment;
import com.andersen.banking.meeting_db.repository.RegularPaymentRepository;
import com.andersen.banking.meeting_impl.service.CardService;
import com.andersen.banking.meeting_impl.service.RegularPaymentService;
import com.andersen.banking.meeting_test.generators.RegularPaymentUnitTestGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RegularPaymentServiceImpl.class)
public class RegularPaymentServiceImplTest {

    private final RegularPayment returnedRegularPayment = RegularPaymentUnitTestGenerator.populateRegularPayment();
    private final RegularPayment receivedRegularPayment = RegularPaymentUnitTestGenerator.populateRegularPayment();

    @MockBean
    CardService cardService;

    @SpyBean
    RegularPaymentService regularPaymentService;

    @MockBean
    RegularPaymentRepository regularPaymentRepository;

    @Test
    void create_ShouldReturnRegularPayment_WhenReceivedRegularPaymentIsCorrect() {
        Mockito.when(cardService.findById(25L)).thenReturn(RegularPaymentUnitTestGenerator.populateSourceCard());
        Mockito.when(regularPaymentRepository.save(receivedRegularPayment)).thenReturn(returnedRegularPayment);

        Assertions.assertEquals(returnedRegularPayment, regularPaymentService.create(receivedRegularPayment));

    }
}
