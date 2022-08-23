package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_impl.util.TransferRequestValidator;

import java.util.List;

public interface TransferMoneyValidator {

    boolean validate(TransferRequestDto transferRequestDto, List<TransferRequestValidator> requestValidators);

}
