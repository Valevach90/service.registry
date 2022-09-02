package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import com.andersen.banking.service.registry.meeting_api.dto.PersonalDataDto;
import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.AddressRepository;
import com.andersen.banking.service.registry.meeting_db.repositories.PassportRepository;
import com.andersen.banking.service.registry.meeting_db.repositories.UserRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.mapping.AddressMapper;
import com.andersen.banking.service.registry.meeting_impl.mapping.PassportMapper;
import com.andersen.banking.service.registry.meeting_impl.mapping.UserMapper;
import com.andersen.banking.service.registry.meeting_impl.service.PersonalDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonalDataServiceImpl implements PersonalDataService {


    private final UserRepository userRepository;
    private final PassportRepository passportRepository;
    private final AddressRepository addressRepository;

    private final UserMapper userMapper;
    private final PassportMapper passportMapper;
    private final AddressMapper addressMapper;


    @Override
    @Transactional(readOnly = true)
    public PersonalDataDto getPersonalData(String email) {
        log.info("Find user personal data by user email: {}", email);

        UserDto userDto = userMapper.toUserDto(userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(User.class, email)));

        PassportDto passportDto = passportMapper.toPassportDto(
                passportRepository.findByUserId(userDto.getId())
                        .orElseThrow(() -> new NotFoundException(Passport.class, userDto.getId())));

        AddressDto addressDto = addressMapper.toAddressDto(
                addressRepository.findAddressByUserId(userDto.getId())
                        .orElseThrow(() -> new NotFoundException(Address.class, userDto.getId())));

        PersonalDataDto personalDataDto = new PersonalDataDto(userDto, passportDto, addressDto);

        log.info("Found user personal data: {}", personalDataDto);

        return personalDataDto;
    }

    @Override
    @Transactional
    public void updatePersonalData(PersonalDataDto updatedPersonalDataDto) {
        log.info("Updating user personal data: {}", updatedPersonalDataDto);

        User updatedUser = userMapper.toUser(updatedPersonalDataDto.getUser());
        Passport updatedPassport = passportMapper.toPassport(updatedPersonalDataDto.getPassport());
        Address updatedAddress = addressMapper.toAddress(updatedPersonalDataDto.getAddress());

        userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new NotFoundException(User.class, updatedUser.getId()));
        passportRepository.findById(updatedPassport.getId())
                .orElseThrow(() -> new NotFoundException(Passport.class, updatedPassport.getId()));
        addressRepository.findById(updatedAddress.getId())
                .orElseThrow(() -> new NotFoundException(Address.class, updatedAddress.getId()));

        userRepository.save(updatedUser);
        passportRepository.save(updatedPassport);
        addressRepository.save(updatedAddress);

        log.info("Updated user: {}, passport: {}, address: {}", updatedUser, updatedPassport,
                updatedAddress);
    }
}
