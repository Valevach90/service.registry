package com.andersen.banking.meeting_impl.service.impl;

import static java.util.stream.Collectors.toMap;

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
import com.andersen.banking.meeting_db.entities.BankBranch;
import com.andersen.banking.meeting_db.entities.City;
import com.andersen.banking.meeting_db.entities.Country;
import com.andersen.banking.meeting_db.entities.Street;
import com.andersen.banking.meeting_db.entities.TimeTable;
import com.andersen.banking.meeting_db.repositories.BankBranchRepository;
import com.andersen.banking.meeting_db.repositories.CityRepository;
import com.andersen.banking.meeting_db.repositories.CountryRepository;
import com.andersen.banking.meeting_db.repositories.StreetRepository;
import com.andersen.banking.meeting_db.repositories.TimeTableRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.BankBranchMapper;
import com.andersen.banking.meeting_impl.mapper.CityMapper;
import com.andersen.banking.meeting_impl.mapper.CountryMapper;
import com.andersen.banking.meeting_impl.mapper.StreetMapper;
import com.andersen.banking.meeting_impl.mapper.TimeTableMapper;
import com.andersen.banking.meeting_impl.service.AdminService;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CountryMapper countryMapper;
    private final CityMapper cityMapper;
    private final StreetMapper streetMapper;
    private final BankBranchMapper bankBranchMapper;
    private final TimeTableMapper timeTableMapper;

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final StreetRepository streetRepository;
    private final BankBranchRepository bankBranchRepository;
    private final TimeTableRepository timeTableRepository;

    @Override
    public List<CountryDto> createCountries(List<String> countries) {
        log.debug("Create countries");
        List<Country> existCountries = countryRepository.findAllByNameIn(countries);
        List<String> existCountry = existCountries.stream()
                .map(Country::getName)
                .toList();

        List<Country> saveCountriesToDB = countries.stream()
                .filter(c -> !existCountry.contains(c))
                .map(c -> Country.builder().name(c).build())
                .toList();

        List<Country> savedCountries = countryRepository.saveAll(saveCountriesToDB);

        return Stream.concat(
                        existCountries.stream(),
                        savedCountries.stream()
                ).map(countryMapper::country2CountryDto)
                .toList();
    }

    @Override
    public List<CityDto> createCities(List<CityCreateDto> cities) {
        log.debug("Create cities");
        List<String> namesCities = cities.stream().map(CityCreateDto::getName).toList();
        List<Long> countriesIds = cities.stream().map(CityCreateDto::getCountryId).distinct()
                .toList();
        List<City> existCities = cityRepository.findAllCitiesByNameAndIdCountry(
                namesCities, countriesIds).stream().toList();

        List<CityCreateDto> existCreateCities = existCities.stream()
                .map(cityMapper::cityToCreateCityDto)
                .toList();

        List<Long> listCountriesId = cities.stream()
                .filter(c -> !existCreateCities.contains(c))
                .map(CityCreateDto::getCountryId)
                .toList();

        Map<Long, Country> collectCountry = countryRepository.findAllByIdIn(listCountriesId)
                .stream()
                .collect(toMap(Country::getId, Function.identity()));

        List<City> saveCitiesToDB = cities.stream()
                .filter(c -> !existCreateCities.contains(c))
                .map(city -> {
                    Country country = collectCountry.get(city.getCountryId());
                    return City.builder()
                            .name(city.getName())
                            .country(country)
                            .build();
                }).toList();
        List<City> savedCities = cityRepository.saveAll(saveCitiesToDB);

        return Stream.concat(
                        existCities.stream(),
                        savedCities.stream()
                ).map(cityMapper::city2CityDto)
                .toList();
    }

    @Override
    public List<StreetDto> createStreets(StreetCreateDto streetCreateDto) {
        log.debug("Create street");
        City city = cityRepository.getById(streetCreateDto.getCityId());
        List<Street> existStreets = streetRepository.findAllByCity_IdAndNameIn(
                streetCreateDto.getCityId(), streetCreateDto.getNames());

        List<String> streetNames = existStreets.stream()
                .map(Street::getName)
                .toList();

        List<Street> saveStreet = streetCreateDto.getNames().stream()
                .filter(s -> !streetNames.contains(s))
                .map(s -> Street.builder()
                        .name(s)
                        .city(city)
                        .build()
                ).toList();

        List<Street> savedStreet = streetRepository.saveAll(saveStreet);

        return Stream.concat(
                        existStreets.stream(),
                        savedStreet.stream()
                ).map(streetMapper::street2StreetDto)
                .toList();
    }

    @Override
    public BankBranchResponseDto createBankBrunch(BankBranchCreateDto bankBranchCreateDto) {
        log.debug("Create bank brunch");
        City city = cityRepository.getById(bankBranchCreateDto.getCityId());
        List<Street> listStreets = streetRepository.findAllByCity_IdAndNameIn(
                city.getId(), List.of(
                        bankBranchCreateDto.getStreetName()));
        if (listStreets.isEmpty()) {
            throw new NotFoundException(Street.class, "name", bankBranchCreateDto.getStreetName());
        }
        Street street = listStreets.get(0);
        BankBranch bankBranch = bankBranchMapper.toBankBranch(bankBranchCreateDto);
        bankBranch.setStreet(street);
        bankBranch.setCity(city);
        BankBranch save = bankBranchRepository.save(bankBranch);
        return bankBranchMapper.bankBranch2BankBranchDto(save);
    }

    @Override
    public TimeTableDto createTimeTable(TimeTableCreateDto timeTableCreateDto) {
        log.debug("Create time table");
        TimeTable timeTable = timeTableMapper.toTimeTable(timeTableCreateDto);
        timeTable.setBankBranch(bankBranchRepository.getById(timeTableCreateDto.getBranchId()));
        TimeTable save = timeTableRepository.save(timeTable);
        return timeTableMapper.timeTable2TimeTableDto(save);
    }

    @Override
    public CountryDto updateCountry(CountryDto countryDto) {
        log.debug("Update country");
        Country existCountry = countryRepository.findById(countryDto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException(Country.class, "id",
                            String.valueOf(countryDto.getId()));
                });
        existCountry.setName(countryDto.getName());
        Country save = countryRepository.save(existCountry);
        log.debug("Update country to {}", save);
        return countryMapper.country2CountryDto(save);
    }

    @Override
    public CityDto updateCity(CityDto cityDto) {
        log.debug("Update city");
        City existCity = cityRepository.findById(cityDto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException(City.class, "id",
                            String.valueOf(cityDto.getId()));
                });
        existCity.setName(cityDto.getName());
        existCity.setCountry(countryRepository
                .findById(cityDto.getCountryId())
                .orElseThrow(() -> {
                    throw new NotFoundException(Country.class, "id",
                            String.valueOf(cityDto.getCountryId()));
                }));
        City save = cityRepository.save(existCity);
        log.debug("Update city to {}", save);
        return cityMapper.city2CityDto(save);
    }

    @Override
    public StreetDto updateStreet(StreetDto streetDto) {
        log.debug("Update street");
        Street existStreet = streetRepository.findById(streetDto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException(Street.class, "id",
                            String.valueOf(streetDto.getId()));
                });
        existStreet.setCity(cityRepository
                .findById(streetDto.getCityId())
                .orElseThrow(() -> {
                    throw new NotFoundException(City.class, "id",
                            String.valueOf(streetDto.getCityId()));
                }));
        Street save = streetRepository.save(existStreet);
        log.debug("Update street to {}", save);
        return streetMapper.street2StreetDto(save);
    }

    @Override
    public BankBranchResponseDto updateBankBrunch(BankBranchDto bankBranchDto) {
        log.debug("Update bank brunch");
        BankBranch existBankBrunch = bankBranchRepository.findById(bankBranchDto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException(BankBranch.class, "id",
                            String.valueOf(bankBranchDto.getId()));
                });
        BankBranch bankBranch = bankBranchMapper.toBankBranch(bankBranchDto);
        bankBranch.setCity(cityRepository.findById(bankBranchDto.getCityId())
                .orElseThrow(() -> {
                    throw new NotFoundException(City.class, "id",
                            String.valueOf(bankBranchDto.getCityId()));
                }));
        bankBranch.setStreet(streetRepository
                .findAllByCity_IdAndNameIn(bankBranchDto.getCityId(),
                        List.of(bankBranchDto.getStreetName())).stream()
                .findFirst().orElseThrow(() -> {
                    throw new NotFoundException(Street.class, "street name",
                            String.valueOf(bankBranchDto.getStreetName()));
                }));

        bankBranch.setCreatedBy(existBankBrunch.getCreatedBy());
        bankBranch.setCreatedDate(existBankBrunch.getCreatedDate());
        bankBranch.setLastModifiedDate(existBankBrunch.getLastModifiedDate());
        bankBranch.setLastModifiedBy(existBankBrunch.getLastModifiedBy());
        bankBranch.setDeleted(existBankBrunch.isDeleted());

        BankBranch save = bankBranchRepository.save(bankBranch);
        log.debug("Update bank brunch to {}", save);
        return bankBranchMapper.bankBranch2BankBranchDto(save);
    }

    @Override
    public TimeTableDto updateTimeTable(TimeTableDto timeTableDto) {
        log.debug("Update time table");
        TimeTable existTimeTable = timeTableRepository.findById(timeTableDto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException(TimeTable.class, "id",
                            String.valueOf(timeTableDto.getId()));
                });
        TimeTable timeTable = timeTableMapper.toTimeTable(timeTableDto);
        timeTable.setBankBranch(bankBranchRepository
                .findById(timeTableDto.getBranchId())
                .orElseThrow(() -> {
                    throw new NotFoundException(TimeTable.class, "id",
                            String.valueOf(timeTableDto.getBranchId()));
                }));

        timeTable.setCreatedBy(existTimeTable.getCreatedBy());
        timeTable.setCreatedDate(existTimeTable.getCreatedDate());
        timeTable.setLastModifiedDate(existTimeTable.getLastModifiedDate());
        timeTable.setLastModifiedBy(existTimeTable.getLastModifiedBy());
        timeTable.setDeleted(existTimeTable.isDeleted());

        TimeTable save = timeTableRepository.save(timeTable);

        log.debug("Update time table to {}", save);
        return timeTableMapper.timeTable2TimeTableDto(save);
    }

    @Override
    public void deleteCountry(Long id) {
        log.debug("Delete country with id: {}", id);
        countryRepository.deleteById(id);
    }

    @Override
    public void deleteCity(Long id) {
        log.debug("Delete city with id: {}", id);
        cityRepository.deleteById(id);
    }

    @Override
    public void deleteStreet(Long id) {
        log.debug("Delete street with id: {}", id);
        streetRepository.deleteById(id);
    }

    @Override
    public void deleteBankBrunch(Long id) {
        log.debug("Delete bank brunch with id: {}", id);
        bankBranchRepository.deleteById(id);
    }

    @Override
    public void deleteTimeTable(Long id) {
        log.debug("Delete time table with id: {}", id);
        timeTableRepository.deleteById(id);
    }
}
