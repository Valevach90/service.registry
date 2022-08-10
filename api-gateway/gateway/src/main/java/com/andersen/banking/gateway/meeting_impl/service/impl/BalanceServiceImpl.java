package com.andersen.banking.gateway.meeting_impl.service.impl;

import com.andersen.banking.gateway.meeting_impl.feign_client.BalanceDepositClient;
import com.andersen.banking.gateway.meeting_impl.feign_client.BalancePaymentClient;
import com.andersen.banking.gateway.meeting_api.dto.deposit.Deposit;
import com.andersen.banking.gateway.meeting_api.dto.gateway.Currency;
import com.andersen.banking.gateway.meeting_api.dto.RestResponsePage;
import com.andersen.banking.gateway.meeting_api.dto.gateway.EntityAggregate;
import com.andersen.banking.gateway.meeting_api.dto.gateway.User;
import com.andersen.banking.gateway.meeting_api.dto.payment.Account;
import com.andersen.banking.gateway.meeting_impl.service.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@Slf4j
@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceDepositClient depositService;

    @Autowired
    private BalancePaymentClient paymentService;

    public User getTotalBalance(Long id) {
        log.info("create request in services for id {}", id);
        Mono<EntityAggregate> map = Mono.zip(
                depositService.findAll(Pageable.unpaged()),
                paymentService.findByOwnerId(id, Pageable.unpaged())
        ).map(this::combine);
        try {
            EntityAggregate entityAggregate = map.toFuture().get();

            Map<String, Double> mapDeposit = entityAggregate.getDeposits()
                    .stream()
                    .filter(d -> d.getUserId().equals(id))
                    .collect(
                            groupingBy((d -> d.getCurrency().getName()),
                                    summingDouble(Deposit::getAmount)));
            log.info("Deposit for {} in quantity {}", id, mapDeposit.size());

            Map<String, Double> mapAccount = entityAggregate.getAccounts()
                    .stream()
                    .collect(groupingBy(Account::getCurrency, summingDouble(Account::getBalance)));
            log.info("Deposit for {} in quantity {}", id, mapAccount.size());

            List<Currency> currencies = Stream.concat(mapDeposit.entrySet().stream(), mapAccount.entrySet().stream())
                    .collect(groupingBy(Map.Entry::getKey,
                            summingDouble(Map.Entry::getValue)))
                    .entrySet().stream()
                    .map(e -> new Currency(e.getKey(), e.getValue())).toList();

            return new User(id, currencies);
        } catch (InterruptedException | ExecutionException e) {
            log.error("No access to services");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private EntityAggregate combine(Tuple2<RestResponsePage<Deposit>, RestResponsePage<Account>> tuple) {
        return EntityAggregate.create(
                tuple.getT1().getContent(),
                tuple.getT2().getContent()
        );
    }
}
