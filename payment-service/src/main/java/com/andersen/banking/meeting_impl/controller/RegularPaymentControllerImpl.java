package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.dto.RegularPaymentRequestDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentResponseDto;
import com.andersen.banking.meeting_db.entities.RegularPayment;
import com.andersen.banking.meeting_api.controller.RegularPaymentController;
import com.andersen.banking.meeting_impl.mapper.RegularPaymentMapper;
import com.andersen.banking.meeting_impl.service.RegularPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegularPaymentControllerImpl implements RegularPaymentController {

    private final RegularPaymentService regularPaymentService;
    private final RegularPaymentMapper regularPaymentMapper;

    @Override
    public RegularPaymentResponseDto create(RegularPaymentRequestDto regularPaymentRequestDto) {
        log.trace("Receiving request for creating regular payment: {}", regularPaymentRequestDto);

        RegularPayment regularPayment = regularPaymentMapper
                .toRegularPayment(regularPaymentRequestDto);

        RegularPaymentResponseDto regularPaymentResponseDto = regularPaymentMapper
                .toDto(regularPaymentService.create(regularPayment));

        log.trace("Returning created regular payment: {}", regularPaymentResponseDto);
        return regularPaymentResponseDto;
    }
}
