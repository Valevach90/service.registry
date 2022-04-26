package com.andersen.banking.service.registry.meeting_impl.service.processing.impl;

import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_api.dto.AddressModifyDto;
import com.andersen.banking.service.registry.meeting_db.entities.AddressEntity;
import com.andersen.banking.service.registry.meeting_impl.mapping.AddressMapper;
import com.andersen.banking.service.registry.meeting_impl.service.local.AddressService;
import com.andersen.banking.service.registry.meeting_impl.service.processing.AddressProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressProcessingServiceImpl implements AddressProcessingService {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @Override
    public AddressDto findById(Long addressId) {
        log.info("Find address by id: {}", addressId);

        AddressEntity address = addressService.findById(addressId);
        AddressDto result = addressMapper.toAddressDto(address);

        log.info("Return addressDto: {}", result);
        return result;
    }

    @Override
    public AddressDto findAddressByUserId(Long userId) {
        log.info("Find address by userId: {}", userId);

        AddressEntity address = addressService.findAddressByUserId(userId);
        AddressDto result = addressMapper.toAddressDto(address);

        log.info("Return addressDto: {}", result);
        return result;
    }

    @Override
    public List<AddressDto> findAllAddresses() {
        log.info("Find all offices");

        List<AddressEntity> allAddresses = addressService.findAllAddress();
        List<AddressDto> result = allAddresses
                .stream()
                .map(addressMapper::toAddressDto)
                .collect(Collectors.toList());

        log.info("Return list of all AddressDto {}", result);
        return result;
    }

    @Override
    public void updateAddress(Long addressId, AddressModifyDto addressUpdate) {
        log.info("Try to update address: {}", addressUpdate);

        AddressEntity addressUpdateEntity = addressMapper.toAddressEntity(addressUpdate);

        AddressEntity address = addressService.findById(addressId);

        addressUpdateEntity.setId(addressId);
        addressUpdateEntity.setUser(address.getUser());

        addressMapper.updateAddressDetails(address, addressUpdateEntity);

        addressService.save(address);

        log.info("Return updated address : {}", address);
    }
}
