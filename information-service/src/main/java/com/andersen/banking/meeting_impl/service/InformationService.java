package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.AddressDto;
import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_api.dto.CountryDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto;

import java.util.List;

public interface InformationService {

    List<CountryDto> getListCountryDto();

    List<CityDto> getListCityDtoByCountryId(Long countryId);

    List<AddressDto> getListAddressDtoByCityId(Long cityId);

    List<TimeTableDto> getListTimeTableDtoByAddressId(Long addressId);

}
