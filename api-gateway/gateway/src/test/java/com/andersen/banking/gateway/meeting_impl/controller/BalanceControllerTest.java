package com.andersen.banking.gateway.meeting_impl.controller;

import com.andersen.banking.gateway.meeting_api.controller.BalanceController;
import com.andersen.banking.gateway.meeting_api.dto.RestResponsePage;
import com.andersen.banking.gateway.meeting_api.dto.deposit.Deposit;
import com.andersen.banking.gateway.meeting_api.dto.gateway.User;
import com.andersen.banking.gateway.meeting_api.dto.payment.Account;
import com.andersen.banking.gateway.meeting_impl.feign_client.BalanceDepositClient;
import com.andersen.banking.gateway.meeting_impl.feign_client.BalancePaymentClient;
import com.andersen.banking.gateway.meeting_impl.service.BalanceService;
import com.andersen.banking.gateway.meeting_impl.service.impl.BalanceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.andersen.banking.gateway.meeting_impl.util.PopulateEntity.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {BalanceController.class, BalanceServiceImpl.class})
@AutoConfigureMockMvc(addFilters = false)
class BalanceControllerTest {

    private List<Account> accounts;
    private List<Deposit> deposits;
    private User expectedUser;

    @MockBean
    BalanceDepositClient depositService;

    @MockBean
    BalancePaymentClient paymentService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BalanceService balanceService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        accounts = createListAccount();
        deposits = createListDeposit();
        expectedUser = populateUser();
    }

    @Test
    void getAllMoneyOwner_shouldReturnAllMoneyFromUserId() throws Exception {
        long id = 1L;
        RestResponsePage<Account> pageAccount = new RestResponsePage<>(accounts, Pageable.unpaged(), Long.MAX_VALUE);
        RestResponsePage<Deposit> pageDeposit = new RestResponsePage<>(deposits, Pageable.unpaged(), Long.MAX_VALUE);

        Mockito.when(depositService.findDepositsByUserId(1L, Pageable.unpaged())).thenReturn(Mono.just(pageDeposit));
        Mockito.when(paymentService.findByOwnerId(1L, Pageable.unpaged())).thenReturn(Mono.just(pageAccount));

        MvcResult result = mockMvc.perform(get("/api/v1/balance/{id}", id))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        String contentAsString = result.getResponse().getContentAsString();
        User actual = mapper.readValue(contentAsString, User.class);

        assertEquals(expectedUser, actual);
    }
}