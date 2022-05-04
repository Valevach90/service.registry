package com.andersen.banking.service.registry.meeting_test.generators;

import com.andersen.banking.service.registry.meeting_api.dto.AddressModifyDto;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
public class AddressModifyDtoGenerator {
    private Faker faker;
    private Random random;

    public AddressModifyDto generateAddressModifyDto() {
        var addressModifyDto = new AddressModifyDto();

        addressModifyDto.setZipCode(random.nextInt(999_999));
        addressModifyDto.setCountry(faker.address().country());
        addressModifyDto.setRegion(faker.hobbit().location());
        addressModifyDto.setLocation(faker.hobbit().location());
        addressModifyDto.setCity(faker.address().city());
        addressModifyDto.setStreet(faker.address().streetName());
        addressModifyDto.setHouse(faker.address().streetAddressNumber());
        addressModifyDto.setBuilding(faker.address().buildingNumber());
        addressModifyDto.setFlat(faker.address().streetAddressNumber());

        return addressModifyDto;
    }

    @PostConstruct
    private void init() {
        faker = new Faker();
        random = new Random();
    }
}
