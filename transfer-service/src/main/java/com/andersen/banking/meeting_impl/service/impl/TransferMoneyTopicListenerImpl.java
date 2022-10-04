package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferExecutor;
import com.andersen.banking.meeting_impl.service.TransferMoneyTopicListener;
import com.andersen.banking.meeting_impl.service.TransferService;
import javax.transaction.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyTopicListenerImpl implements TransferMoneyTopicListener {

    private final TransferService transferService;
    private final TransferExecutor transferExecutor;

    @Override
    @Transactional
    public void listenMessage(ResponseTransferMessage message) {
        Integer status = message.getStatus();
        if (status == Status.STATUS_UNKNOWN) {
            return;
        }

        log.info("Changed transfer status for response : {}", message);
        if (status == Status.STATUS_ROLLING_BACK) {
            Transfer transfer = transferService.findById(message.getTransferId());
            transfer.setStatus(Status.STATUS_ROLLING_BACK);
            transferExecutor.execute(transfer);
            return;
        }
        if (!transferService.isEqualStatus(message.getTransferId(), status)) {
            transferService.changeTransferStatus(
                    message.getTransferId(),
                    status,
                    message.getService()
            );
            return;
        }
        if (status == Status.STATUS_COMMITTING) {
            saveTransferService(message);
        }
    }

    private void saveTransferService(ResponseTransferMessage message) {
        String service = transferService.getService(message.getTransferId());
        if (service == null) {
            return;
        }
        String msgService = message.getService();
        if (!service.contains(msgService)) {
            transferService.changeTransferStatus(
                    message.getTransferId(),
                    Status.STATUS_COMMITTED,
                    service + msgService
            );
        }
    }
}
