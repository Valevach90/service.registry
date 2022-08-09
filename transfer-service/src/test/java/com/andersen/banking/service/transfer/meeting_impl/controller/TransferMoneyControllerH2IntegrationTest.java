package com.andersen.banking.service.transfer.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.TransferMoneyController;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_db.repository.TransferStatusRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

import static com.andersen.banking.service.transfer.meeting_impl.generators.TransferServiceTestEntitiesGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TransferMoneyController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransferMoneyControllerH2IntegrationTest {

    private TransferStatus transferStatus;
    private TransferStatusResponseDto transferStatusResponseDto;
    private Long id;

    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";

    @Autowired
    private TransferStatusRepository transferStatusRepository;

    private static RestTemplate restTemplate = null;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/v1/transfers/");
    }

    @Test
    void whenFindTransferStatusByIdandOk() {
        transferStatus = generateTransferStatus();
        transferStatusResponseDto = generateTransferStatusResponseDto(transferStatus);
        id = setId();

        TransferStatusResponseDto response = restTemplate.getForObject(baseUrl + "/{transfer_id}/status", TransferStatusResponseDto.class, id);

        assertEquals(transferStatusResponseDto, response);
        assertEquals(1, transferStatusRepository.findAll().size());
    }

}
