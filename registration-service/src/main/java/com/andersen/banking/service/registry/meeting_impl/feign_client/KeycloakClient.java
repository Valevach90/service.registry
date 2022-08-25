package com.andersen.banking.service.registry.meeting_impl.feign_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@FeignClient(name = "deposit-service")
public interface KeycloakClient {

}
