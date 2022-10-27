package com.andersen.banking.meeting_api.controller;

import static com.andersen.banking.meeting_impl.security.SecurityUtil.ADMIN;
import static com.andersen.banking.meeting_impl.security.SecurityUtil.EMPLOYEE;

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Admin controller", description = "Create delete and update information")
@RequestMapping(value = "/api/v1/information/admin")
@RolesAllowed({ADMIN, EMPLOYEE})
@SecurityRequirement(name = "Bearer Authentication")
public interface AdminController {

    @Operation(summary = "Save list of countries",
            description = "save list of countries")
    @PostMapping("/country")
    List<CountryDto> createListCountries(@RequestBody List<String> countries);

    @Operation(summary = "Save list of cities",
            description = "save list of cities")
    @PostMapping("/city")
    List<CityDto> createListCities(@RequestBody List<CityCreateDto> cities);

    @Operation(summary = "Save list of street in one city",
            description = "save list of street in one city")
    @PostMapping("/street")
    List<StreetDto> createListStreetsInCity(@RequestBody StreetCreateDto streetCreateDto);

    @Operation(summary = "Save bank brunch",
            description = "save bank brunch")
    @PostMapping("/brunch")
    BankBranchResponseDto createBrunch(@RequestBody BankBranchCreateDto bankBranchCreateDto);

    @Operation(summary = "Save time table",
            description = "save time table")
    @PostMapping("/timetable")
    TimeTableDto createTimeTable(@RequestBody TimeTableCreateDto timeTableCreateDto);

    @Operation(summary = "Update country",
            description = "Update country")
    @PutMapping("/country")
    CountryDto updateCountry(@RequestBody CountryDto countryDto);

    @Operation(summary = "Update city",
            description = "Update city")
    @PutMapping("/city")
    CityDto updateCity(@RequestBody CityDto city);

    @Operation(summary = "Update street",
            description = "Update street")
    @PutMapping("/street")
    StreetDto updateStreet(@RequestBody StreetDto streetDto);

    @Operation(summary = "Update bank brunch",
            description = "Update bank brunch")
    @PutMapping("/brunch")
    BankBranchResponseDto updateBankBrunch(@RequestBody BankBranchDto bankBranchDto);

    @Operation(summary = "Update time table",
            description = "Update time table")
    @PutMapping("/timetable")
    TimeTableDto updateTimeTable(@RequestBody TimeTableDto timeTableDto);

    @Operation(summary = "Delete country",
            description = "Delete country")
    @DeleteMapping("/country/{id}")
    void deleteCountry(@RequestParam("id") Long id);

    @Operation(summary = "Delete city",
            description = "Delete city")
    @DeleteMapping("/city/{id}")
    void deleteCity(@RequestParam Long id);

    @Operation(summary = "Delete street",
            description = "Delete street")
    @DeleteMapping("/street/{id}")
    void deleteStreet(@RequestParam("id") Long id);

    @Operation(summary = "Delete bank brunch",
            description = "Delete bank brunch")
    @DeleteMapping("/brunch/{id}")
    void deleteBankBrunch(@RequestParam("id") Long id);

    @Operation(summary = "Delete time table",
            description = "Delete time table")
    @DeleteMapping("/timetable/{id}")
    void deleteTimeTable(@RequestParam("id") Long id);
}
