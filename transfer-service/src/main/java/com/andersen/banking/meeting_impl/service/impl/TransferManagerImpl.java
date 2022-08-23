package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.exception.RequestValidationException;
import com.andersen.banking.meeting_impl.service.TransferExecutor;
import com.andersen.banking.meeting_impl.service.TransferManager;
import com.andersen.banking.meeting_impl.service.TransferMoneyValidator;
import com.andersen.banking.meeting_impl.util.TransferRequestValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TransferManagerImpl implements TransferManager {

    private final TransferMoneyValidator transferMoneyValidator;
    private List<TransferRequestValidator> requestValidators;

    private final TransferExecutorInternalService transferExecutorInternalService;

    @Override
    @Transactional(rollbackFor = NotFoundException.class)
    public TransferResponseDto run(TransferRequestDto transferRequestDto) throws RuntimeException {
        log.info("Execute for : {}", transferRequestDto);

        if (transferMoneyValidator.validate(transferRequestDto, requestValidators)) {
            TransferExecutor executor = getExecutor(transferRequestDto);
            TransferResponseDto transferResponseDto = executor.execute(transferRequestDto);

            log.info("Return response : {}", transferResponseDto);
            return transferResponseDto;
        } else {
            log.error("Failed verification for payment and currency fields for : {}", transferRequestDto);
            throw new RequestValidationException(transferRequestDto.getClass());
        }

    }


    @Override
    public TransferExecutor getExecutor(TransferRequestDto transferRequestDto) {

        if (transferRequestDto.getSourcePaymentTypeId().equals(transferRequestDto.getDestinationPaymentTypeId())) {
            log.info("Return internal service executor.");

            return transferExecutorInternalService;
        } else {
            throw new RuntimeException("An operation is not support for request : "
                    .concat(transferRequestDto.toString()));
        }
    }
}
