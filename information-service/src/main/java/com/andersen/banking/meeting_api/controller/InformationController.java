package com.andersen.banking.meeting_api.controller;


import com.andersen.banking.meeting_api.dto.BankBranchDto;
import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_api.dto.CityDtoForSearch;
import com.andersen.banking.meeting_api.dto.CountryDto;
import com.andersen.banking.meeting_api.dto.StreetDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Information controller", description = "work with information")
@RequestMapping(value = "/api/v1/information")
public interface InformationController {

    @Operation(summary = "Get all countries",
            description = "get list with all countries")
    @GetMapping("/country")
    List<CountryDto> getAllCountries();

    @Operation(summary = "Get all cities by countryId",
            description = "get list with all cities in a selected country by countryId.")
    @GetMapping("/country/{id}/city")
    List<CityDto> getAllCitiesByCountryId(@Parameter(description = "country id", required = true)
                                          @PathVariable(value = "id") Long countryId,
                                          @ParameterObject Pageable pageable,
                                          @RequestParam(defaultValue = "false", required = false) boolean onlyWithBranches,
                                          @RequestParam(defaultValue = "false", required = false) boolean singlePage);

    @Operation(summary = "Get all cities by countryId and part of the name of the city",
            description = "get list with all cities in a selected country by countryId and part of city name")
    @PostMapping("/country/{id}/cities")
    List<CityDto> getAllCitiesByCountryIdAndByPartOfCityName(@Parameter(description = "country id", required = true)
    @PathVariable(value = "id") Long countryId,
            @ParameterObject @PageableDefault(sort = {"name"}) Pageable pageable,
            @RequestBody @Valid CityDtoForSearch cityName, BindingResult result);

    @Operation(summary = "Get all streets by cityId",
            description = "get list with all streets in a selected city by cityId")
    @GetMapping("/city/{id}/street")
    List<StreetDto> getAllStreetsByCityId(@Parameter(description = "city id", required = true)
                                          @PathVariable(value = "id") Long cityId);


    @Operation(summary = "Get all bank branches by cityId",
            description = "get list with all bank branches in a selected cityId")
    @GetMapping("/city/{id}/branch")
    List<BankBranchDto> getAllBankBranchesByCityId(@Parameter(description = "city id", required = true)
                                                   @PathVariable(value = "id") Long cityId);

    @Operation(summary = "Get all timetables by branch id",
            description = "get list with all timetables in a selected address by branch id")
    @GetMapping("/branch/{id}/timetable")
    List<TimeTableDto> getAllTimeTablesByBranchId(@Parameter(description = "branch id", required = true)
                                                  @PathVariable(value = "id") Long branchId);


}
