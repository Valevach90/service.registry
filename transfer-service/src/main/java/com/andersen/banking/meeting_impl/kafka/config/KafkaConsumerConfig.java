package com.andersen.banking.meeting_impl.kafka.config;

import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferMoneyTopicListener;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final TransferMoneyTopicListener transferMoneyTopicListener;

    @Bean
    public Consumer<ResponseTransferMessage> eventConsumer() {
        return transferMoneyTopicListener::listenMessage;
    }
}


