package com.andersen.banking.meeting_api.controller.feign;

import com.andersen.banking.meeting_api.dto.AccountDto;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="payment-service")
public interface BalancePaymentServiceProxy {
    @GetMapping("/owners/{id}")
    Page<AccountDto> findByOwnerId(@Parameter(description = "ownerId", required = true)
                                   @PathVariable("id") Long id,
                                   Pageable pageable);
}
