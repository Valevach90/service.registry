package com.andersen.banking.gateway.meeting_impl.feign_client;

import com.andersen.banking.gateway.meeting_api.dto.RestResponsePage;
import com.andersen.banking.gateway.meeting_api.dto.deposit.Deposit;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "deposit-service")
public interface BalanceDepositClient {

    @GetMapping("/api/v1/deposits")
    Mono<RestResponsePage<Deposit>> findAll(Pageable pageable);
}