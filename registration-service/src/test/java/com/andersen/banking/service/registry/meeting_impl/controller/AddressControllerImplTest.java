package com.andersen.banking.service.registry.meeting_impl.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andersen.banking.service.registry.meeting_api.controller.AddressController;
import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_impl.mapping.AddressMapper;
import com.andersen.banking.service.registry.meeting_impl.mapping.AddressMapperImpl;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import com.andersen.banking.service.registry.meeting_test.generators.AddressGenerator;
import com.andersen.banking.service.registry.meeting_test.generators.UserGenerator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {
        AddressControllerImpl.class,
        AddressGenerator.class,
        UserGenerator.class,
        AddressMapperImpl.class
        })
class AddressControllerImplTest {

    private Address address;
    private AddressDto addressDto;

    @Autowired
    AddressController addressController;
    @Autowired
    AddressGenerator addressGenerator;
    @Autowired
    UserGenerator userGenerator;
    @Autowired
    AddressMapper addressMapper;
    @MockBean
    AddressService addressService;

    @BeforeEach
    void initData(
            @Autowired AddressGenerator addressGenerator,
            @Autowired UserGenerator userGenerator,
            @Autowired AddressMapper addressMapper)
    {
        address = addressGenerator.generateAddress(userGenerator.generateUser());
        addressDto = addressMapper.toAddressDto(address);
    }

    @Test
    void whenFindById_andOk() {

        Optional<Address> addressCheck = Optional.of(address);

        Mockito
                .when(addressService.findById(address.getId()))
                .thenReturn(addressCheck);

        var result = addressController.findById(address.getId());

        assertEquals(addressDto, result);
    }


    @Test
    void whenFindByUserId_andOk() {

        Optional<Address> addressCheck = Optional.of(address);

        Mockito
                .when(addressService.findAddressByUserId(address.getUser().getId()))
                .thenReturn(addressCheck);

        var result = addressController.findAddressByUserId(address.getUser().getId());

        assertEquals(addressDto, result);
    }

    @Test
    void whenFindAll_andOk() {

        List<Address> addresses = Stream.generate(() -> addressGenerator.generateAddress(userGenerator.generateUser()))
                .limit(100).toList();

        List<AddressDto> addressesDto = addresses.stream()
                .map(addressMapper::toAddressDto)
                .toList();
        Mockito
                .when(addressService.findAllAddresses())
                .thenReturn(addresses);

        var result = addressController.findAll();

        assertEquals(addressesDto, result);
    }

    @Test
    void whenUpdateAddress_andOk() {
        Address address = addressMapper.toAddress(addressDto);

        ArgumentCaptor<Address> addressCapture = ArgumentCaptor.forClass(Address.class);
        Mockito
                .doNothing()
                .when(addressService).update(addressCapture.capture());

        addressController.updateAddress(addressDto);

        assertEquals(address.getId(), addressCapture.getValue().getId());
        assertEquals(address.getUser(), addressCapture.getValue().getUser());
        assertEquals(address.getZipCode(), addressCapture.getValue().getZipCode());
        assertEquals(address.getCountry(), addressCapture.getValue().getCountry());
        assertEquals(address.getRegion(), addressCapture.getValue().getRegion());
        assertEquals(address.getCity(), addressCapture.getValue().getCity());
        assertEquals(address.getStreet(), addressCapture.getValue().getStreet());
        assertEquals(address.getHouse(), addressCapture.getValue().getHouse());
        assertEquals(address.getBuilding(), addressCapture.getValue().getBuilding());
        assertEquals(address.getFlat(), addressCapture.getValue().getFlat());
    }
}

