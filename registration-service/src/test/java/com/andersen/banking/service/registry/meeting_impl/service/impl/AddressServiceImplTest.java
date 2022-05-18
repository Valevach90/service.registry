package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.repositories.AddressRepository;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import com.andersen.banking.service.registry.meeting_test.generators.AddressGenerator;
import com.andersen.banking.service.registry.meeting_test.generators.UserGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        AddressGenerator.class,
        UserGenerator.class,
        AddressServiceImpl.class
})
class AddressServiceImplTest {

    private Address address;

    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressGenerator addressGenerator;
    @Autowired
    private UserGenerator userGenerator;

    @BeforeEach
    void initData(
            @Autowired AddressGenerator addressGenerator,
            @Autowired UserGenerator userGenerator)
    {
        address = addressGenerator.generateAddress(userGenerator.generateUser());
    }

    @Test
    void whenFindById_andOk() {

        Optional<Address> addressCheck = Optional.of(address);

        Mockito
        .when(addressRepository.findById(address.getId()))
        .thenReturn(addressCheck);

        Optional<Address> result = addressService.findById(address.getId());

        assertEquals(addressCheck, result);
    }

    @Test
    void whenFindAddressByUserId_andOk() {

        Optional<Address> addressCheck = Optional.of(address);

        Mockito
                .when(addressRepository.findAddressByUserId(address.getUser().getId()))
                .thenReturn(addressCheck);

        Optional<Address> result = addressService.findAddressByUserId(address.getUser().getId());

        assertEquals(addressCheck, result);
    }

    @Test
    void findAllAddress_andOk() {

        List<Address> addresses = Stream.generate(() -> addressGenerator.generateAddress(userGenerator.generateUser()))
                .filter(element -> element.getCountry().length() <= 30)
                .limit(100).toList();
        Mockito
                .when(addressRepository.findAll())
                .thenReturn(addresses);

        List<Address> result = addressService.findAllAddresses();

        assertEquals(addresses, result);
    }

    @Test
    void whenUpdate_andOk() {

        Optional<Address> addressUpdated = Optional.of(address);

        Mockito
                .when(addressRepository.findById(address.getId()))
                .thenReturn(addressUpdated);

        addressService.update(addressUpdated.get());

        assertEquals(address.getZipCode(), addressUpdated.get().getZipCode());
        assertEquals(address.getCountry(), addressUpdated.get().getCountry());
        assertEquals(address.getRegion(), addressUpdated.get().getRegion());
        assertEquals(address.getCity(), addressUpdated.get().getCity());
        assertEquals(address.getStreet(), addressUpdated.get().getStreet());
        assertEquals(address.getHouse(), addressUpdated.get().getHouse());
        assertEquals(address.getBuilding(), addressUpdated.get().getBuilding());
        assertEquals(address.getFlat(), addressUpdated.get().getFlat());

        Mockito
                .verify(addressRepository, Mockito.times(1))
                .save(addressUpdated.get());
    }
}