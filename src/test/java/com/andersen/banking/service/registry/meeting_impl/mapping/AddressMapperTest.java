package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.AddressModifyDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_test.generators.AddressGenerator;
import com.andersen.banking.service.registry.meeting_test.generators.AddressModifyDtoGenerator;
import com.andersen.banking.service.registry.meeting_test.generators.UserGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {
        AddressMapperImpl.class,
        AddressGenerator.class,
        UserGenerator.class,
        AddressModifyDtoGenerator.class
})
public class AddressMapperTest {


    private Address address;
    private AddressModifyDto addressModifyDto;
    private Random random;

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddressGenerator addressGenerator;
    @Autowired
    private UserGenerator userGenerator;
    @Autowired
    private AddressModifyDtoGenerator addressModifyDtoGenerator;

    @BeforeEach
    void initTestData() {
        address = addressGenerator.generateAddress(userGenerator.generateUser());
        addressModifyDto = addressModifyDtoGenerator.generateAddressModifyDto();
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

        var address = addressMapper.toAddress(addressModifyDto);

        assertNotNull(address);

        assertEquals(address.getZipCode(), addressModifyDto.getZipCode());
        assertEquals(address.getCountry(), addressModifyDto.getCountry());
        assertEquals(address.getRegion(), addressModifyDto.getRegion());
        assertEquals(address.getLocation(), addressModifyDto.getLocation());
        assertEquals(address.getCity(), addressModifyDto.getCity());
        assertEquals(address.getStreet(), addressModifyDto.getStreet());
        assertEquals(address.getHouse(), addressModifyDto.getHouse());
        assertEquals(address.getBuilding(), addressModifyDto.getBuilding());
        assertEquals(address.getFlat(), addressModifyDto.getFlat());
    }

    @Test
    void whenMapListOfAddressToListOfAddressDto_andOk() {
        random = new Random();

        List<Address> addressList = Stream.generate(() -> addressGenerator.generateAddress(userGenerator.generateUser()))
                .limit(random.nextInt(1000))
                .toList();
        var addressDtoList = addressMapper.toAddressDto(addressList);

        assertNotNull(addressDtoList);

        assertFalse(addressDtoList.isEmpty());
        assertEquals(addressList.size(), addressDtoList.size());

    }
}