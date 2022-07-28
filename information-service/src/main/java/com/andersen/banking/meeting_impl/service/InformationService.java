package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.*;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface InformationService {

    /**
     * Find all CountryDto.
     *
     * @return list of CountryDto
     */
    List<CountryDto> getListCountryDto();


    /**
     * Find all CityDto.
     * @param countryId - country id.
     * @return list of CityDto by Country Id
     */
    List<CityDto> getListCityDtoByCountryId(Long countryId, Pageable pageable);

    /**
     * Find all CityDto.
     * @param cityId - city id.
     * @return list of CityDto by Country Id
     */
    List<StreetDto> getListStreetDtoByCityId(Long cityId);


    List<TimeTableDto> getListTimeTableDtoByBranchId(Long addressId);

    List<BankBranchDto> getListBankBranchDtoByCityId(Long addressId);

}
