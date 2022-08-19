package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TransferService {

    /**
     * Return transferLog by id.
     *
     * @param id of the transferLog
     * @return transferLog
     */
    TransferResponseDto findById(UUID id);


    /**
     * @param id
     * @param status
     */
    void changeTransferStatus(UUID id, int status);

    /**
     * @param transferRequestDto
     * @return
     */

    Transfer create(TransferRequestDto transferRequestDto);

    /**
     * @param transferId
     * @return
     */
    TransferStatusResponseDto getTransferStatus(UUID transferId);


    /**
     * @param id
     * @param pageable
     * @return
     */
    List<TransferResponseDto> findByUserId(Long id, Pageable pageable);

}
