package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.bank_brunch.BankBranchCreateDto;
import com.andersen.banking.meeting_api.dto.bank_brunch.BankBranchDto;
import com.andersen.banking.meeting_api.dto.CityCreateDto;
import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_api.dto.CountryDto;
import com.andersen.banking.meeting_api.dto.StreetCreateDto;
import com.andersen.banking.meeting_api.dto.StreetDto;
import com.andersen.banking.meeting_api.dto.TimeTableCreateDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto;
import com.andersen.banking.meeting_api.dto.bank_brunch.BankBranchResponseDto;
import java.util.List;

public interface AdminService {
    List<CountryDto> createCountries(List<String> countries);

    List<CityDto> createCities(List<CityCreateDto> cities);

    List<StreetDto> createStreets(StreetCreateDto streetCreateDto);

    BankBranchResponseDto createBankBrunch(BankBranchCreateDto bankBranchCreateDto);

    TimeTableDto createTimeTable(TimeTableCreateDto timeTableCreateDto);

    CountryDto updateCountry(CountryDto countryDto);

    CityDto updateCity(CityDto cityDto);

    StreetDto updateStreet(StreetDto streetDto);

    BankBranchResponseDto updateBankBrunch(BankBranchDto bankBranchDto);

    TimeTableDto updateTimeTable(TimeTableDto timeTableDto);

    void deleteCountry(Long id);

    void deleteCity(Long id);

    void deleteStreet(Long id);

    void deleteBankBrunch(Long id);

    void deleteTimeTable(Long id);
}
