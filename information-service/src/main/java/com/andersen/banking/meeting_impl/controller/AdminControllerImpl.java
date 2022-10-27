package com.andersen.banking.meeting_impl.controller;


import com.andersen.banking.meeting_api.controller.AdminController;
import com.andersen.banking.meeting_api.dto.CityCreateDto;
import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_api.dto.CountryDto;
import com.andersen.banking.meeting_api.dto.StreetCreateDto;
import com.andersen.banking.meeting_api.dto.StreetDto;
import com.andersen.banking.meeting_api.dto.TimeTableCreateDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto;
import com.andersen.banking.meeting_api.dto.bank_brunch.BankBranchCreateDto;
import com.andersen.banking.meeting_api.dto.bank_brunch.BankBranchDto;
import com.andersen.banking.meeting_api.dto.bank_brunch.BankBranchResponseDto;
import com.andersen.banking.meeting_impl.service.AdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {

    private final AdminService adminService;

    @Override
    public List<CountryDto> createListCountries(List<String> countries) {
        log.info("Create countries {}", countries);
        return adminService.createCountries(countries);
    }

    @Override
    public List<CityDto> createListCities(List<CityCreateDto> cities) {
        log.info("Create cities {}", cities);
        return adminService.createCities(cities);
    }

    @Override
    public List<StreetDto> createListStreetsInCity(StreetCreateDto streetCreateDto) {
        log.info("Create street in one city {}", streetCreateDto);
        return adminService.createStreets(streetCreateDto);
    }

    @Override
    public BankBranchResponseDto createBrunch(BankBranchCreateDto bankBranchCreateDto) {
        log.info("Create bank brunch {}", bankBranchCreateDto);
        return adminService.createBankBrunch(bankBranchCreateDto);
    }

    @Override
    public TimeTableDto createTimeTable(TimeTableCreateDto timeTableCreateDto) {
        log.info("Create time table {}", timeTableCreateDto);
        return adminService.createTimeTable(timeTableCreateDto);
    }

    @Override
    public CountryDto updateCountry(CountryDto countryDto) {
        log.info("Update country {}", countryDto);
        return adminService.updateCountry(countryDto);
    }

    @Override
    public CityDto updateCity(CityDto city) {
        log.info("Update city {}", city);
        return adminService.updateCity(city);
    }

    @Override
    public StreetDto updateStreet(StreetDto streetDto) {
        log.info("Update street {}", streetDto);
        return adminService.updateStreet(streetDto);
    }

    @Override
    public BankBranchResponseDto updateBankBrunch(BankBranchDto bankBranchDto) {
        log.info("Update bank brunch {}", bankBranchDto);
        return adminService.updateBankBrunch(bankBranchDto);
    }

    @Override
    public TimeTableDto updateTimeTable(TimeTableDto timeTableDto) {
        log.info("Update time table {}", timeTableDto);
        return adminService.updateTimeTable(timeTableDto);
    }

    @Override
    public void deleteCountry(Long id) {
        log.info("Delete country with id: {}", id);
        adminService.deleteCountry(id);
    }

    @Override
    public void deleteCity(Long id) {
        log.info("Delete city with id: {}", id);
        adminService.deleteCity(id);

    }

    @Override
    public void deleteStreet(Long id) {
        log.info("Delete street with id: {}", id);
        adminService.deleteStreet(id);
    }

    @Override
    public void deleteBankBrunch(Long id) {
        log.info("Delete bank brunch with id: {}", id);
        adminService.deleteBankBrunch(id);
    }

    @Override
    public void deleteTimeTable(Long id) {
        log.info("Delete time table with id: {}", id);
        adminService.deleteTimeTable(id);
    }
}
