package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.*;

import java.util.List;

public interface InformationService {

    List<CountryDto> getListCountryDto();

    List<CityDto> getListCityDtoByCountryId(Long countryId);

    List<StreetDto> getListStreetDtoByCityId(Long cityId);

    List<AddressDto> getListAddressDtoByStreetId(Long streetId);

    List<TimeTableDto> getListTimeTableDtoByAddressId(Long addressId);

    List<BankBranchDto> getListBankBranchDtoByAddressId(Long addressId);

}
