package com.andersen.banking.service.transfer.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.TransferMoneyController;
import com.andersen.banking.service.transfer.testcontainer.IntegrationTestWithPostgres;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@IntegrationTestWithPostgres
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TransferMoneyControllerIntegrationTest {
    @Autowired
    private TransferMoneyController transferMoneyController;

    @Test
    void contextLoads() {
        assertThat(transferMoneyController).isNotNull();
    }

}
