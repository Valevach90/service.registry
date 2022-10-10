package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.TransferMoneyController;
import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_api.dto.StatusTransfer;
import com.andersen.banking.meeting_impl.mapper.TransferMapper;
import com.andersen.banking.meeting_impl.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransferMoneyControllerImpl implements TransferMoneyController {

    private final TransferService transferService;

    private final TransferManager transferManager;

    private final TransferMapper transferMapper;

    private final PaymentTypeService paymentTypeService;

    private final CurrencyService currencyService;

    private final TransferMoneyValidator transferMoneyValidator;


    @Override
    public List<TransferResponseDto> findAllByUserId(UUID userId, Pageable pageable) {
        log.info("Find all transfers by user_id: {}", userId);
        return transferService.findByUserId(userId, pageable).stream()
                .map(transferMapper::transfer2transferResponseDto)
                .toList();
    }

    @Override
    public TransferResponseDto findById(UUID transferId, UUID userId) {
        log.info("Find transfer by id : {} for user_id: {}", transferId, userId);
        Transfer transfer = transferService.findById(transferId);
        return transferMapper.transfer2transferResponseDto(transfer);
    }

    @Override
    public TransferStatusResponseDto findTransferStatusById(UUID transferId) {
        log.info("Transfer status by id : {}", transferId);
        return transferService.getTransferStatus(transferId);
    }

    @Override
    public ResponseEntity<TransferResponseDto> create(TransferRequestDto transferRequestDto) {
        log.info("Get request on transfer money from : {}", transferRequestDto);
        transferRequestDto.setStatusTransfer(StatusTransfer.PREPARING);
        return ResponseEntity.accepted().body(transferManager.run(transferRequestDto));
    }

    @Override
    public List<PaymentTypeResponseDto> getAllPaymentTypes() {
        log.info("Get request on get list payment types");
        return paymentTypeService.getAllPaymentTypes();
    }

    @Override
    public List<CurrencyResponseDto> getAllCurrency() {
        log.info("Get request on get list currencies");
        return currencyService.getAllCurrencies();
    }

}
