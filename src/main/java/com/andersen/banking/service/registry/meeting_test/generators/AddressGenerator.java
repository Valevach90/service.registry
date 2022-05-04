package com.andersen.banking.service.registry.meeting_test.generators;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Users;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
public class AddressGenerator {
    private Faker faker;
    private Random random;

    public Address generateAddress(Users user) {
        var address = new Address();
        address.setId(random.nextLong(1_000_000));
        address.setUser(user);
        address.setZipCode(random.nextInt(999_999));
        address.setCountry(faker.address().country());
        address.setRegion(faker.hobbit().location());
        address.setLocation(faker.hobbit().location());
        address.setCity(faker.address().city());
        address.setStreet(faker.address().streetName());
        address.setHouse(faker.address().streetAddressNumber());
        address.setBuilding(faker.address().buildingNumber());
        address.setFlat(faker.address().streetAddressNumber());

        return address;
    }

    @PostConstruct
    private void init() {
        faker = new Faker();
        random = new Random();
    }
}
