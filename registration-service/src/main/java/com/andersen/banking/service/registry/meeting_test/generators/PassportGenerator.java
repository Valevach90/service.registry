package com.andersen.banking.service.registry.meeting_test.generators;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PassportGenerator {
    private static final AtomicLong counter = new AtomicLong(1);

    private Faker faker;

    public Passport generatePassport(User user, Address address) {
        var passport = new Passport();
        var now = LocalDate.now();
        passport.setUserId(user.getId());
        passport.setAddress(address);
        passport.setSerialNumber(String.valueOf(faker.number().numberBetween(10, 99)));
        passport.setPassportCode(String.valueOf(faker.number().numberBetween(1000000, 9999999)));
        passport.setPatronymic(StringUtils.substring(faker.elderScrolls().region(), 0, 29));
        passport.setTerminationDate(now.plusYears(faker.number().randomDigitNotZero()));
        passport.setLastName(StringUtils.substring(faker.elderScrolls().lastName(), 0, 29));
        passport.setFirstName(StringUtils.substring(faker.elderScrolls().firstName(), 0, 29));
        passport.setDepartmentIssued(StringUtils.substring(faker.address().fullAddress(), 0, 44));
        passport.setBirthday(now.minusYears(18L + faker.number().randomDigit()));
        passport.setBornPlace(StringUtils.substring(faker.address().fullAddress(), 0, 44));
        passport.setId(counter.getAndIncrement());
        passport.setDateIssue(now.minusYears(faker.number().numberBetween(0, 5)));

        return passport;
    }

    @PostConstruct
    private void init() {
        faker = new Faker();
    }
}
