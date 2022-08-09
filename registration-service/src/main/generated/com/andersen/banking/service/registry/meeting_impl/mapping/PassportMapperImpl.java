package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-06T02:30:31+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class PassportMapperImpl implements PassportMapper {

    @Override
    public PassportDto toPassportDto(Passport passport) {
        if ( passport == null ) {
            return null;
        }

        PassportDto passportDto = new PassportDto();

        passportDto.setUserId( passportUserId( passport ) );
        passportDto.setAddressId( passportAddressId( passport ) );
        passportDto.setId( passport.getId() );
        passportDto.setSerialNumber( passport.getSerialNumber() );
        passportDto.setPassportCode( passport.getPassportCode() );
        passportDto.setBirthday( passport.getBirthday() );
        passportDto.setDateIssue( passport.getDateIssue() );
        passportDto.setTerminationDate( passport.getTerminationDate() );
        passportDto.setFirstName( passport.getFirstName() );
        passportDto.setLastName( passport.getLastName() );
        passportDto.setPatronymic( passport.getPatronymic() );
        passportDto.setDepartmentIssued( passport.getDepartmentIssued() );
        passportDto.setBornPlace( passport.getBornPlace() );

        return passportDto;
    }

    @Override
    public Passport toPassport(PassportDto passportDto) {
        if ( passportDto == null ) {
            return null;
        }

        Passport passport = new Passport();

        passport.setId( passportDto.getId() );
        passport.setSerialNumber( passportDto.getSerialNumber() );
        passport.setPassportCode( passportDto.getPassportCode() );
        passport.setBirthday( passportDto.getBirthday() );
        passport.setDateIssue( passportDto.getDateIssue() );
        passport.setTerminationDate( passportDto.getTerminationDate() );
        passport.setFirstName( passportDto.getFirstName() );
        passport.setLastName( passportDto.getLastName() );
        passport.setPatronymic( passportDto.getPatronymic() );
        passport.setDepartmentIssued( passportDto.getDepartmentIssued() );
        passport.setBornPlace( passportDto.getBornPlace() );

        return passport;
    }

    private Long passportUserId(Passport passport) {
        if ( passport == null ) {
            return null;
        }
        User user = passport.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long passportAddressId(Passport passport) {
        if ( passport == null ) {
            return null;
        }
        Address address = passport.getAddress();
        if ( address == null ) {
            return null;
        }
        Long id = address.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
