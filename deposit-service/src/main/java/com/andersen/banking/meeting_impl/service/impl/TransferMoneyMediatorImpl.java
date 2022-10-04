package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import com.andersen.banking.meeting_impl.service.DepositService;
import com.andersen.banking.meeting_impl.service.TransferMoneyMediator;
import java.util.UUID;
import javax.transaction.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransferMoneyMediatorImpl implements TransferMoneyMediator {

    private static final String DEPOSIT = "deposit";
    private final DepositService depositService;
    private final TransferServiceImpl transferService;

    public ResponseTransferMessage execute(RequestTransferMessage message) {
        UUID transferId = message.getTransferId();

        if (transferService.isExistStatus(transferId, message.getStatus())
                || !message.getSourceType().equalsIgnoreCase(DEPOSIT)
                && message.getSourceType().equalsIgnoreCase(message.getDestinationType())) {
            return ResponseTransferMessage.builder()
                    .transferId(transferId)
                    .status(Status.STATUS_UNKNOWN)
                    .statusDescription("").build();
        } else {
            return depositService.makeTransfer(message);
        }
    }
}
