package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_db.entity.PaymentType;

import java.util.List;
import java.util.UUID;

public interface PaymentTypeService {

    /**
     * @return List of PaymentTypeResponseDto
     */

    List<PaymentTypeResponseDto> getAllPaymentTypes();

    /**
     * @param id
     * @return
     */
    PaymentType getPaymentTypeById(UUID id);

}
