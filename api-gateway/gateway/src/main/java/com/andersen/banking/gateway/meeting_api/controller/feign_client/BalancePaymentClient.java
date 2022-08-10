package com.andersen.banking.gateway.meeting_api.controller.feign_client;

import com.andersen.banking.gateway.meeting_api.dto.payment.Account;
import com.andersen.banking.gateway.meeting_api.dto.RestResponsePage;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "payment-service")
public interface BalancePaymentClient {
    @GetMapping("/api/v1/accounts/owners/{id}")
    Mono<RestResponsePage<Account>> findByOwnerId(@Parameter(description = "ownerId", required = true)
                                                  @PathVariable("id") Long id,
                                                  Pageable pageable);
}
