package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;


public interface TransferManager {
    /**
     * @param transferRequestDto
     * @return
     */
    TransferResponseDto run(TransferRequestDto transferRequestDto);
}
