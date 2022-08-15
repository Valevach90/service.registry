package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.TransferMoneyController;
import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransferMoneyControllerImpl implements TransferMoneyController {

    private final TransferMoneyService transferMoneyService;


    @Override
    public List<TransferResponseDto> findAllByUserId(Long userId) {
        log.info("Find all transfers by user_id: {}", userId);
        return transferMoneyService.findAllByUserId(userId);
    }

    @Override
    public TransferResponseDto findById(Long userId, Long transferId) {
        log.info("Find transfer by id : {} for user_id: {}", transferId, userId);
        return null;
    }

    @Override
    public TransferStatusResponseDto findTransferStatusById(Long transferId) {
        log.info("Transfer status by id : {}", transferId);
        return transferMoneyService.getTransferStatus(transferId);
    }

    @Override
    public TransferResponseDto create(TransferRequestDto transferRequestDto) {
        log.info("Get request on transfer money from : {}", transferRequestDto.getSourceNumber());
        return null;
    }

    @Override
    public List<PaymentTypeResponseDto> getAllPaymentTypes() {
        log.info("Get request on get list payment types");
        return transferMoneyService.getAllPaymentTypes();
    }

    @Override
    public List<CurrencyResponseDto> getAllCurrency() {
        log.info("Get request on get list currencies");
        return transferMoneyService.getAllCurrencies();
    }

}
