package com.andersen.banking.meeting_impl.kafka.config;

import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
@AllArgsConstructor
@Slf4j
public class KafkaPublisherConfig {

    @Bean
    public Sinks.Many<RequestTransferMessage> transferSink() {
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<RequestTransferMessage>> transferSupplier(
            Sinks.Many<RequestTransferMessage> sink) {
        return sink::asFlux;
    }
}

