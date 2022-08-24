package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_impl.kafka.message.ResponseKafkaTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferService;
import com.andersen.banking.meeting_impl.service.TransferMoneyTopicListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Status;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyTopicListenerImpl implements TransferMoneyTopicListener {

    private final TransferService transferService;

    @Override
    @Transactional
    @KafkaListener(topics = "${spring.kafka.transfer_response_topic_name}")
    public void listenResponseMessage(@Payload List<ResponseKafkaTransferMessage> messages) {
        messages.forEach(response -> {

            transferService.changeTransferStatus(
                    response.getTransferId(),
                    response.isResult() ? Status.STATUS_COMMITTING : Status.STATUS_ROLLEDBACK
            );

            log.info("Changed transfer status for response : {}", response);
        });
    }

}
