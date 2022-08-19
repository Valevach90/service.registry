package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_impl.kafka.config.KafkaProperties;
import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferService;
import com.andersen.banking.meeting_impl.service.TransferMoneyTopicSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.transaction.Status;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyTopicSenderImpl implements TransferMoneyTopicSender {

    private final TransferService transferService;
    private final KafkaTemplate<String, RequestKafkaTransferMessage> kafkaTemplate;


    @Override
    @Transactional
    public void sendRequestMessage(String topicName, RequestKafkaTransferMessage requestKafkaTransferMessage) {

        final long transferId = requestKafkaTransferMessage.getTransferId();

        ListenableFuture<SendResult<String, RequestKafkaTransferMessage>> listenableFuture =
                kafkaTemplate.send(topicName, requestKafkaTransferMessage);

        transferService.changeTransferStatus(transferId, Status.STATUS_UNKNOWN);

        log.debug("Send transfer result message to topic: {}. Transfer id : {}", topicName, transferId);
        listenableFuture.addCallback(new ListenableFutureCallback() {

            @Override
            @Transactional
            public void onFailure(Throwable ex) {

                transferService.changeTransferStatus(transferId, Status.STATUS_MARKED_ROLLBACK);

                log.error("Sending operation failed. TopicName : {}. Transfer id : {}. Reason : {}. ", topicName, transferId, ex.getMessage());

            }

            @Override
            @Transactional
            public void onSuccess(Object result) {

                transferService.changeTransferStatus(transferId, Status.STATUS_PREPARING);

                log.info("Sent successfully. TopicName : {}. Transfer id : {}.", topicName, transferId);

            }
        });

    }
}
