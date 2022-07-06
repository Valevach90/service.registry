package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.InformationController;
import com.andersen.banking.meeting_api.dto.AddressDto;
import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_api.dto.CountryDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto;
import com.andersen.banking.meeting_impl.service.InformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
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
    public List<AddressDto> getAllAddressesByCityId(Long cityId) {
        log.info("get addresses");
        return informationService.getListAddressDtoByCityId(cityId);
    }

    @Override
    public List<TimeTableDto> getAllTimeTablesByAddressId(Long addressId) {
        log.info("get timetables");
        return informationService.getListTimeTableDtoByAddressId(addressId);
    }
}
