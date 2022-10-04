package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_db.entity.Transfer;


public interface TransferExecutor {


    /**
     * @param transfer
     * @return
     */
    void execute(Transfer transfer);

}
