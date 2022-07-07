package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.AddressDto;
import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_api.dto.CountryDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto;
import com.andersen.banking.meeting_db.repositories.AddressRepository;
import com.andersen.banking.meeting_db.repositories.CityRepository;
import com.andersen.banking.meeting_db.repositories.CountryRepository;
import com.andersen.banking.meeting_db.repositories.TimeTableRepository;
import com.andersen.banking.meeting_impl.mapper.AddressMapper;
import com.andersen.banking.meeting_impl.mapper.CityMapper;
import com.andersen.banking.meeting_impl.mapper.CountryMapper;
import com.andersen.banking.meeting_impl.mapper.TimeTableMapper;
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

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;
    private final TimeTableRepository timeTableRepository;

    private final CountryMapper countryMapper;
    private final CityMapper cityMapper;
    private final AddressMapper addressMapper;
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
    public List<AddressDto> getListAddressDtoByCityId(Long cityId) {
        log.debug("get addresses by cityId : {}", cityId);
        return addressRepository.getAddressByCity_IdAndDeletedIsFalse(cityId)
                .stream().map(addressMapper::address2AddressDto).collect(Collectors.toList());
    }

    @Override
    public List<TimeTableDto> getListTimeTableDtoByAddressId(Long addressId) {
        log.debug("get timetables by addressId: {}", addressId);
        return timeTableRepository.getTimeTableByAddress_IdAndDeletedIsFalse(addressId)
                .stream().map(timeTableMapper::timeTable2TimeTableDto).collect(Collectors.toList());
    }
}
