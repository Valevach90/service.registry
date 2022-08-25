package com.andersen.banking.service.payment.meeting_impl.controller;

import com.andersen.banking.service.payment.meeting_api.controller.RegularPaymentController;
import com.andersen.banking.service.payment.meeting_api.dto.RegularPaymentRequestDto;
import com.andersen.banking.service.payment.meeting_api.dto.RegularPaymentResponseDto;
import com.andersen.banking.service.payment.meeting_impl.exception.MapperException;
import com.andersen.banking.service.payment.meeting_impl.mapper.RegularPaymentMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.andersen.banking.service.payment.meeting_test.generators.RegularPaymentUnitTestGenerator.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RegularPaymentControllerImpl.class)
public class RegularPaymentControllerImplTest {

    private final RegularPaymentResponseDto regularPaymentResponseDto = populateRegularPaymentResponseDto();
    private final RegularPaymentRequestDto regularPaymentRequestDto = populateRegularPaymentRequestDto();

    @MockBean
    RegularPaymentMapper regularPaymentMapper;

    @Autowired
    RegularPaymentController regularPaymentController;

    @Test
    void create_ShouldReturnRegularPaymentResponseDto_WhenRegularPaymentRequestIsCorrect() {

    }

    @Test
    void create_ShouldThrowMapperException_WhenRegularPaymentRequestDtoIsIncorrect() {
        Mockito.when(regularPaymentMapper.toRegularPayment(regularPaymentRequestDto)).thenThrow(new MapperException());

        assertThrows(MapperException.class, () -> regularPaymentController.create(regularPaymentRequestDto));
    }
}
