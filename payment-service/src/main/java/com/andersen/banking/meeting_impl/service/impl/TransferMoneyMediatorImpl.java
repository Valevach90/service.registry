package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseKafkaTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferLogService;
import com.andersen.banking.meeting_impl.service.TransferMoneyMediator;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import com.andersen.banking.meeting_impl.service.TransferMoneyTopicSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Status;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyMediatorImpl implements TransferMoneyMediator {

    private final TransferMoneyService transferMoneyService;
    private final TransferMoneyTopicSender transferMoneyTopicSender;

    private final TransferLogService transferLogService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void run(RequestKafkaTransferMessage requestKafkaTransferMessage) {

        transferLogService.createTransferLogAccordingToRequestKafkaMessage(
                requestKafkaTransferMessage);

        ImmutablePair<Boolean, StringBuffer> transferResults =
                transferMoneyService.executeTransfer(requestKafkaTransferMessage);

        transferLogService.changeTransferLogStatus(
                requestKafkaTransferMessage.getTransferId(), Status.STATUS_COMMITTING);

        transferMoneyTopicSender.sendResponseMessage(
                ResponseKafkaTransferMessage.builder()
                        .transferId(requestKafkaTransferMessage.getTransferId())
                        .result(transferResults.left)
                        .statusDescription(transferResults.right.toString())
                        .build());
    }
}
