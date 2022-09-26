package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.exception.RequestValidationException;
import com.andersen.banking.meeting_impl.mapper.TransferMapper;
import com.andersen.banking.meeting_impl.service.TransferManager;
import com.andersen.banking.meeting_impl.service.TransferMoneyValidator;
import com.andersen.banking.meeting_impl.service.TransferService;
import com.andersen.banking.meeting_impl.util.TransferRequestValidator;
import javax.transaction.Status;
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
    private final TransferMapper transferMapper;
    private final TransferService transferService;

    private final TransferExecutorImpl transferExecutorImpl;

    //private final ReplenishmentPaymentTransferExecutor replenishmentPaymentTransferExecutor;

    @Override
    @Transactional(rollbackFor = NotFoundException.class)
    public TransferResponseDto run(TransferRequestDto transferRequestDto) throws RuntimeException {
        log.info("Execute for : {}", transferRequestDto);

        if (transferMoneyValidator.validate(transferRequestDto, requestValidators)) {
            Transfer transfer = transferService.create(transferRequestDto);
            transferExecutorImpl.execute(transfer);
            transferService.changeTransferStatus(transfer.getId(), Status.STATUS_PREPARING);
            TransferResponseDto transferResponseDto = transferMapper.transfer2transferResponseDto(
                    transfer);
            log.info("Return response : {}", transferResponseDto);
            return transferResponseDto;
        } else {
            log.error("Failed verification for payment and currency fields for : {}", transferRequestDto);
            throw new RequestValidationException(transferRequestDto.getClass());
        }
    }
}
