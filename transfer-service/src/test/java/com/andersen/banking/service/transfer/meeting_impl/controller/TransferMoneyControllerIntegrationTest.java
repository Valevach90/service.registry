package com.andersen.banking.service.transfer.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.TransferMoneyController;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_db.repository.TransferStatusRepository;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import com.andersen.banking.service.transfer.testcontainer.IntegrationTestWithPostgres;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@IntegrationTestWithPostgres
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TransferMoneyControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TransferMoneyController transferMoneyController;
    @Autowired
    private TransferMoneyService transferMoneyService;

    private final Random random = new Random();

    private static List<TransferStatus> transferStatusList;

    private static final String URL_TRANSFERS = "/api/v1/transfers/";

    @BeforeAll
    static void add (@Autowired TransferStatusRepository transferStatusRepository) {
        transferStatusRepository.deleteAll();
        addTransferStatusesToList();
        transferStatusList.forEach(transferStatusRepository::save);
    }

    private static void addTransferStatusesToList() {
        TransferStatus success = new TransferStatus();
        success.setName("SUCCESS");
        success.setDescription("Transaction completed successfully.");

        TransferStatus error = new TransferStatus();
        error.setName("ERROR");
        error.setDescription("The transaction failed.");

        transferStatusList.add(success);
        transferStatusList.add(error);
    }

    @AfterAll
    static void clear(@Autowired TransferStatusRepository transferStatusRepository) {
        transferStatusRepository.deleteAll();
    }

    @Test
    void contextLoads() {
        assertThat(transferMoneyController).isNotNull();
    }

    @Test
    void whenFindTransferStatusByIdandOk()  throws Exception {

        TransferStatusResponseDto transferStatusResponseDto = transferMoneyService
                .getTransferStatus(random.nextLong(0, 2L));

        mockMvc.perform(get(URL_TRANSFERS + (random.nextLong(0, 2L)) + "/status").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(transferStatusResponseDto.getName()))
                .andExpect(jsonPath("$.description").value(transferStatusResponseDto.getDescription()));
    }

}
