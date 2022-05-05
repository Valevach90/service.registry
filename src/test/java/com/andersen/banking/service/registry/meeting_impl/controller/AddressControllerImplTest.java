package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.AddressController;
import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_api.dto.AddressModifyDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_impl.mapping.AddressMapper;
import com.andersen.banking.service.registry.meeting_impl.mapping.AddressMapperImpl;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import com.andersen.banking.service.registry.meeting_test.generators.AddressGenerator;
import com.andersen.banking.service.registry.meeting_test.generators.AddressModifyDtoGenerator;
import com.andersen.banking.service.registry.meeting_test.generators.UserGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {
        AddressControllerImpl.class,
        AddressGenerator.class,
        UserGenerator.class,
        AddressMapperImpl.class,
        AddressModifyDtoGenerator.class
        }

)
class AddressControllerImplTest {

    private Address address;
    private AddressDto addressDto;
    private AddressModifyDto addressModifyDto;
    private Random random;

    @Autowired
    AddressController addressController;
    @Autowired
    AddressGenerator addressGenerator;
    @Autowired
    UserGenerator userGenerator;
    @Autowired
    AddressModifyDtoGenerator addressModifyDtoGenerator;
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
        addressModifyDto = addressModifyDtoGenerator.generateAddressModifyDto();
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
                .filter(element -> element.getCountry().length() <= 30)
                .limit(100).toList();

        List<AddressDto> addressesDto = addresses.stream()
                .map(addressMapper::toAddressDto)
                .toList();
        Mockito
                .when(addressService.findAllAddress())
                .thenReturn(addresses);

        var result = addressController.findAll();

        assertEquals(addressesDto, result);
    }

    @Test
    void whenUpdateAddress_andOk() {

        random = new Random();
        Address address = addressMapper.toAddress(addressModifyDto);
        address.setId(random.nextLong(100));

        addressController.updateAddress(address.getId(), addressModifyDto);

        Mockito
                .verify(addressService, Mockito.times(1))
                .update(any(Long.class), any(Address.class));
    }
}

