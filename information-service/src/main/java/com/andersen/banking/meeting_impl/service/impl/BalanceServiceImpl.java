package com.andersen.banking.meeting_impl.service.impl;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

import com.andersen.banking.meeting_api.dto.RestResponsePage;
import com.andersen.banking.meeting_api.dto.deposit.Deposit;
import com.andersen.banking.meeting_api.dto.gateway.Currency;
import com.andersen.banking.meeting_api.dto.gateway.EntityAggregate;
import com.andersen.banking.meeting_api.dto.gateway.UserBalance;
import com.andersen.banking.meeting_api.dto.payment.Account;
import com.andersen.banking.meeting_impl.feign_client.BalanceDepositClient;
import com.andersen.banking.meeting_impl.feign_client.BalancePaymentClient;
import com.andersen.banking.meeting_impl.service.BalanceService;
import com.andersen.banking.meeting_impl.util.JwtUtil;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Slf4j
@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceDepositClient balanceDepositClient;

    @Autowired
    private BalancePaymentClient balancePaymentClient;

    public UserBalance getTotalBalance(Authentication authentication) {
        Jwt token = (Jwt) authentication.getPrincipal();
        UUID id = UUID.fromString(JwtUtil.extractIdFromToken(token));
        log.info("create request in services with user id: {}", id);
        Mono<EntityAggregate> map = Mono.zip(
                balanceDepositClient.findDepositsByUserId(token.getTokenValue(), Pageable.unpaged()),
                balancePaymentClient.findByOwnerId(id, Pageable.unpaged())
        ).map(this::combine);
        try {
            EntityAggregate entityAggregate = map.toFuture().get();

            Map<String, Double> mapDeposit = entityAggregate.getDeposits()
                    .stream()
                    .collect(
                            groupingBy((d -> d.getCurrency().getName()),
                                    summingDouble(Deposit::getAmount)));
            log.info("Deposit for {} in quantity {}", id, mapDeposit.size());

            Map<String, Double> mapAccount = entityAggregate.getAccounts()
                    .stream()
                    .collect(groupingBy(Account::getCurrency, summingDouble(Account::getBalance)));
            log.info("Deposit for {} in quantity {}", id, mapAccount.size());

            List<Currency> currencies = Stream.concat(
                            mapDeposit.entrySet().stream(), mapAccount.entrySet().stream()
                    )
                    .collect(groupingBy(Map.Entry::getKey,
                            summingDouble(Map.Entry::getValue)))
                    .entrySet().stream()
                    .map(e -> new Currency(e.getKey(), e.getValue())).toList();

            return new UserBalance(id, currencies);
        } catch (ExecutionException | InterruptedException e) {
            log.error("No access to services with exception:", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private EntityAggregate combine(
            Tuple2<RestResponsePage<Deposit>, RestResponsePage<Account>> tuple) {
        return EntityAggregate.create(
                tuple.getT1().getContent(),
                tuple.getT2().getContent()
        );
    }
}
