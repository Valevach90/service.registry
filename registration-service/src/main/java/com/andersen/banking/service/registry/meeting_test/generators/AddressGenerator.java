package com.andersen.banking.service.registry.meeting_test.generators;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.github.javafaker.Faker;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AddressGenerator {

    private static final AtomicLong counter = new AtomicLong(1L);
    private Faker faker;
    private Random random;

    public Address generateAddress(User user) {
        var address = new Address();
        address.setId(counter.getAndIncrement());
        address.setUserId(user.getId());
        address.setZipCode(random.nextInt(999_999));
        address.setCountry(countryWithLimitLenght(faker.address().country()));
        address.setRegion(faker.hobbit().location());
        address.setLocation(faker.hobbit().location());
        address.setCity(faker.address().city());
        address.setStreet(faker.address().streetName());
        address.setHouse(faker.address().streetAddressNumber());
        address.setBuilding(faker.address().buildingNumber());
        address.setFlat(faker.address().streetAddressNumber());

        return address;
    }

    private String countryWithLimitLenght(String address){

        while (address.length() > 30) {
            address = faker.address().country();
        }
        return address;
    }

    @PostConstruct
    private void init() {
        faker = new Faker();
        random = new Random();
    }
}
