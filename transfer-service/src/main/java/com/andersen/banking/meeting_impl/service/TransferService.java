package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.Transfer;

public interface TransferService {

    /**
     * Return transferLog by id.
     *
     * @param id of the transferLog
     * @return transferLog
     */
    TransferResponseDto findById(Long id);


    /**
     * @param id
     * @param status
     */
    void changeTransferStatus(long id, int status);

    /**
     * @param transferRequestDto
     * @return
     */

    Transfer create(TransferRequestDto transferRequestDto);

    /**
     * @param transferId
     * @return
     */
    TransferStatusResponseDto getTransferStatus(Long transferId);

}
