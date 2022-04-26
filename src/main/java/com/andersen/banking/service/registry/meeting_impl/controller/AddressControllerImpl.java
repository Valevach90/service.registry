package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.AddressController;
import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_api.dto.AddressModifyDto;
import com.andersen.banking.service.registry.meeting_impl.service.processing.AddressProcessingService;
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

    private final AddressProcessingService addressProcessingService;

    @Override
    public List<AddressDto> findAll() {
        log.trace("Find all addresses");

        List<AddressDto> list = addressProcessingService.findAllAddresses();

        log.trace("Return list of Addresses: {}", list);
        return list;
    }

    @Override
    public AddressDto findAddressByUserId(Long userId) {
        log.trace("Find address by userId: {}", userId);

        AddressDto returnDto = addressProcessingService.findAddressByUserId(userId);

        log.trace("Return address: {}", returnDto);
        return returnDto;
    }

    @Override
    public AddressDto findById(Long id) {
        log.trace("Find address by id: {}", id);

        AddressDto returnDto = addressProcessingService.findById(id);

        log.trace("Return address: {}", returnDto);
        return returnDto;
    }

    @Override
    public void updateAddress(Long id, AddressModifyDto addressModifyDto) {
        log.trace("Try to update event: {}", addressModifyDto);

        addressProcessingService.updateAddress(id, addressModifyDto);

        log.trace("Updated address by update address");
    }
}
