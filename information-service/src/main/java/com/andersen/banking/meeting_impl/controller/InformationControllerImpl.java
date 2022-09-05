package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.InformationController;
import com.andersen.banking.meeting_api.dto.BankBranchDto;
import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_api.dto.CityDtoForSearch;
import com.andersen.banking.meeting_api.dto.CountryDto;
import com.andersen.banking.meeting_api.dto.StreetDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto;
import com.andersen.banking.meeting_impl.exception.InvalidRequestException;
import com.andersen.banking.meeting_impl.service.InformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
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
    public List<CityDto> getAllCitiesByCountryId(Long countryId, Pageable pageable, boolean onlyWithBranches, boolean singlePage) {
        if (singlePage) pageable = null;
        if (onlyWithBranches) {
            log.info("get only cities with bank branches");
            return informationService.getListCityDtoWithBankBranchesByCountryId(countryId);
        } else {
            log.info("get cities");
            return informationService.getListCityDtoByCountryId(countryId, pageable);
        }

    }

    @Override
    public List<CityDto> getAllCitiesByCountryIdAndByPartOfCityName(Long countryId, Pageable pageable, CityDtoForSearch cityPartName, BindingResult result) {
        log.info("get cities which contain part of the city name");
        if(result.hasErrors()) {
            throw new InvalidRequestException("too short string: " + cityPartName);
        } else {
            return informationService.getListCityDtoByCountryIdAndPartOfCityName(countryId, cityPartName, pageable);
        }

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
