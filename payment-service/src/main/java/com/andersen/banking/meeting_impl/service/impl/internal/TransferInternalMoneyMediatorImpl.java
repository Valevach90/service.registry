package com.andersen.banking.meeting_impl.service.impl.internal;

import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import com.andersen.banking.meeting_impl.mapper.TransferMessageMapper;
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
public class TransferInternalMoneyMediatorImpl implements TransferMoneyMediator {


    private final TransferMoneyService transferInternalMoneyServiceImpl;

    private final TransferLogService transferLogService;


    @Override
    public ResponseTransferMessage run(RequestTransferMessage requestTransferMessage) {
        log.info("Executing internal run with : {}", requestTransferMessage);

        transferLogService.createTransferLogAccordingToRequestKafkaMessage(
                requestTransferMessage);

        ResponseTransferMessage response = transferInternalMoneyServiceImpl.executeTransfer(
                requestTransferMessage);

        transferLogService.changeTransferLogStatus(
                requestTransferMessage.getTransferId(),
                response.getStatus() == Status.STATUS_COMMITTING ? Status.STATUS_COMMITTED
                        : Status.STATUS_ROLLEDBACK
        );

        log.info("Executed run with : {}", requestTransferMessage);
        return response;
    }
}
