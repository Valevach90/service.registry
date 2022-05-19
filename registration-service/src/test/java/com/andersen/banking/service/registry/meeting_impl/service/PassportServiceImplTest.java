package com.andersen.banking.service.registry.meeting_impl.service;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.PassportRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.FoundException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.impl.PassportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
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

@SpringBootTest(classes = PassportServiceImpl.class)
class PassportServiceImplTest {
    private static final Long ID = 23L;
    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "userId";

    private Optional<Passport> passportOptional;
    private Passport passportToUpdate;

    @SpyBean
    PassportService passportService;
    @MockBean
    PassportRepository passportRepository;
    @MockBean
    AddressService addressService;
    @MockBean
    UserService userService;

    @BeforeEach
    void initData() {
        passportOptional = Optional.of(new Passport());
        passportToUpdate = new Passport();
    }

    @Test
    void whenFindById_andOk() {
        Mockito
                .when(passportRepository.findById(ID))
                .thenReturn(passportOptional);

        var result = passportService.findById(ID);

        assertEquals(passportOptional, result);
    }

    @Test
    void whenFindByUserId_andOk() {
        Mockito
                .when(passportRepository.findByUserId(ID))
                .thenReturn(passportOptional);

        var result = passportService.findByUserId(ID);

        assertEquals(passportOptional, result);
    }

    @Test
    void whenFindByAddressId_andOk() {
        Mockito
                .when(passportRepository.findByAddressId(ID))
                .thenReturn(passportOptional);

        var result = passportService.findByAddressId(ID);

        assertEquals(passportOptional, result);
    }

    @Test
    void whenFindAll_andOk() {
        List<Passport> passports = generatePassports();
        Pageable pageable = createPageable();
        Page<Passport> page = new PageImpl<>(passports, pageable, SIZE_PAGE);

        Mockito
                .when(passportRepository.findAll(pageable))
                .thenReturn(page);

        Page<Passport> result = passportService.findAll(pageable);

        assertEquals(page, result);
    }

    @Test
    void whenUpdate_andOk() {
        User user = new User();
        Address address = new Address();
        passportOptional.get().setAddress(address);
        passportOptional.get().setUser(user);
        passportToUpdate.setId(ID);

        Mockito
                .when(passportRepository.findById(passportToUpdate.getId()))
                .thenReturn(passportOptional);

        passportService.update(passportToUpdate);

        assertEquals(passportOptional.get().getUser(), passportToUpdate.getUser());
        assertEquals(passportOptional.get().getAddress(), passportToUpdate.getAddress());

        Mockito
                .verify(passportRepository, Mockito.times(1))
                .save(passportToUpdate);
    }

    @Test
    void whenUpdate_andNotFound_shouldThrowException() {
        passportToUpdate.setId(ID);

        Mockito
                .when(passportRepository.findById(ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> passportService.update(passportToUpdate));
    }

    @Test
    void whenDeleteById_andOk() {
        Mockito
                .when(passportRepository.findById(ID))
                .thenReturn(passportOptional);

        passportService.deleteById(ID);

        Mockito
                .verify(passportRepository, Mockito.times(1))
                .deleteById(ID);
    }

    @Test
    void whenDeleteById_andNotFound_shouldThrowException() {
        assertThrows(NotFoundException.class, () -> passportService.deleteById(ID));

        Mockito
                .verify(passportRepository, Mockito.times(0))
                .deleteById(ID);
    }

    @Test
    void whenCreate_andOk() {
        var user = Optional.of(new User());
        var address = Optional.of(new Address());

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
                .when(passportRepository.save(passportOptional.get()))
                .thenReturn(passportOptional.get());

        var result = passportService.create(passportOptional.get(), ID, ID);

        assertEquals(passportOptional.get(), result);
        assertEquals(user.get(), passportOptional.get().getUser());
        assertEquals(address.get(), passportOptional.get().getAddress());
    }

    @Test
    void whenCreate_andPassportWithAddressFound_shouldThrowException() {
        var pas = passportOptional.get();

        Mockito
                .when(passportService.findByAddressId(ID))
                .thenReturn(passportOptional);

        assertThrows(FoundException.class, () -> passportService.create(pas, ID, ID));
    }

    @Test
    void whenCreate_andPassportWithUserFound_shouldThrowException() {
        var pas = passportOptional.get();

        Mockito
                .when(passportService.findByAddressId(ID))
                .thenReturn(Optional.empty());
        Mockito
                .when(passportService.findByUserId(ID))
                .thenReturn(passportOptional);

        assertThrows(FoundException.class, () -> passportService.create(pas, ID, ID));
    }

    @Test
    void whenCreate_andAddressNotFound_shouldThrowException() {
        var pas = passportOptional.get();

        Mockito
                .when(passportService.findByAddressId(ID))
                .thenReturn(Optional.empty());
        Mockito
                .when(passportService.findByUserId(ID))
                .thenReturn(Optional.empty());
        Mockito
                .when(addressService.findById(ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> passportService.create(pas, ID, ID));
    }

    @Test
    void whenCreate_andUserNotFound_shouldThrowException() {
        var address = Optional.of(new Address());
        var pas = passportOptional.get();

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

        assertThrows(NotFoundException.class, () -> passportService.create(pas, ID, ID));
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