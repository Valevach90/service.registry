package com.andersen.banking.meeting_impl.controller;

import static com.andersen.banking.meeting_impl.util.PopulateEntity.FIRST_ID;
import static com.andersen.banking.meeting_impl.util.PopulateEntity.createListAccount;
import static com.andersen.banking.meeting_impl.util.PopulateEntity.createListDeposit;
import static com.andersen.banking.meeting_impl.util.PopulateEntity.populateJwt;
import static com.andersen.banking.meeting_impl.util.PopulateEntity.populateUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.andersen.banking.meeting_api.controller.BalanceController;
import com.andersen.banking.meeting_api.dto.RestResponsePage;
import com.andersen.banking.meeting_api.dto.deposit.Deposit;
import com.andersen.banking.meeting_api.dto.gateway.UserBalance;
import com.andersen.banking.meeting_api.dto.payment.Account;
import com.andersen.banking.meeting_impl.feign_client.BalanceDepositClient;
import com.andersen.banking.meeting_impl.feign_client.BalancePaymentClient;
import com.andersen.banking.meeting_impl.service.BalanceService;
import com.andersen.banking.meeting_impl.service.impl.BalanceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import reactor.core.publisher.Mono;

@WebMvcTest(value = {BalanceController.class, BalanceServiceImpl.class})
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
class BalanceControllerTest {

    @MockBean
    BalanceDepositClient depositService;
    @MockBean
    BalancePaymentClient paymentService;
    @Autowired
    BalanceService balanceService;
    private List<Account> accounts;
    private List<Deposit> deposits;
    private UserBalance expectedUser;
    private String token;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        accounts = createListAccount();
        deposits = createListDeposit();
        expectedUser = populateUser();
        token = populateJwt();
    }

    @Test
    void getAllMoneyOwner_shouldReturnAllMoneyFromUserId() throws Exception {
        RestResponsePage<Account> pageAccount = new RestResponsePage<>(accounts, Pageable.unpaged(),
                Long.MAX_VALUE);
        RestResponsePage<Deposit> pageDeposit = new RestResponsePage<>(deposits, Pageable.unpaged(),
                Long.MAX_VALUE);

        Mockito.when(depositService.findDepositsByUserId(token, Pageable.unpaged()))
                .thenReturn(Mono.just(pageDeposit));
        Mockito.when(paymentService.findByOwnerId(FIRST_ID, Pageable.unpaged()))
                .thenReturn(Mono.just(pageAccount));

        ResultActions bearer_token = mockMvc.perform(get("/api/v1/balance")
                .accept(MediaType.ALL)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON));
        MvcResult result = bearer_token
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        String contentAsString = result.getResponse().getContentAsString();
        UserBalance actual = mapper.readValue(contentAsString, UserBalance.class);

        assertEquals(expectedUser, actual);
    }
}