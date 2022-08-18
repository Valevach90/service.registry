package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.repositories.AddressRepository;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import com.andersen.banking.service.registry.meeting_test.generators.AddressGenerator;
import com.andersen.banking.service.registry.meeting_test.generators.UserGenerator;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AddressControllerImplIntegrationTest {

    private Address address;

    @Autowired
    private AddressService addressService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private AddressControllerImpl addressController;

    private final Random random = new Random();

    @BeforeEach
    void add(
            @Autowired AddressRepository addressRepository,
            @Autowired AddressGenerator addressGenerator,
            @Autowired UserGenerator userGenerator)
    {
       Stream.generate(() -> addressGenerator.generateAddress(userGenerator.generateUser()))
               .limit(100)
               .forEach(addressRepository::save);
    }

    @AfterAll
    static void clear(
            @Autowired AddressRepository addressRepository){
        addressRepository.deleteAll();
    }

    @Test
    void contextLoads() {
        assertThat(addressController).isNotNull();
    }

    @Test
    void whenFindAll_andOk() throws Exception {

        List<Address> allAddresses = addressService.findAllAddresses();

        assertNotNull(allAddresses);
        assertFalse(allAddresses.isEmpty());

        mvc
                .perform(get("/api/v1/addresses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void whenFindById_andOk() throws Exception {

        address = addressService.findById(random.nextLong(0, addressService.findAllAddresses().size())).orElse(null);

        mvc.perform(
                        get("/api/v1/addresses/" + address.getId())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(address.getId()))
                .andExpect(jsonPath("$.userId").value(address.getUser().getId()))
                .andExpect(jsonPath("$.zipCode").value(address.getZipCode()))
                .andExpect(jsonPath("$.country").value(address.getCountry()))
                .andExpect(jsonPath("$.region").value(address.getRegion()))
                .andExpect(jsonPath("$.location").value(address.getLocation()))
                .andExpect(jsonPath("$.city").value(address.getCity()))
                .andExpect(jsonPath("$.street").value(address.getStreet()))
                .andExpect(jsonPath("$.house").value(address.getHouse()))
                .andExpect(jsonPath("$.building").value(address.getBuilding()))
                .andExpect(jsonPath("$.flat").value(address.getFlat()))
                .andDo(print());
    }

    @Test
    void whenFindByUserId_andOk() throws Exception {

        address = addressService.findById(random.nextLong(0, addressService.findAllAddresses().size())).orElse(null);

        mvc.perform(
                        get("/api/v1/addresses/user/" + address.getUser().getId())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(address.getId()))
                .andExpect(jsonPath("$.userId").value(address.getUser().getId()))
                .andExpect(jsonPath("$.zipCode").value(address.getZipCode()))
                .andExpect(jsonPath("$.country").value(address.getCountry()))
                .andExpect(jsonPath("$.region").value(address.getRegion()))
                .andExpect(jsonPath("$.location").value(address.getLocation()))
                .andExpect(jsonPath("$.city").value(address.getCity()))
                .andExpect(jsonPath("$.street").value(address.getStreet()))
                .andExpect(jsonPath("$.house").value(address.getHouse()))
                .andExpect(jsonPath("$.building").value(address.getBuilding()))
                .andExpect(jsonPath("$.flat").value(address.getFlat()))
                .andDo(print());
    }

    @Test
    void whenUpdateAddress_andOk() throws Exception {

        address = addressService.findById(random.nextLong(0, addressService.findAllAddresses().size())).orElse(null);

        address.setZipCode(999_999);
        address.setCountry("Belarus");
        address.setRegion("Vitebsk");
        address.setCity("Minsk");
        address.setStreet("Komsomolskaya");
        address.setHouse("99999");
        address.setBuilding("99999");
        address.setFlat("99999");
        addressService.update(address);

        mvc.perform(
                        get("/api/v1/addresses/" + address.getId())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(address.getId()))
                .andExpect(jsonPath("$.userId").value(address.getUser().getId()))
                .andExpect(jsonPath("$.zipCode").value(address.getZipCode()))
                .andExpect(jsonPath("$.country").value(address.getCountry()))
                .andExpect(jsonPath("$.region").value(address.getRegion()))
                .andExpect(jsonPath("$.location").value(address.getLocation()))
                .andExpect(jsonPath("$.city").value(address.getCity()))
                .andExpect(jsonPath("$.street").value(address.getStreet()))
                .andExpect(jsonPath("$.house").value(address.getHouse()))
                .andExpect(jsonPath("$.building").value(address.getBuilding()))
                .andExpect(jsonPath("$.flat").value(address.getFlat()))
                .andDo(print());
    }
}