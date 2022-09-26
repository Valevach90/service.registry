package com.andersen.banking.meeting_impl.kafka.config;

import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferMoneyMediator;
import java.util.function.Function;
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

    private final TransferMoneyMediator transferMoneyMediator;

    @Bean
    public Function<Flux<RequestTransferMessage>, Flux<ResponseTransferMessage>> depositProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::depositProcessor);
    }

    private Mono<ResponseTransferMessage> depositProcessor(RequestTransferMessage req) {
        return Mono.fromSupplier(() -> transferMoneyMediator.execute(req));
    }
}
