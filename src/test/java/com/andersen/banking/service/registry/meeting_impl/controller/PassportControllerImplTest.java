package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.PassportController;
import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.mapping.PassportMapper;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import com.andersen.banking.service.registry.meeting_impl.service.PassportService;
import com.andersen.banking.service.registry.meeting_impl.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = PassportControllerImpl.class)
class PassportControllerImplTest {
    private static final Long ID = 23L;
    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "userId";

    private PassportDto passportDto;
    private Optional<Passport> passport;

    @Autowired
    PassportController passportController;
    @MockBean
    PassportService passportService;
    @MockBean
    PassportMapper passportMapper;
    @MockBean
    UserService userService;
    @MockBean
    AddressService addressService;


    @BeforeEach
    void initData() {
        passport = Optional.of(new Passport());
        passportDto = new PassportDto();
    }

    @Test
    void whenFindById_andOk() {
        Mockito
                .when(passportService.findById(ID))
                .thenReturn(passport);
        Mockito
                .when(passportMapper.toPassportDto(passport.get()))
                .thenReturn(passportDto);

        var result = passportController.findById(ID);

        assertEquals(passportDto, result);
    }

    @Test
    void whenFindById_andNotFoundById_shouldThrowException() {
        Mockito
                .when(passportService.findById(ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> passportController.findById(ID));

        Mockito
                .verify(passportMapper, Mockito.times(0))
                .toPassportDto(Mockito.any(Passport.class));
    }

    @Test
    void whenFindByUserId_andOk() {
        Mockito
                .when(passportService.findByUserId(ID))
                .thenReturn(passport);
        Mockito
                .when(passportMapper.toPassportDto(passport.get()))
                .thenReturn(passportDto);

        var result = passportController.findByUserId(ID);

        assertEquals(passportDto, result);
    }

    @Test
    void whenFindByUserId_andNotFoundById_shouldThrowException() {
        Mockito
                .when(passportService.findById(ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> passportController.findByUserId(ID));

        Mockito
                .verify(passportMapper, Mockito.times(0))
                .toPassportDto(Mockito.any(Passport.class));
    }

    @Test
    void whenFindByAddressId_andOk() {
        Mockito
                .when(passportService.findByAddressId(ID))
                .thenReturn(passport);
        Mockito
                .when(passportMapper.toPassportDto(passport.get()))
                .thenReturn(passportDto);

        var result = passportController.findByAddressId(ID);

        assertEquals(passportDto, result);
    }

    @Test
    void whenFindByAddressId_andNotFoundById_shouldThrowException() {
        Mockito
                .when(passportService.findById(ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> passportController.findByAddressId(ID));

        Mockito
                .verify(passportMapper, Mockito.times(0))
                .toPassportDto(Mockito.any(Passport.class));
    }

    @Test
    void whenFindAll_andOk() {
        List<Passport> passports = generatePassports();
        Pageable pageable = createPageable();
        Page<Passport> page = new PageImpl<>(passports, pageable, SIZE_PAGE);

        Mockito
                .when(passportService.findAll(pageable))
                .thenReturn(page);
        Mockito
                .when(passportMapper.toPassportDto(Mockito.any(Passport.class)))
                .thenAnswer(invocation -> {
                    Passport entity = invocation.getArgument(0);
                    if (passports.contains(entity)) {
                        return passportDto;
                    }
                    return new PassportDto();
                });

        var result = passportController.findAll(pageable);

        result.forEach(resultDto -> assertEquals(passportDto, resultDto));
    }

    @Test
    void whenUpdate_andOk() {
        Mockito
                .when(passportMapper.toPassport(passportDto))
                .thenReturn(passport.get());

        passportController.update(passportDto);

        Mockito
                .verify(passportService, Mockito.times(1))
                .update(passport.get());
    }

    @Test
    void whenUpdate_andNotFound_shouldThrowException() {
        Mockito
                .when(passportMapper.toPassport(passportDto))
                .thenReturn(passport.get());
        Mockito
                .doThrow(NotFoundException.class)
                .when(passportService)
                .update(passport.get());

        assertThrows(NotFoundException.class, () -> passportController.update(passportDto));

        Mockito
                .verify(passportService, Mockito.times(1))
                .update(Mockito.any(Passport.class));
    }

    @Test
    void whenDelete_andOk() {
        Mockito
                .when(passportService.findById(ID))
                .thenReturn(passport);

        passportController.deleteById(ID);

        Mockito
                .verify(passportService, Mockito.times(1))
                .deleteById(ID);
    }

    @Test
    void whenDelete_andNotFound_shouldThrowException() {
        Mockito
                .when(passportService.findById(ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> passportController.deleteById(ID));

        Mockito
                .verify(passportService, Mockito.times(0))
                .deleteById(ID);
    }

    @Test
    void whenCreate_andOk() {
        passportDto.setUserId(ID);
        passportDto.setAddressId(ID);
        var user = Optional.of(new User());
        var address = Optional.of(new Address());

        Mockito
                .when(passportMapper.toPassport(passportDto))
                .thenReturn(passport.get());
        Mockito
                .when(passportService.findByAddressId(ID))
                .thenReturn(Optional.empty());
        Mockito
                .when(passportService.findByUserId(ID))
                .thenReturn(Optional.empty());
        Mockito
                .when(addressService.findById(ID))
                .thenReturn(address);
        Mockito
                .when(userService.findById(ID))
                .thenReturn(user);
        Mockito
                .when(passportService.create(passport.get()))
                .thenReturn(passport.get());
        Mockito
                .when(passportMapper.toPassportDto(passport.get()))
                .thenReturn(passportDto);

        PassportDto result = passportController.create(passportDto);
        assertEquals(passportDto, result);
        assertEquals(user.get(), passport.get().getUser());
        assertEquals(address.get(), passport.get().getAddress());
    }

    @Test
    void whenCreate_andPassportWithAddressFound_shouldThrowException() {
        passportDto.setAddressId(ID);

        Mockito
                .when(passportService.findByAddressId(ID))
                .thenReturn(passport);

        assertThrows(NotFoundException.class, () -> passportController.create(passportDto));
    }

    @Test
    void whenCreate_andPassportWithUserFound_shouldThrowException() {
        passportDto.setAddressId(ID);

        Mockito
                .when(passportService.findByAddressId(ID))
                .thenReturn(Optional.empty());
        Mockito
                .when(passportService.findByUserId(ID))
                .thenReturn(passport);

        assertThrows(NotFoundException.class, () -> passportController.create(passportDto));
    }

    @Test
    void whenCreate_andAddressNotFound_shouldThrowException() {
        passportDto.setUserId(ID);
        passportDto.setAddressId(ID);

        Mockito
                .when(passportMapper.toPassport(passportDto))
                .thenReturn(passport.get());
        Mockito
                .when(passportService.findByAddressId(ID))
                .thenReturn(Optional.empty());
        Mockito
                .when(passportService.findByUserId(ID))
                .thenReturn(Optional.empty());
        Mockito
                .when(addressService.findById(ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> passportController.create(passportDto));
    }

    @Test
    void whenCreate_andUserNotFound_shouldThrowException() {
        passportDto.setUserId(ID);
        passportDto.setAddressId(ID);
        var address = Optional.of(new Address());

        Mockito
                .when(passportMapper.toPassport(passportDto))
                .thenReturn(passport.get());
        Mockito
                .when(passportService.findByAddressId(ID))
                .thenReturn(Optional.empty());
        Mockito
                .when(passportService.findByUserId(ID))
                .thenReturn(Optional.empty());
        Mockito
                .when(addressService.findById(ID))
                .thenReturn(address);
        Mockito
                .when(userService.findById(ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> passportController.create(passportDto));
    }

    private List<Passport> generatePassports() {
        return Stream
                .generate(Passport::new)
                .limit(23)
                .collect(Collectors.toList());
    }

    private Pageable createPageable() {
        Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
        return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
    }

}
