package com.andersen.banking.service.registry.meeting_impl.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_test.generators.AddressGenerator;
import com.andersen.banking.service.registry.meeting_test.generators.UserGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        AddressMapperImpl.class,
        AddressGenerator.class,
        UserGenerator.class
})
public class AddressMapperTest {


    private Address address;
    private AddressDto addressDto;

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddressGenerator addressGenerator;
    @Autowired
    private UserGenerator userGenerator;

    @BeforeEach
    void initTestData() {
        address = addressGenerator.generateAddress(userGenerator.generateUser());
        addressDto = addressMapper.toAddressDto(address);
    }

    @Test
    void whenMapAddressToAddressDto_andOK() {

        var addressDto = addressMapper.toAddressDto(address);
        assertNotNull(addressDto);

        assertEquals(address.getId(), addressDto.getId());
        assertEquals(address.getUser().getId(), addressDto.getUserId());
        assertEquals(address.getZipCode(), addressDto.getZipCode());
        assertEquals(address.getCountry(), addressDto.getCountry());
        assertEquals(address.getRegion(), addressDto.getRegion());
        assertEquals(address.getLocation(), addressDto.getLocation());
        assertEquals(address.getCity(), addressDto.getCity());
        assertEquals(address.getStreet(), addressDto.getStreet());
        assertEquals(address.getHouse(), addressDto.getHouse());
        assertEquals(address.getBuilding(), addressDto.getBuilding());
        assertEquals(address.getFlat(), addressDto.getFlat());
    }

    @Test
    void whenMapAddressDtoToAddress_andOK() {

        var address = addressMapper.toAddress(addressDto);

        assertNotNull(address);

        assertEquals(address.getZipCode(), addressDto.getZipCode());
        assertEquals(address.getCountry(), addressDto.getCountry());
        assertEquals(address.getRegion(), addressDto.getRegion());
        assertEquals(address.getLocation(), addressDto.getLocation());
        assertEquals(address.getCity(), addressDto.getCity());
        assertEquals(address.getStreet(), addressDto.getStreet());
        assertEquals(address.getHouse(), addressDto.getHouse());
        assertEquals(address.getBuilding(), addressDto.getBuilding());
        assertEquals(address.getFlat(), addressDto.getFlat());
    }
}