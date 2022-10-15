package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.AtmController;
import com.andersen.banking.meeting_api.dto.AtmDtoRequest;
import com.andersen.banking.meeting_api.dto.AtmDtoResponse;
import com.andersen.banking.meeting_db.entities.Atm;
import com.andersen.banking.meeting_impl.mapper.AtmMapper;
import com.andersen.banking.meeting_impl.service.AtmService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AtmControllerImpl implements AtmController {

    private final AtmService atmService;

    private final AtmMapper atmMapper;

    @Override
    public AtmDtoResponse create(AtmDtoRequest atmDtoRequest) {
        log.debug("Create ATM: {}", atmDtoRequest);

        Atm atm = atmMapper.toAtm(atmDtoRequest);

        Atm savedAtm = atmService.create(atm);

        AtmDtoResponse savedAtmDto = atmMapper.toAtmDtoResponse(savedAtm);

        log.debug("Created ATM: {}", savedAtmDto);
        return savedAtmDto;
    }

    @Override
    public AtmDtoResponse getById(UUID id) {
        log.debug("Get ATM by id: {}", id);

        Atm atm = atmService.findById(id);

        AtmDtoResponse atmDtoResponse = atmMapper.toAtmDtoResponse(atm);

        log.debug("find an ATM: {}", atmDtoResponse);
        return atmDtoResponse;
    }

    @Override
    public Page<AtmDtoResponse> getAll(Pageable pageable) {
        log.debug("Get all ATM for pageable: {}", pageable);

        Page<AtmDtoResponse> allAtmDto = atmService.findAll(pageable)
                .map(atmMapper::toAtmDtoResponse);

        log.debug("Found {} ATM", allAtmDto.getContent().size());
        return allAtmDto;
    }

    @Override
    public Page<AtmDtoResponse> getAllAtmOnTheStreet(Long streetId, Pageable pageable) {
        log.debug("Find all ATM on the streetId {} and pageable: {}", streetId, pageable);

        Page<AtmDtoResponse> pageOfAtm = atmService.findAllByStreetId(streetId, pageable)
                .map(atmMapper::toAtmDtoResponse);

        log.debug("Found {} ATM on the streetId {}", pageOfAtm.getContent().size(), streetId);
        return pageOfAtm;
    }

    @Override
    public Page<AtmDtoResponse> getAllAtmOnTheBankBranchByBranchId(Long bankBranchId, Pageable pageable) {
        log.debug("Find all ATM on the bank branch with id {} and pageable: {}", bankBranchId, pageable);

        Page<AtmDtoResponse> pageOfAtm = atmService.findAllByBankBranchId(bankBranchId, pageable)
                .map(atmMapper::toAtmDtoResponse);

        log.debug("Found {} ATM on the bank branch wit id {}", pageOfAtm.getContent().size(), bankBranchId);
        return pageOfAtm;
    }

    @Override
    public AtmDtoResponse update(AtmDtoRequest atmDtoRequest) {
        log.debug("Updating ATM {}", atmDtoRequest);

        Atm atm = atmMapper.toAtm(atmDtoRequest);

        Atm updatedAtm = atmService.update(atm);

        AtmDtoResponse updatedAtmDtoResponse = atmMapper.toAtmDtoResponse(updatedAtm);

        log.debug("Updated ATM: {}", updatedAtmDtoResponse);
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Deleting ATM with id: {}", id);

        atmService.deleteById(id);

        log.debug("ATM with id {} Deleted successfully", id);
    }
}
