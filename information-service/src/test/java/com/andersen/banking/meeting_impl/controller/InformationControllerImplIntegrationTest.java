package com.andersen.banking.meeting_impl.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.andersen.banking.generators.GenerateDataForInformationService;
import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_api.dto.CityDtoForSearch;
import com.andersen.banking.meeting_db.entities.City;
import com.andersen.banking.meeting_db.entities.Country;
import com.andersen.banking.meeting_db.repositories.CityRepository;
import com.andersen.banking.meeting_db.repositories.CountryRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@PropertySource("classpath:application.properties")
class InformationControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    private City city;

    private Country country;

    private final Long countryId = 1L;

    private CityDtoForSearch cityDtoForSearch;

    private CityDtoForSearch wrongCityDtoForSearch;

    private final String exception = "{\"message\":\"To search by name, you must pass at least three characters\",\"debugMessage\":\"too short string: CityDtoForSearch(name=mo)\"}";

    @BeforeEach
    void initialize() {
        country = GenerateDataForInformationService.country;
        countryRepository.save(country);

        city = GenerateDataForInformationService.generateCity();
        cityRepository.save(city);

        /**
         * for test - too short string "osco" (part of "Moscow")
         */
        cityDtoForSearch = GenerateDataForInformationService.generateCityDtoForSearch("osco");

        /**
         * for test first validation - too short string "mo"
         */
        wrongCityDtoForSearch = GenerateDataForInformationService.generateWrongCityDtoForSearch();

    }

    @Test
    void shouldReturnOk_getListCityDtoByCountryIdAndPartOfCityNameTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/api/v1/information/country/" + countryId + "/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cityDtoForSearch));

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);
        String result = resultActions
                .andReturn()
                .getResponse()
                .getContentAsString();

        resultActions.andExpect(status().isOk());

        List<CityDto> list = objectMapper.readValue(result, new TypeReference<>() {});

        assertEquals(1, list.size());
    }

    @Test
    void shouldReturnException_getListCityDtoByCountryIdAndPartOfCityNameTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/api/v1/information/country/" + countryId + "/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wrongCityDtoForSearch));

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);
        String result = resultActions
                .andReturn()
                .getResponse()
                .getContentAsString();

        resultActions.andExpect(status().is(400));
        assertEquals(exception, result);
    }
}
