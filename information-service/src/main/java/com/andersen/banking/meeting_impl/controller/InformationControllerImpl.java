package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.InformationController;
import com.andersen.banking.meeting_api.dto.*;
import com.andersen.banking.meeting_impl.service.InformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InformationControllerImpl implements InformationController {

    private final InformationService informationService;

    @Override
    public List<CountryDto> getAllCountries() {
        log.info("get countries");
        return informationService.getListCountryDto();
    }


    @Override
    public List<CityDto> getAllCitiesByCountryId(Long countryId, Pageable pageable) {
        log.info("get cities");
        return informationService.getListCityDtoByCountryId(countryId, pageable);
    }

    @Override
    public List<StreetDto> getAllStreetsByCityId(Long cityId) {
        log.info("get streets");
        return informationService.getListStreetDtoByCityId(cityId);
    }


    @Override
    public List<BankBranchDto> getAllBankBranchesByCityId(Long addressId) {
        log.info("get bank branches");
        return informationService.getListBankBranchDtoByCityId(addressId);
    }

    @Override
    public List<TimeTableDto> getAllTimeTablesByBranchId(Long addressId) {
        log.info("get timetables");
        return informationService.getListTimeTableDtoByBranchId(addressId);
    }
}
