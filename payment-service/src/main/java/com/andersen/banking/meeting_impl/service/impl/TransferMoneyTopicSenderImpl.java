package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_impl.kafka.config.KafkaProperties;
import com.andersen.banking.meeting_impl.kafka.message.ResponseKafkaTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferLogService;
import com.andersen.banking.meeting_impl.service.TransferMoneyTopicSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.transaction.Status;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyTopicSenderImpl implements TransferMoneyTopicSender {

    private final TransferLogService transferLogService;

    private final KafkaTemplate<String, ResponseKafkaTransferMessage> kafkaTemplate;

    private final KafkaProperties kafkaProperties;

    @Override
    public void sendResponseMessage(ResponseKafkaTransferMessage responseKafkaTransferMessage) {

        final UUID transferId = responseKafkaTransferMessage.getTransferId();
        final String topicName = kafkaProperties.getTransfer_response_topic_name();

        ListenableFuture<SendResult<String, ResponseKafkaTransferMessage>> listenableFuture =
                kafkaTemplate.send(
                        topicName,
                        ResponseKafkaTransferMessage.builder()
                                .transferId(transferId)
                                .result(responseKafkaTransferMessage.isResult())
                                .statusDescription(
                                        responseKafkaTransferMessage.getStatusDescription())
                                .build());

        log.debug(
                "Send transfer result message to topic: {}. Transfer id : {}",
                topicName,
                transferId);

        listenableFuture.addCallback(
                new ListenableFutureCallback() {
                    @Override
                    public void onFailure(Throwable ex) {
                        transferLogService.changeTransferLogStatus(
                                transferId, Status.STATUS_ROLLING_BACK);

                        log.error(
                                "Sending operation failed. TopicName : {}. Transfer id : {}. Reason : {}. ",
                                topicName,
                                transferId,
                                ex.getMessage());
                    }

                    @Override
                    public void onSuccess(Object result) {
                        transferLogService.changeTransferLogStatus(
                                transferId, Status.STATUS_COMMITTED);

                        log.info(
                                "Sent successfully. TopicName : {}. Transfer id : {}.",
                                topicName,
                                transferId);
                    }
                });
    }
}
