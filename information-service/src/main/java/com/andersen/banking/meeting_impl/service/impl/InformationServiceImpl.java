package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.*;
import com.andersen.banking.meeting_db.repositories.*;
import com.andersen.banking.meeting_impl.mapper.*;
import com.andersen.banking.meeting_impl.service.InformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {

    private final AddressRepository addressRepository;

    private final BankBranchRepository bankBranchRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    private final StreetRepository streetRepository;
    private final TimeTableRepository timeTableRepository;


    private final AddressMapper addressMapper;

    private final BankBranchMapper bankBranchMapper;

    private final CityMapper cityMapper;
    private final CountryMapper countryMapper;

    private final StreetMapper streetMapper;

    private final TimeTableMapper timeTableMapper;

    @Override
    public List<CountryDto> getListCountryDto() {
        log.debug("get countries");
        return countryRepository.getCountriesByDeletedIsFalse()
                .stream().map(countryMapper::country2CountryDto).collect(Collectors.toList());
    }

    @Override
    public List<CityDto> getListCityDtoByCountryId(Long countryId) {
        log.debug("get cities by countryId : {}", countryId);
        return cityRepository.getCitiesByCountry_IdAndDeletedIsFalse(countryId)
                .stream().map(cityMapper::city2CityDto).collect(Collectors.toList());
    }

    @Override
    public List<StreetDto> getListStreetDtoByCityId(Long cityId) {
        log.debug("get streets by cityId : {}", cityId);
        return streetRepository.getTimeTableByCity_IdAndDeletedIsFalse(cityId)
                .stream().map(streetMapper::street2StreetDto).collect(Collectors.toList());
    }


    @Override
    public List<AddressDto> getListAddressDtoByStreetId(Long streetId) {
        log.debug("get addresses by streetId : {}", streetId);
        return addressRepository.getAddressByStreet_IdAndDeletedIsFalse(streetId)
                .stream().map(addressMapper::address2AddressDto).collect(Collectors.toList());
    }

    @Override
    public List<TimeTableDto> getListTimeTableDtoByAddressId(Long addressId) {
        log.debug("get timetables by addressId: {}", addressId);
        return timeTableRepository.getTimeTableByAddress_IdAndDeletedIsFalse(addressId)
                .stream().map(timeTableMapper::timeTable2TimeTableDto).collect(Collectors.toList());
    }

    @Override
    public List<BankBranchDto> getListBankBranchDtoByAddressId(Long addressId) {
        log.debug("get bank branches by addressId: {}", addressId);
        return bankBranchRepository.getBankBranchByAddress_IdAndDeletedIsFalse(addressId)
                .stream().map(bankBranchMapper::bankBranch2BankBranchDto).collect(Collectors.toList());
    }
}
