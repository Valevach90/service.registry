package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.RegularPaymentController;
import com.andersen.banking.meeting_api.dto.RegularPaymentRequestDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentResponseDto;
import com.andersen.banking.meeting_db.entities.RegularPayment;
import com.andersen.banking.meeting_impl.exception.MapperException;
import com.andersen.banking.meeting_impl.mapper.RegularPaymentMapper;
import com.andersen.banking.meeting_impl.service.RegularPaymentService;
import com.andersen.banking.meeting_test.generators.RegularPaymentUnitTestGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = RegularPaymentControllerImpl.class)
public class RegularPaymentControllerImplTest {

    private final RegularPayment regularPayment =
            RegularPaymentUnitTestGenerator.populateRegularPayment();
    private final RegularPaymentResponseDto regularPaymentResponseDto =
            RegularPaymentUnitTestGenerator.populateRegularPaymentResponseDto();
    private final RegularPaymentRequestDto regularPaymentRequestDto =
            RegularPaymentUnitTestGenerator.populateRegularPaymentRequestDto();

    @MockBean RegularPaymentMapper regularPaymentMapper;

    @MockBean RegularPaymentService regularPaymentService;

    @Autowired RegularPaymentController regularPaymentController;

    @Test
    void create_ShouldReturnRegularPaymentResponseDto_WhenRegularPaymentRequestIsCorrect() {
        Mockito.when(regularPaymentMapper.toRegularPayment(regularPaymentRequestDto))
                .thenReturn(regularPayment);
        Mockito.when(regularPaymentMapper.toRegularPaymentResponseDto(regularPayment))
                .thenReturn(regularPaymentResponseDto);
        Mockito.when(regularPaymentService.create(regularPayment)).thenReturn(regularPayment);

        assertEquals(
                regularPaymentResponseDto,
                regularPaymentController.create(regularPaymentRequestDto));
    }

    @Test
    void create_ShouldThrowMapperException_WhenRegularPaymentRequestDtoIsIncorrect() {
        Mockito.when(regularPaymentMapper.toRegularPayment(regularPaymentRequestDto))
                .thenThrow(new MapperException());

        assertThrows(
                MapperException.class,
                () -> regularPaymentController.create(regularPaymentRequestDto));
    }
}
