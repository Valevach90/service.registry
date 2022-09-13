package com.andersen.banking.generators;

import com.andersen.banking.meeting_api.dto.CityDtoForSearch;
import com.andersen.banking.meeting_db.entities.City;
import com.andersen.banking.meeting_db.entities.Country;
import com.andersen.banking.meeting_impl.mapper.CityMapperImpl;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class GenerateDataForInformationService {

    private static CityMapperImpl cityMapper = new CityMapperImpl();

    public static Country country = generateCountry();

    public static Country generateCountry() {
        Country country = new Country();
        country.setId(1L);
        country.setName("Russia");

        return country;
    }

    public static City generateCity() {
        City cityOne = new City();
        cityOne.setCountry(country);
        cityOne.setName("Moscow");
        cityOne.setId(1L);

        return cityOne;
    }

    public static CityDtoForSearch generateCityDtoForSearch(String partOfName) {
        CityDtoForSearch cityDtoForSearch = new CityDtoForSearch();
        cityDtoForSearch.setName(partOfName);

        return cityDtoForSearch;
    }

    public static CityDtoForSearch generateWrongCityDtoForSearch() {
        CityDtoForSearch wrongCityDtoForSearch = new CityDtoForSearch();
        wrongCityDtoForSearch.setName("mo");

        return wrongCityDtoForSearch;
    }
}
