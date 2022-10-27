package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.*;
import com.andersen.banking.meeting_api.dto.bank_brunch.BankBranchDto;
import com.andersen.banking.meeting_api.dto.bank_brunch.BankBranchResponseDto;
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
     * Find cities with bank branches by country id.
     *
     * @param countryId - country id.
     * @return list of CityDto
     */

    List<CityDto> getListCityDtoWithBankBranchesByCountryId(Long countryId);


    /**
     * Find all CityDto.
     *
     * @param countryId - country id.
     * @return list of CityDto by country Id
     */
    List<CityDto> getListCityDtoByCountryId(Long countryId, Pageable pageable);

    /**
     * Find all CityDto.
     *
     * @param countryId - country id.
     * @param cityPartName - full name of the city or part of name
     * @return list of CityDto by country_Id and which contain part of the city name
     */
    List<CityDto> getListCityDtoByCountryIdAndPartOfCityName(Long countryId, CityDtoForSearch cityPartName, Pageable pageable);


    /**
     * Find all CityDto.
     *
     * @param cityId - city id.
     * @return list of CityDto by Country Id
     */
    List<StreetDto> getListStreetDtoByCityId(Long cityId);


    List<TimeTableDto> getListTimeTableDtoByBranchId(Long addressId);

    List<BankBranchResponseDto> getListBankBranchDtoByCityId(Long addressId);

    ExchangeRatesDto getGeneralExchangeRates();

    ExchangeRatesResponseDto getForSpecificCurrency(ExchangeRatesDto ratesDto, String currency);
}
