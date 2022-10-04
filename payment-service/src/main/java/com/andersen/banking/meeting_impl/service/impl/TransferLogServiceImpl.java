package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.TransferLog;
import com.andersen.banking.meeting_db.repository.TransferLogRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.exception.TransferAccountException;
import com.andersen.banking.meeting_impl.exception.TransferLogAlreadyExistsException;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferLogService;
import java.util.UUID;
import javax.transaction.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferLogServiceImpl implements TransferLogService {

    private final TransferLogRepository transferLogRepository;

    @Override
    @Transactional
    public TransferLog create(TransferLog transferLog) {
        log.info("Creating transferLog: {}", transferLog);

        TransferLog savedTransferLog = transferLogRepository.save(transferLog);

        log.info("Created transferLog: {}", savedTransferLog);
        return savedTransferLog;
    }

    @Override
    @Transactional(readOnly = true)
    public TransferLog findById(UUID id) {
        log.debug("Finding transferLog by id: {}", id);

        TransferLog transferLog =
                transferLogRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException(TransferLog.class, id));

        log.debug("TransferLog with id {} was successfully found", id);
        return transferLog;
    }

    @Override
    public boolean isExist(UUID id) {
        log.info("Check transfer exist with id {}", id);
        return transferLogRepository.existsById(id);
    }

    @Override
    public boolean isExistStatus(UUID id, int status) {
        log.info("Check transfer exist with id {}", id);
        return transferLogRepository.existsByIdAndStatus(id, status);
    }

    @Override
    @Transactional
    public TransferLog update(TransferLog transferLog) {
        log.debug("Trying to update transferLog: {}", transferLog);

        findById(transferLog.getId());
        TransferLog updateTransferLog = transferLogRepository.save(transferLog);

        log.debug("Return updated account: {}", updateTransferLog);
        return updateTransferLog;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransferLog createTransferLogAccordingToRequestKafkaMessage(
            RequestTransferMessage requestTransferMessage)
            throws TransferAccountException {

        TransferLog transferLog = TransferLog.builder()
                .id(requestTransferMessage.getTransferId())
                .sourceNumber(requestTransferMessage.getSourceNumber())
                .sourcePaymentType(requestTransferMessage.getSourceType())
                .destinationNumber(requestTransferMessage.getDestinationNumber())
                .destinationPaymentType(requestTransferMessage.getDestinationType())
                .amount(requestTransferMessage.getAmount())
                .status(Status.STATUS_ACTIVE)
                .currency(requestTransferMessage.getCurrencyName())
                .userId(requestTransferMessage.getUserId())
                .build();

        if (!transferLogRepository.existsById(transferLog.getId())) {
            log.info("Creating transferLog: {}", transferLog);

            return transferLogRepository.save(transferLog);
        } else {

            throw new TransferAccountException(transferLog.getUserId());
        }
    }

    @Override
    @Transactional
    public void changeTransferLogStatus(UUID id, int status) throws NotFoundException {
        log.info("Changing status for transferLog : {} to {}", id, status);
        TransferLog transferLog = findById(id);
        transferLog.setStatus(status);
        transferLogRepository.save(transferLog);

        log.info("Return result of status for transferLog : {} to {}", id, status);
    }
}
