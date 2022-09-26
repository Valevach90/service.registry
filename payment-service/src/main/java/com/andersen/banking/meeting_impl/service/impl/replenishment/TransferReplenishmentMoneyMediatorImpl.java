package com.andersen.banking.meeting_impl.service.impl.replenishment;

import com.andersen.banking.meeting_db.entities.StatusDescription;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferLogService;
import com.andersen.banking.meeting_impl.service.TransferMoneyMediator;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import javax.transaction.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferReplenishmentMoneyMediatorImpl implements TransferMoneyMediator {


    private final TransferMoneyService transferReplenishmentMoneyServiceImpl;

    private final TransferLogService transferLogService;

    @Override
    public ResponseTransferMessage run(RequestTransferMessage message) {
        log.info("Executing internal run with : {}", message);

        if (message.getStatus() == Status.STATUS_ROLLING_BACK) {
            return ResponseTransferMessage.builder()
                    .transferId(message.getTransferId())
                    .statusDescription(StatusDescription.FAILED.getDescription())
                    .status(Status.STATUS_ROLLEDBACK)
                    .build();
        }

        transferLogService.createTransferLogAccordingToRequestKafkaMessage(
                message);

        ResponseTransferMessage response =
                transferReplenishmentMoneyServiceImpl.executeTransfer(message);

        boolean isOk = response.getStatus() == Status.STATUS_COMMITTING;
        transferLogService.changeTransferLogStatus(
                message.getTransferId(),
                isOk ? Status.STATUS_COMMITTED
                        : Status.STATUS_ROLLEDBACK
        );

        log.info("Executed run with : {}", message);
        return response;
    }
}
