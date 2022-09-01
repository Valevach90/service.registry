package com.andersen.banking.service.transfer.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.TransferMoneyController;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_db.repository.TransferStatusRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TransferMoneyController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransferMoneyControllerH2IntegrationTest {

    private TransferStatus transferStatus;
    private TransferStatusResponseDto transferStatusResponseDto;

    @LocalServerPort
    private int port;
    private static String baseUrl = "http://localhost:";

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

                   //Create generator
//    @Test
//    void whenFindTransferStatusByIdAndOk() {
//        transferStatus = generateTransferStatus();
//        transferStatusResponseDto = generateTransferStatusResponseDto(transferStatus);
//        Long id = setId();
//
//        TransferStatusResponseDto response = restTemplate.getForObject(baseUrl + "/{transfer_id}/status", TransferStatusResponseDto.class, id);
//
//        assertEquals(transferStatusResponseDto, response);
//        assertEquals(1, transferStatusRepository.findAll().size());
//    }

}
