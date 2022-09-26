package com.andersen.banking.meeting_impl.kafka.config;

import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferMoneyTopicListener;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final TransferMoneyTopicListener transferMoneyTopicListener;

    @Bean
    public Function<Flux<RequestTransferMessage>,
            Flux<ResponseTransferMessage>> paymentProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::paymentProcessor);
    }


    private Mono<ResponseTransferMessage> paymentProcessor(RequestTransferMessage req) {
        return Mono.fromSupplier(() ->
                transferMoneyTopicListener.execute(req)
        );
    }
}
