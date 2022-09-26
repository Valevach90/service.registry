package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TransferService {

    /**
     * Return transferLog by id.
     *
     * @param id of the transferLog
     * @return transferLog
     */
    Transfer findById(UUID id);

    /**
     * Assume transfer is available
     *
     * @param id of the transferLog
     * @return transferLog
     */
    boolean isEqualStatus(UUID id, int status);


    /**
     * @param id
     * @param status
     */
    void changeTransferStatus(UUID id, int status);

    /**
     * @param id
     * @param status
     */
    void changeTransferStatus(UUID id, int status, String service);

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
    Page<Transfer> findByUserId(UUID id, Pageable pageable);

    /**
     * @param id
     * @return
     */
    String getService(UUID id);
}
