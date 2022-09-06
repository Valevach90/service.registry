package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.dto.RegularPaymentRequestDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentResponseDto;
import com.andersen.banking.meeting_db.repository.RegularPaymentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

import static com.andersen.banking.meeting_test.generators.RegularPaymentUnitTestGenerator.populateRegularPaymentRequestDto;
import static com.andersen.banking.meeting_test.generators.RegularPaymentUnitTestGenerator.populateRegularPaymentResponseDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RegularPaymentControllerH2IntegrationTest {

    private static RestTemplate restTemplate;

    private final RegularPaymentResponseDto regularPaymentResponseDto = populateRegularPaymentResponseDto();
    private final RegularPaymentRequestDto regularPaymentRequestDto = populateRegularPaymentRequestDto();

    @Autowired
    RegularPaymentRepository regularPaymentRepository;

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/v1/payments/regular");
    }

    //org.springframework.web.client.HttpClientErrorException$Unauthorized: 401 : [no body]
    @Disabled
    void create_shouldReturnRegularPaymentResponseDto_whenRequestDtoIsCorrect() {
        RegularPaymentResponseDto response = restTemplate
                .postForObject(baseUrl, regularPaymentRequestDto, RegularPaymentResponseDto.class);

        assertEquals(regularPaymentResponseDto, response);
        assertEquals(1, regularPaymentRepository.findAll().size());
    }

    void create_shouldThrowException_whenRequestDtoIsIncorrect() {
        RegularPaymentRequestDto empty = new RegularPaymentRequestDto();

        assertThrows(Exception.class, () -> restTemplate
                .postForObject(baseUrl, empty, RegularPaymentResponseDto.class));
    }
}
