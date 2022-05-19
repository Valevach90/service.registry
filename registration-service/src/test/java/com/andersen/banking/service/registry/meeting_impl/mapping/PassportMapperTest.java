package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = PassportMapperImpl.class)
class PassportMapperTest {

    private Passport passport;
    private PassportDto passportDto;

    @Autowired
    PassportMapper passportMapper;

    @BeforeEach
    void initData() {
        passport = new Passport();
        var address = new Address();
        var user = new User();
        var date = LocalDate.now();
        passport.setId(1L);
        passport.setAddress(address);
        passport.setUser(user);
        passport.setPassportCode("1");
        passport.setBirthday(date);
        passport.setBornPlace("1");
        passport.setDateIssue(date);
        passport.setDepartmentIssued("1");
        passport.setDivisionCode("1");
        passport.setFirstName("1");
        passport.setLastName("1");
        passport.setPatronymic("1");
        passport.setSerialNumber("1");
        passport.setTerminationDate(date);

        passportDto = new PassportDto();
        passportDto.setId(1L);
        passportDto.setPassportCode("1");
        passportDto.setBirthday(date);
        passportDto.setBornPlace("1");
        passportDto.setDateIssue(date);
        passportDto.setDepartmentIssued("1");
        passportDto.setDivisionCode("1");
        passportDto.setFirstName("1");
        passportDto.setLastName("1");
        passportDto.setPatronymic("1");
        passportDto.setSerialNumber("1");
        passportDto.setTerminationDate(date);
    }

    @Test
    void whenMapEntityToDto_andOk() {
        var result = passportMapper.toPassportDto(passport);
        checkForEquals(passport, result);
    }

    @Test
    void whenMapDtoToEntity_andOk() {
        var result = passportMapper.toPassport(passportDto);
        checkForEquals(passportDto, result);
        assertNull(result.getAddress());
        assertNull(result.getUser());
    }

    private void checkForEquals(Passport passport, PassportDto passportDto) {
        assertEquals(passport.getAddress().getId(), passportDto.getAddressId());
        assertEquals(passport.getUser().getId(), passportDto.getUserId());
        assertEquals(passport.getId(), passportDto.getId());
        assertEquals(passport.getPassportCode(), passportDto.getPassportCode());
        assertEquals(passport.getBirthday(), passportDto.getBirthday());
        assertEquals(passport.getBornPlace(), passportDto.getBornPlace());
        assertEquals(passport.getDateIssue(), passportDto.getDateIssue());
        assertEquals(passport.getDepartmentIssued(), passportDto.getDepartmentIssued());
        assertEquals(passport.getDivisionCode(), passportDto.getDivisionCode());
        assertEquals(passport.getFirstName(), passportDto.getFirstName());
        assertEquals(passport.getLastName(), passportDto.getLastName());
        assertEquals(passport.getPatronymic(), passportDto.getPatronymic());
        assertEquals(passport.getSerialNumber(), passportDto.getSerialNumber());
        assertEquals(passport.getTerminationDate(), passportDto.getTerminationDate());
    }

    private void checkForEquals(PassportDto passportDto, Passport passport) {
        assertEquals(passportDto.getId(), passport.getId());
        assertEquals(passportDto.getPassportCode(), passport.getPassportCode());
        assertEquals(passportDto.getBirthday(), passport.getBirthday());
        assertEquals(passportDto.getBornPlace(), passport.getBornPlace());
        assertEquals(passportDto.getDateIssue(), passport.getDateIssue());
        assertEquals(passportDto.getDepartmentIssued(), passport.getDepartmentIssued());
        assertEquals(passportDto.getDivisionCode(), passport.getDivisionCode());
        assertEquals(passportDto.getFirstName(), passport.getFirstName());
        assertEquals(passportDto.getLastName(), passport.getLastName());
        assertEquals(passportDto.getPatronymic(), passport.getPatronymic());
        assertEquals(passportDto.getSerialNumber(), passport.getSerialNumber());
        assertEquals(passportDto.getTerminationDate(), passport.getTerminationDate());
    }
}
