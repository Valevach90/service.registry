package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.RegularPaymentController;
import com.andersen.banking.meeting_api.dto.RegularPaymentRequestDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentResponseDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentUpdateDto;
import com.andersen.banking.meeting_db.entities.RegularPayment;
import com.andersen.banking.meeting_impl.mapper.RegularPaymentMapper;
import com.andersen.banking.meeting_impl.service.RegularPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegularPaymentControllerImpl implements RegularPaymentController {

    private final RegularPaymentService regularPaymentService;
    private final RegularPaymentMapper regularPaymentMapper;

    @Override
    public RegularPaymentResponseDto create(RegularPaymentRequestDto regularPaymentRequestDto) {
        log.trace("Receiving request for creating regular payment: {}", regularPaymentRequestDto);

        RegularPayment regularPayment =
                regularPaymentMapper.toRegularPayment(regularPaymentRequestDto);

        RegularPaymentResponseDto regularPaymentResponseDto =
                regularPaymentMapper.toRegularPaymentResponseDto(regularPaymentService.create(regularPayment));

        log.trace("Returning created regular payment: {}", regularPaymentResponseDto);
        return regularPaymentResponseDto;
    }

    @Override
    public RegularPaymentResponseDto update(RegularPaymentUpdateDto regularPaymentUpdateDto) {
        log.trace("Receiving request for updating regular payment: {}", regularPaymentUpdateDto);

        RegularPayment regularPayment =
                regularPaymentMapper.toRegularPayment(regularPaymentUpdateDto);

        RegularPaymentResponseDto regularPaymentResponseDto =
                regularPaymentMapper.toRegularPaymentResponseDto(regularPaymentService.update(regularPayment));

        log.trace("Returning updated regular payment: {}", regularPaymentResponseDto);
        return regularPaymentResponseDto;
    }

    @Override
    public RegularPaymentResponseDto findById(UUID id) {
        log.trace("Receiving regular payment id: {}", id);

        RegularPaymentResponseDto regularPaymentResponseDto = regularPaymentMapper.toRegularPaymentResponseDto(regularPaymentService.findById(id));

        log.trace("Returning regular payment with id: {}", id);
        return regularPaymentResponseDto;
    }

    @Override
    public Page<RegularPaymentResponseDto> findAll(Pageable pageable) {
        log.trace("Receiving request for getting all regular payments");

        Page<RegularPaymentResponseDto> result = regularPaymentService.findAll(pageable).map(regularPaymentMapper::toRegularPaymentResponseDto);

        log.trace("Returning list of card products: {}", result.getContent());
        return result;
    }

    @Override
    public void deleteById(UUID id) {
        log.trace("Receiving request for deleting regular payment by id: {}", id);

        regularPaymentService.deleteById(id);
    }


}
