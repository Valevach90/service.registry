package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.TransferMoneyController;
import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransferMoneyControllerImpl implements TransferMoneyController {


    @Override
    public List<TransferResponseDto> findAllByUserId(Long userId) {
        log.info("Find all transfers by user_id: {}", userId);

        return null;
    }

    @Override
    public TransferResponseDto findById(Long userId, Long transferId) {
        log.info("Find transfer by id : {} for user_id: {}", transferId, userId);
        return null;
    }

    @Override
    public TransferResponseDto create(TransferRequestDto transferRequestDto) {
        log.info("Get request on transfer money from : {} {}",
                transferRequestDto.getSourceType(), transferRequestDto.getSourceNumber());

        return null;
    }

}
