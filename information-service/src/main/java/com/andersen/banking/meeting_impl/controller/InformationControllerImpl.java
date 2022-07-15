package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.InformationController;
import com.andersen.banking.meeting_api.dto.*;
import com.andersen.banking.meeting_impl.service.InformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<CityDto> getAllCitiesByCountryId(Long countryId) {
        log.info("get cities");
        return informationService.getListCityDtoByCountryId(countryId);
    }

    @Override
    public List<StreetDto> getAllStreetsByCityId(Long cityId) {
        log.info("get streets");
        return informationService.getListStreetDtoByCityId(cityId);
    }

    @Override
    public List<AddressDto> getAllAddressesByStreetId(Long streetId) {
        log.info("get addresses");
        return informationService.getListAddressDtoByStreetId(streetId);
    }

    @Override
    public List<BankBranchDto> getAllBankBranchesByAddressId(Long addressId) {
        log.info("get bank branches");
        return informationService.getListBankBranchDtoByAddressId(addressId);
    }

    @Override
    public List<TimeTableDto> getAllTimeTablesByAddressId(Long addressId) {
        log.info("get timetables");
        return informationService.getListTimeTableDtoByAddressId(addressId);
    }
}
