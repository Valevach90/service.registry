package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.UserController;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.UserRepository;
import com.andersen.banking.service.registry.meeting_test.generators.UserGenerator;
import com.andersen.banking.service.registry.testcontainer.IntegrationTestWithPostgres;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@IntegrationTestWithPostgres
class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserController userController;

    private final Random random = new Random();

    private static List<User> users;

    private static final String URL_USERS_ALL = "/api/v1/users";
    private static final String URL_USERS = "/api/v1/users/";
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

    @BeforeAll
    static void add(
            @Autowired UserRepository userRepository,
            @Autowired UserGenerator userGenerator) {
             users = Stream.generate(userGenerator::generateUser)
                .limit(100)
                .collect(Collectors.toList());
             userRepository.saveAll(users);
    }

    @AfterAll
    static void clear(
            @Autowired UserRepository passportRepository) {
        passportRepository.deleteAll();
    }

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    void whenFindAll_andOk() throws Exception {
        int pageSize = users.size() / 5 == 0 ? 1 : users.size() / 5;
        int pageNumber = users.size() / pageSize / 3;

        mockMvc.perform(
                        get(URL_USERS_ALL)
                                .param(PARAM_PAGE, String.valueOf(pageNumber))
                                .param(PARAM_SIZE, String.valueOf(pageSize))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(PARAM_NUMBER).value(pageNumber))
                .andExpect(jsonPath(PARAM_SIZE).value(pageSize))
                .andExpect(jsonPath(PARAM_TOTAL_ELEMENTS).value(users.size()))
                .andExpect(jsonPath(PARAM_TOTAL_PAGES).value(users.size() % pageSize == 0 ? users.size() / pageSize : users.size() / pageSize + 1))
                .andExpect(jsonPath(PARAM_FIRST).value(pageNumber == 0))
                .andExpect(jsonPath(PARAM_LAST).value(users.size() / pageSize == pageNumber))
                .andExpect(jsonPath(PARAM_CONTENT).isArray())
                .andExpect(jsonPath(PARAM_CONTENT, hasSize(pageSize)))
                .andExpect(jsonPath(PARAM_CONTENT_FIRST_NAME, Matchers.hasItems(users.stream()
                        .filter(user -> user.getId() > (long) pageSize * pageNumber && user.getId() <= pageSize * (pageNumber + 1L))
                        .map(User::getFirstName)
                        .distinct()
                        .toArray())))
                .andExpect(jsonPath(PARAM_CONTENT_LAST_NAME, Matchers.hasItems(users.stream()
                        .filter(passport -> passport.getId() > (long) pageSize * pageNumber && passport.getId() <= pageSize * (pageNumber + 1L))
                        .map(User::getLastName)
                        .distinct()
                        .toArray())))
        ;
    }

    @Test
    void whenGetByIdAndOk() throws Exception {

        int userId = random.nextInt(0, 100);
        var user = users.get(userId);
        user.setId(userId + 1L);

        mockMvc.perform(
                        get(URL_USERS + user.getId())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.first_name").value(user.getFirstName()))
                .andExpect(jsonPath("$.last_name").value(user.getLastName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.patronymic").value(user.getPatronymic()))
                .andExpect(jsonPath("$.phone").value(user.getPhone()));
    }
}

