package com.andersen.banking.gateway.meeting_impl.service.impl;

import com.andersen.banking.gateway.meeting_api.controller.feign.BalancePaymentService;
import com.andersen.banking.gateway.meeting_api.dto.Account;
import com.andersen.banking.gateway.meeting_api.dto.RestResponsePage;
import com.andersen.banking.gateway.meeting_impl.service.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalancePaymentService paymentService;

    public Double getTotalBalance(Long id) {
        Mono<RestResponsePage<Account>> byOwnerId = paymentService.findByOwnerId(id, Pageable.unpaged());
        try {
            return byOwnerId
                    .toFuture()
                    .get()
                    .getContent()
                    .stream()
                    .mapToDouble(Account::getBalance).sum();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
