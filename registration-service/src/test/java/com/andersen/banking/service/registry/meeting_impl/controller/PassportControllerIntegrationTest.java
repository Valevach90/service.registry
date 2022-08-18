package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.PassportController;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.PassportRepository;
import com.andersen.banking.service.registry.meeting_test.generators.AddressGenerator;
import com.andersen.banking.service.registry.meeting_test.generators.PassportGenerator;
import com.andersen.banking.service.registry.meeting_test.generators.UserGenerator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PassportControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PassportController passportController;

    @Value("application/json")
    private String contentType;

    private final Random random = new Random();

    private static List<Passport> passports;

    private static final String URL_PASSPORTS_ALL = "/api/v1/passport/all";
    private static final String URL_PASSPORTS = "/api/v1/passport/";
    private static final String PARAM_NUMBER = "number";
    private static final String PARAM_SIZE = "size";
    private static final String PARAM_PAGE = "page";
    private static final String PARAM_TOTAL_ELEMENTS = "totalElements";
    private static final String PARAM_TOTAL_PAGES = "totalPages";
    private static final String PARAM_FIRST = "first";
    private static final String PARAM_LAST = "last";
    private static final String PARAM_CONTENT = "$.content";
    private static final String PARAM_CONTENT_FIRST_NAME = "$.content[*].firstName";
    private static final String PARAM_CONTENT_LAST_NAME = "$.content[*].lastName";

    @BeforeEach
    void add(
            @Autowired PassportRepository passportRepository,
            @Autowired PassportGenerator passportGenerator,
            @Autowired AddressGenerator addressGenerator,
            @Autowired UserGenerator userGenerator) {

        passports = Stream.generate(() -> {
            User user = userGenerator.generateUser();
            return passportGenerator.generatePassport(user, addressGenerator.generateAddress(user));
        })
                .limit(100)
                .toList();
        passports.forEach(passportRepository::save);
    }

    @AfterAll
    static void clear(
            @Autowired PassportRepository passportRepository) {
        passportRepository.deleteAll();
    }

    @Test
    void contextLoads() {
        assertThat(passportController).isNotNull();
    }

    @Test
    void whenFindAll_andOk() throws Exception {
        int pageSize = passports.size() / 5 == 0 ? 1 : passports.size() / 5;
        int pageNumber = passports.size() / pageSize / 3;

        mockMvc.perform(
                get(URL_PASSPORTS_ALL)
                        .param(PARAM_PAGE, String.valueOf(pageNumber))
                        .param(PARAM_SIZE, String.valueOf(pageSize))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath(PARAM_NUMBER).value(pageNumber))
                .andExpect(jsonPath(PARAM_SIZE).value(pageSize))
                .andExpect(jsonPath(PARAM_TOTAL_ELEMENTS).value(passports.size()))
                .andExpect(jsonPath(PARAM_TOTAL_PAGES).value(passports.size() % pageSize == 0 ? passports.size() / pageSize : passports.size() / pageSize + 1))
                .andExpect(jsonPath(PARAM_FIRST).value(pageNumber == 0))
                .andExpect(jsonPath(PARAM_LAST).value(passports.size() / pageSize == pageNumber))
                .andExpect(jsonPath(PARAM_CONTENT).isArray())
                .andExpect(jsonPath(PARAM_CONTENT, hasSize(pageSize)))
                .andExpect(jsonPath(PARAM_CONTENT_FIRST_NAME, Matchers.hasItems(passports.stream()
                        .filter(passport -> passport.getId() > (long) pageSize * pageNumber && passport.getId() <= pageSize * (pageNumber + 1L))
                        .map(Passport::getFirstName)
                        .distinct()
                        .toArray())))
                .andExpect(jsonPath(PARAM_CONTENT_LAST_NAME, Matchers.hasItems(passports.stream()
                        .filter(passport -> passport.getId() > (long) pageSize * pageNumber && passport.getId() <= pageSize * (pageNumber + 1L))
                        .map(Passport::getLastName)
                        .distinct()
                        .toArray())));
    }

    @Test
    void whenGetByIdAndOk() throws Exception {
        var passport = passports.get(random.nextInt(0, passports.size()));

        mockMvc.perform(
                get(URL_PASSPORTS + passport.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(passport.getId()))
                .andExpect(jsonPath("$.firstName").value(passport.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(passport.getLastName()))
                .andExpect(jsonPath("$.passportCode").value(passport.getPassportCode()))
                .andExpect(jsonPath("$.birthday").value(passport.getBirthday().toString()))
                .andExpect(jsonPath("$.bornPlace").value(passport.getBornPlace()))
                .andExpect(jsonPath("$.dateIssue").value(passport.getDateIssue().toString()))
                .andExpect(jsonPath("$.departmentIssued").value(passport.getDepartmentIssued()))
                .andExpect(jsonPath("$.terminationDate").value(passport.getTerminationDate().toString()))
                .andExpect(jsonPath("$.patronymic").value(passport.getPatronymic()))
                .andExpect(jsonPath("$.serialNumber").value(passport.getSerialNumber()));
    }
}
