package com.andersen.banking.meeting_impl.util;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;

public interface TransferRequestValidator {

    boolean validate(TransferRequestDto transferRequestDto);

}
