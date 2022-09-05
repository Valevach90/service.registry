package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.BankBranchDto;
import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_api.dto.CityDtoForSearch;
import com.andersen.banking.meeting_api.dto.CountryDto;
import com.andersen.banking.meeting_api.dto.StreetDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto;
import com.andersen.banking.meeting_db.repositories.BankBranchRepository;
import com.andersen.banking.meeting_db.repositories.CityRepository;
import com.andersen.banking.meeting_db.repositories.CountryRepository;
import com.andersen.banking.meeting_db.repositories.StreetRepository;
import com.andersen.banking.meeting_db.repositories.TimeTableRepository;
import com.andersen.banking.meeting_impl.mapper.BankBranchMapper;
import com.andersen.banking.meeting_impl.mapper.CityMapper;
import com.andersen.banking.meeting_impl.mapper.CountryMapper;
import com.andersen.banking.meeting_impl.mapper.StreetMapper;
import com.andersen.banking.meeting_impl.mapper.TimeTableMapper;
import com.andersen.banking.meeting_impl.service.InformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.andersen.banking.meeting_db.repositories.specifications.CitySpecification.containsName;
import static com.andersen.banking.meeting_db.repositories.specifications.CitySpecification.hasCountry;
import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {


    private final BankBranchRepository bankBranchRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    private final StreetRepository streetRepository;
    private final TimeTableRepository timeTableRepository;


    private final BankBranchMapper bankBranchMapper;

    private final CityMapper cityMapper;
    private final CountryMapper countryMapper;

    private final StreetMapper streetMapper;

    private final TimeTableMapper timeTableMapper;

    @Override
    public List<CountryDto> getListCountryDto() {
        log.debug("get countries");
        return countryRepository.getCountriesByDeletedIsFalse()
                .stream().map(countryMapper::country2CountryDto).collect(Collectors.toList());
    }

    @Override
    public List<CityDto> getListCityDtoWithBankBranchesByCountryId(Long countryId) {
        log.debug("get only cities with bank branches by countryId : {}", countryId);
        return cityRepository.getOnlyCitiesWithBankBranches(countryId)
                .stream().map(cityMapper::city2CityDto).collect(Collectors.toList());
    }

    @Override
    public List<CityDto> getListCityDtoByCountryId(Long countryId, Pageable pageable) {
        log.debug("get cities by countryId : {}", countryId);
        return cityRepository.getCitiesByCountry_IdAndDeletedIsFalse(countryId, pageable)
                .stream().map(cityMapper::city2CityDto).collect(Collectors.toList());
    }

    @Override
    public List<CityDto> getListCityDtoByCountryIdAndPartOfCityName(Long countryId, CityDtoForSearch cityPartName, Pageable pageable) {
        log.debug("get cities by countryId : {} , and contains part of name {}", countryId, cityPartName);
        return cityRepository.findAll(where(hasCountry(countryId)).and(containsName(cityPartName.getName())), pageable)
                    .stream().map(cityMapper::city2CityDto).collect(Collectors.toList());


    }

    @Override
    public List<StreetDto> getListStreetDtoByCityId(Long cityId) {
        log.debug("get streets by cityId : {}", cityId);
        return streetRepository.getTimeTableByCity_IdAndDeletedIsFalse(cityId)
                .stream().map(streetMapper::street2StreetDto).collect(Collectors.toList());
    }


    @Override
    public List<TimeTableDto> getListTimeTableDtoByBranchId(Long branchId) {
        log.debug("get timetables by addressId: {}", branchId);
        return timeTableRepository.getTimeTableByBankBranch_IdAndDeletedIsFalse(branchId)
                .stream().map(timeTableMapper::timeTable2TimeTableDto).collect(Collectors.toList());
    }

    @Override
    public List<BankBranchDto> getListBankBranchDtoByCityId(Long cityId) {
        log.debug("get bank branches by addressId: {}", cityId);
        return bankBranchRepository.getBankBranchByCity_IdAndDeletedIsFalse(cityId)
                .stream().map(bankBranchMapper::bankBranch2BankBranchDto).collect(Collectors.toList());
    }


}
