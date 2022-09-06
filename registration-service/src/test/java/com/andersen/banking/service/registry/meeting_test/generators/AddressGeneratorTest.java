package com.andersen.banking.service.registry.meeting_test.generators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = AddressGenerator.class)
class AddressGeneratorTest {

    @Autowired
    private AddressGenerator addressGenerator;

    @MockBean
    private User user;

    @Test
    void whenCreateAddress_allFieldCreated() {
        Address address = addressGenerator.generateAddress(user);

        assertNotNull(address.getId());
        assertNotNull(address.getZipCode());
        assertNotNull(address.getCountry());
        assertNotNull(address.getRegion());
        assertNotNull(address.getCity());
        assertNotNull(address.getStreet());
        assertNotNull(address.getHouse());
        assertNotNull(address.getBuilding());
        assertNotNull(address.getFlat());
        assertEquals(user, address.getUser());
    }

    @Test
    void whenCreateTwoAddresses_theyAreDifferent() {
        Address address1 = addressGenerator.generateAddress(user);
        Address address2 = addressGenerator.generateAddress(user);

        assertNotEquals(address1, address2);
    }
}