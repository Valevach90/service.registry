package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.StatusDescription;
import com.andersen.banking.meeting_impl.exception.DifferentTransferTypeException;
import com.andersen.banking.meeting_impl.exception.TransferLogAlreadyExistsException;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferLogService;
import com.andersen.banking.meeting_impl.service.TransferMoneyMediator;
import com.andersen.banking.meeting_impl.service.TransferMoneyTopicListener;
import java.util.Map;
import java.util.UUID;
import javax.transaction.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyTopicListenerImpl implements TransferMoneyTopicListener {

    private static final String CARD = "card";
    private static final String DEPOSIT = "deposit";

    private final Map<String, TransferMoneyMediator> transferMoneyMediatorMap;
    private final TransferLogService transferLogService;

    @Override
    public ResponseTransferMessage execute(RequestTransferMessage message) {
        ResponseTransferMessage response;
        try {
            response = getMoneyMediator(message).run(message);
        } catch (TransferLogAlreadyExistsException | DifferentTransferTypeException e) {
            response = ResponseTransferMessage.builder()
                    .transferId(message.getTransferId())
                    .status(Status.STATUS_UNKNOWN)
                    .statusDescription(StatusDescription.EXIST.getDescription())
                    .build();
        }
        return response;
    }

    private TransferMoneyMediator getMoneyMediator(RequestTransferMessage message)
            throws TransferLogAlreadyExistsException, DifferentTransferTypeException {
        UUID transferId = message.getTransferId();
        if (transferLogService.isExistStatus(transferId, message.getStatus())) {
            throw new TransferLogAlreadyExistsException(transferId);
        }

        if (transferLogService.isExist(transferId)) {
            if (message.getStatus() == Status.STATUS_ROLLING_BACK) {
                return transferMoneyMediatorMap.get("transferReplenishmentMoneyMediatorImpl");
            } else {
                throw new DifferentTransferTypeException(transferId);
            }
        }

        if (message.getSourceType().equalsIgnoreCase(CARD)) {
            if (message.getSourceType().equalsIgnoreCase(message.getDestinationType())) {
                return transferMoneyMediatorMap.get("transferInternalMoneyMediatorImpl");
            }
            if (message.getDestinationType().equalsIgnoreCase(DEPOSIT)) {
                return transferMoneyMediatorMap.get(
                        "transferReplenishmentMoneyMediatorImpl");
            }
        }

        if(message.getSourceType().equalsIgnoreCase(DEPOSIT)) {
            if(message.getDestinationType().equalsIgnoreCase(CARD)) {
                return transferMoneyMediatorMap.get("transferReplenishmentMoneyMediatorImpl");
            }
        }

        throw new DifferentTransferTypeException(transferId);
    }
}
