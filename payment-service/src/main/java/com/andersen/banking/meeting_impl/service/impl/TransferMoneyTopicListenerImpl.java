package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferMoneyMediator;
import com.andersen.banking.meeting_impl.service.TransferMoneyTopicListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyTopicListenerImpl implements TransferMoneyTopicListener {

    private final TransferMoneyMediator transferMoneyMediator;

    @Override
    @KafkaListener(topics = "$spring.kafka.transfer_request_topic_name")
    public void listenKafkaTopicOnInternalTransfer(List<RequestKafkaTransferMessage> messages) {
        messages.forEach(
                m -> {
                    log.info("Receive message from topic with transfer id : {}", m.getTransferId());

                    transferMoneyMediator.run(m);
                });
    }
}
