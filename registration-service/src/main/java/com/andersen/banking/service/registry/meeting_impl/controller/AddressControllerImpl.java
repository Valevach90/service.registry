package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.AddressController;
import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.mapping.AddressMapper;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Addresses controller Implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @Override
    public List<AddressDto> findAll() {
        log.info("Find all offices");

        List<AddressDto> result = addressService.findAllAddresses().stream()
                .map(addressMapper::toAddressDto)
                .collect(Collectors.toList());

        log.info("Return list of all AddressDto {}", result);
        return result;
    }

    @Override
    public AddressDto findAddressByUserId(Long userId) {
        log.info("Find address by userId: {}", userId);

        AddressDto result = addressService.findAddressByUserId(userId)
                .map(addressMapper::toAddressDto)
                .orElse(null);

        log.info("Return addressDto: {}", result);
        return result;
    }

    @Override
    public AddressDto findById(Long id) {
        log.info("Find address by id: {}", id);

        AddressDto result = addressService.findById(id)
                .map(addressMapper::toAddressDto)
                .orElseThrow(() -> new NotFoundException(Address.class, id));

        log.info("Return addressDto: {}", result);
        return result;
    }

    @Override
    public void updateAddress(AddressDto addressDto) {
        log.info("Try to update address: {}", addressDto);

        Address addressUpdated = addressMapper.toAddress(addressDto);
        addressService.update(addressUpdated);

        log.info("Return updated address : {}", addressUpdated);
    }
}
