package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.AddressController;
import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_api.dto.AddressModifyDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.mapping.AddressMapper;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        List<Address> allAddresses = addressService.findAllAddress();
        List<AddressDto> result = addressMapper.toAddressDto(allAddresses);

        log.info("Return list of all AddressDto {}", result);
        return result;
    }

    @Override
    public AddressDto findAddressByUserId(Long userId) {
        log.info("Find address by userId: {}", userId);

        Address address = addressService.findAddressByUserId(userId).orElse(null);
        AddressDto result = addressMapper.toAddressDto(address);

        log.info("Return addressDto: {}", result);
        return result;
    }

    @Override
    public AddressDto findById(Long id) {
        log.info("Find address by id: {}", id);

        Address address = addressService.findById(id).orElse(null);
        AddressDto result = addressMapper.toAddressDto(address);

        log.info("Return addressDto: {}", result);
        return result;
    }

    @Override
    public void updateAddress(Long id, AddressModifyDto addressModifyDto) {
        log.info("Try to update address: {}", addressModifyDto);

        Address addressUpdate = addressMapper.toAddressEntity(addressModifyDto);

        Address address = addressService.findById(id)
                .orElseThrow(() -> new NotFoundException(Address.class, id));

        addressUpdate.setId(id);
        addressUpdate.setUser(address.getUser());

        addressMapper.updateAddressDetails(address, addressUpdate);

        addressService.update(address);

        log.info("Return updated address : {}", address);
    }
}
