package com.andersen.banking.meeting_api.controller;


import com.andersen.banking.meeting_api.dto.AddressDto;
import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_api.dto.CountryDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Information controller", description = "work with information")
@RequestMapping(value = "/api/v1/information")
public interface InformationController {

    @Operation(summary = "Get all countries",
            description = "get list with all countries")
    @GetMapping("/country")
    List<CountryDto> getAllCountries();

    @Operation(summary = "Get all cities by countryId",
            description = "get list with all cities in a selected country by countryId")
    @GetMapping("/country/{id}/city")
    List<CityDto> getAllCitiesByCountryId(@Parameter(description = "country id", required = true)
                                          @PathVariable(value = "id") Long countryId
    );

    @Operation(summary = "Get all addresses by city id",
            description = "get list with all addresses in a selected city by cityId")
    @GetMapping("/city/{id}/address")
    List<AddressDto> getAllAddressesByCityId(@Parameter(description = "city id", required = true)
                                             @PathVariable(value = "id") Long cityId);


    @Operation(summary = "Get all timetables by address id",
            description = "get list with all timetables in a selected address by address id")
    @GetMapping("/address/{id}/timetable")
    List<TimeTableDto> getAllTimeTablesByAddressId(@Parameter(description = "address id", required = true)
                                                   @PathVariable(value = "id") Long addressId);


}
