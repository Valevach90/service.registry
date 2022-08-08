package com.andersen.banking.gateway.meeting_impl.service.impl;

import com.andersen.banking.gateway.meeting_api.controller.feign.BalanceDepositService;
import com.andersen.banking.gateway.meeting_api.controller.feign.BalancePaymentService;
import com.andersen.banking.gateway.meeting_api.dto.*;
import com.andersen.banking.gateway.meeting_impl.service.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Slf4j
@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceDepositService depositService;

    @Autowired
    private BalancePaymentService paymentService;

    public User getTotalBalance(Long id) {
        Mono<RestResponsePage<Deposit>> deposit = depositService.findAll(Pageable.unpaged());
        Mono<RestResponsePage<Account>> payment = paymentService.findByOwnerId(id, Pageable.unpaged());
        Mono<Tuple2<RestResponsePage<Deposit>, RestResponsePage<Account>>> zip = Mono.zip(deposit, payment);
        try {
            Map<String, Double> mapDeposit = zip.toFuture()
                    .get()
                    .getT1()
                    .getContent()
                    .stream()
                    .filter(d -> d.getUserId().equals(id))
                    .collect(
                            groupingBy((d -> d.getCurrency().getName()),
                                    summingDouble(Deposit::getAmount)));

            Map<String, Double> mapAccount = zip.toFuture()
                    .get()
                    .getT2()
                    .stream()
                    .collect(groupingBy(Account::getCurrency, summingDouble(Account::getBalance)));

            Map<String, Double> combinedMap = Stream.concat(mapDeposit.entrySet().stream(), mapAccount.entrySet().stream())
                    .collect(groupingBy(Map.Entry::getKey,
                            summingDouble(Map.Entry::getValue)));

            List<Currency> collect = combinedMap.entrySet().stream()
                    .map(e -> new Currency(e.getKey(), e.getValue())).collect(toList());
            return new User(id, collect);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
