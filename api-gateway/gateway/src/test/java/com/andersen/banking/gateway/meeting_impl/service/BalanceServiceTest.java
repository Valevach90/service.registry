package com.andersen.banking.gateway.meeting_impl.service;

import com.andersen.banking.gateway.meeting_api.controller.feign.BalanceDepositService;
import com.andersen.banking.gateway.meeting_api.controller.feign.BalancePaymentService;
import com.andersen.banking.gateway.meeting_api.dto.RestResponsePage;
import com.andersen.banking.gateway.meeting_api.dto.deposit.Deposit;
import com.andersen.banking.gateway.meeting_api.dto.gateway.User;
import com.andersen.banking.gateway.meeting_api.dto.payment.Account;
import com.andersen.banking.gateway.meeting_impl.service.impl.BalanceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.andersen.banking.gateway.meeting_impl.util.PopulateEntity.*;

@SpringBootTest(classes = BalanceServiceImpl.class)
class BalanceServiceTest {

    private List<Account> accounts;
    private List<Deposit> deposits;
    private User expectedUser;

    @MockBean
    BalanceDepositService depositService;

    @MockBean
    BalancePaymentService paymentService;

    @Autowired
    BalanceService balanceService;

    @BeforeEach
    void init() {
        accounts = createListAccount();
        deposits = createListDeposit();
        expectedUser = populateUser();
    }

    @Test
    void getAllMoneyOwner_shouldReturnAllMoneyForUserId() {
        RestResponsePage<Account> pageAccount = new RestResponsePage<>(accounts, Pageable.unpaged(), Long.MAX_VALUE);
        RestResponsePage<Deposit> pageDeposit = new RestResponsePage<>(deposits, Pageable.unpaged(), Long.MAX_VALUE);

        Mockito.when(depositService.findAll(Pageable.unpaged())).thenReturn(Mono.just(pageDeposit));
        Mockito.when(paymentService.findByOwnerId(1L, Pageable.unpaged())).thenReturn(Mono.just(pageAccount));

        User actual = balanceService.getTotalBalance(1L);

        Assertions.assertEquals(expectedUser, actual);
    }

}