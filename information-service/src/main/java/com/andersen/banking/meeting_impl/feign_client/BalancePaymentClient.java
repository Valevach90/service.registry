package com.andersen.banking.meeting_impl.feign_client;

import com.andersen.banking.meeting_api.dto.RestResponsePage;
import com.andersen.banking.meeting_api.dto.payment.Account;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "payment-service")
public interface BalancePaymentClient {

    @GetMapping("/api/v1/accounts/owners/{id}")
    Mono<RestResponsePage<Account>> findByOwnerId(@PathVariable("id") UUID id, Pageable pageable);
}
