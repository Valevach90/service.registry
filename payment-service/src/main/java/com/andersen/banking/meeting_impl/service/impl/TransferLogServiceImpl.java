package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.TransferLog;
import com.andersen.banking.meeting_db.repository.TransferLogRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.exception.TransferAccountException;
import com.andersen.banking.meeting_impl.exception.TransferLogAlreadyExistsException;
import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Status;
import java.util.UUID;

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
            RequestKafkaTransferMessage requestKafkaTransferMessage)
            throws TransferLogAlreadyExistsException {
        TransferLog transferLog = new TransferLog();
        transferLog.setId(requestKafkaTransferMessage.getTransferId());
        transferLog.setSourceNumber(requestKafkaTransferMessage.getSourceNumber());
        transferLog.setSourcePaymentType(requestKafkaTransferMessage.getSourceType());
        transferLog.setDestinationNumber(requestKafkaTransferMessage.getDestinationNumber());
        transferLog.setDestinationPaymentType(requestKafkaTransferMessage.getDestinationType());
        transferLog.setAmount(requestKafkaTransferMessage.getAmount());
        transferLog.setStatus(Status.STATUS_ACTIVE);
        transferLog.setCurrency(requestKafkaTransferMessage.getCurrencyName());
        transferLog.setUserId(requestKafkaTransferMessage.getUserId());

        if (!transferLogRepository.existsById(transferLog.getId())) {
            log.info("Creating transferLog: {}", transferLog);

            return transferLogRepository.save(transferLog);
        } else {

            throw new TransferAccountException(transferLog.getUserId());
        }
    }

    @Override
    public void changeTransferLogStatus(UUID id, int status) throws RuntimeException {
        log.info("Changing status for transferLog : {} to {}", id, status);

        TransferLog transferLog = findById(id);
        transferLog.setStatus(status);
        transferLogRepository.save(transferLog);

        log.info("Return result of status for transferLog : {} to {}", id, status);
    }
}
