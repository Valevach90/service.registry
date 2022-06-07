package com.andersen.banking.service.payment.impl.controller;


import com.andersen.banking.service.payment.container.IntegrationTestWithPostgres;
import com.andersen.banking.service.payment.meeting_api.controller.AccountController;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.repository.AccountRepository;
import com.andersen.banking.service.payment.impl.generator.AccountGenerator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@IntegrationTestWithPostgres
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class AccountControllerIntegrationTest {

    private static List<Account> accountList;

    private static final String URL = "/api/v1/accounts/";
    private static final String PARAM_ID = "$.id";
    private static final String PARAM_USER_ID = "$.user_id";
    private static final String PARAM_ISSUE_DATE = "$.issue_date";
    private static final String PARAM_TERMINATION_DATE = "$.termination_date";
    private static final String PARAM_BANK_NAME = "$.bank_name";
    private static final String PARAM_ACCOUNT_NUMBER = "$.account_number";
    private static final String PARAM_CURRENCY = "$.currency";
    private static final String PARAM_NUMBER = "number";
    private static final String PARAM_SIZE = "size";
    private static final String PARAM_PAGE = "page";
    private static final String PARAM_TOTAL_ELEMENTS = "totalElements";
    private static final String PARAM_TOTAL_PAGES = "totalPages";
    private static final String PARAM_FIRST = "first";
    private static final String PARAM_LAST = "last";
    private static final String PARAM_CONTENT = "$.content";

    private final String CONTENT_TYPE = "application/json";

    private final Random random = new Random();

    @Autowired
    private AccountController accountController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(accountController).isNotNull();
    }


    @BeforeAll
    static void add(
            @Autowired AccountRepository accountRepository,
            @Autowired AccountGenerator accountGenerator) {

        accountList = Stream.generate(accountGenerator::generate)
                .limit(100)
                .toList();
        accountRepository.saveAll(accountList);
    }

    @AfterAll
    static void clear(
            @Autowired AccountRepository accountRepository) {
        accountRepository.deleteAll();
    }


    @Test
    void findById_ShouldWorkProperly() throws Exception {
        Account account = accountList.get(random.nextInt(0, accountList.size()));

        mockMvc.perform(
                        get(URL + account.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath(PARAM_ID).value(account.getId()))
                .andExpect(jsonPath(PARAM_USER_ID).value(account.getUserId()))
                .andExpect(jsonPath(PARAM_ACCOUNT_NUMBER).value(account.getAccountNumber()))
                .andExpect(jsonPath(PARAM_CURRENCY).value(account.getCurrency()))
                .andExpect(jsonPath(PARAM_BANK_NAME).value(account.getBankName()))
        ;
    }

    @Test
    void findAll_ShouldWorkProperly() throws Exception {
        Integer pageNumber = 0;
        Integer pageSize = 10;

        mockMvc.perform(
                        get(URL)
                                .param(PARAM_PAGE, String.valueOf(pageNumber))
                                .param(PARAM_SIZE, String.valueOf(pageSize))
                ).andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath(PARAM_NUMBER).value(pageNumber))
                .andExpect(jsonPath(PARAM_SIZE).value(pageSize))
                .andExpect(jsonPath(PARAM_TOTAL_ELEMENTS).value(accountList.size()))
                .andExpect(jsonPath(PARAM_TOTAL_PAGES).value(
                        accountList.size() % pageSize == 0 ? accountList.size() / pageSize : accountList.size() / pageSize + 1))
                .andExpect(jsonPath(PARAM_FIRST).value(pageNumber == 0))
                .andExpect(jsonPath(PARAM_LAST).value(accountList.size() / pageSize == pageNumber))
                .andExpect(jsonPath(PARAM_CONTENT).isArray())
                .andExpect(jsonPath(PARAM_CONTENT, hasSize(pageSize)));
    }


}
