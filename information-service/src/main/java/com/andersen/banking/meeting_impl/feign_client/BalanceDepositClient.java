package com.andersen.banking.meeting_impl.feign_client;

import com.andersen.banking.meeting_api.dto.RestResponsePage;
import com.andersen.banking.meeting_api.dto.deposit.Deposit;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "deposit-service")
public interface BalanceDepositClient {

    @GetMapping("/api/v1/deposits/user")
    Mono<RestResponsePage<Deposit>> findDepositsByUserId(
            @RequestHeader("Authorization") String token,
            Pageable pageable);
}
