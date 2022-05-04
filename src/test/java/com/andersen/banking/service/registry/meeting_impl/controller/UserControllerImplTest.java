package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.UserRepository;
import com.andersen.banking.service.registry.meeting_impl.service.local.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@WithMockUser
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Container
    public static final PostgreSQLContainer<?> postgreSQLContainer =
            (new PostgreSQLContainer<>("postgres"))
                    .withDatabaseName("registry-service")
                    .withUsername("benefit")
                    .withPassword("ben01479");

    @DynamicPropertySource
    public static void postgreSQLProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    }

    @Before
    public void initData(){
        userRepository.deleteAll();
    }



    @Test
    @Order(1)
    void whenAllUsersFindSuccess() throws Exception {
        User userFirst = new User(1L,"Sasha", "Drinov", "Labovich", "sasha@mail.com", "787-57-75");
        User userSecond = new User(2L,"Dima", "Vlasov", "Slenchaev", "dima@mail.com", "799-78-77");
        User userThird = new User(3L,"Vasya", "Norris", "Fringer", "vasya@mail.com", "563-23-34");
        List<User> userList = new ArrayList<>();
        userList.add(userFirst);
        userList.add(userSecond);
        userList.add(userThird);
        userRepository.saveAll(userList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1//users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0]firstName", is("Sasha")))
                .andExpect(jsonPath("$[1]firstName", is("Dima")))
                .andExpect(jsonPath("$[2]firstName", is("Vasya")));
    }

    @Test
    @Order(2)
    public void whenGetUserByIdPositiveScenario() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Sasha")));
    }

    @Test
    @Order(3)
    public void whenGetUserByIdNegativeScenario() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/{id}", 5)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message", is("Not found by id")));

    }


    @Test
    @Order(4)
    public void whenAddUserPositiveScenario() throws Exception {
        User userExpected = new User(4L,"Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");

        mockMvc.perform(post("/api/v1/users")
                .content(objectMapper.writeValueAsString(userExpected))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.firstName", is("Chuck")));
    }


    @Test
    @Order(5)
    public void whenUpdateUserPositiveScenario() throws Exception {
        User newUser = new User(1L,"Dima", "Mickailovich", "Svetlichni", "micha@mail.com", "888-88-88");

        userService.updateUser(1L, newUser);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Dima")));


    }

    @Test
    @Order(6)
    public void whenDeletePositiveScenario() throws Exception {
        User user= new User(1L,"Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");
        userRepository.save(user);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void whenDeleteNegativeScenario() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/users/{id}", 5)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message", is("Not found by id")));
    }

}