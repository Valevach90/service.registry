package com.andersen.banking.service.registry.meeting_impl.service;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.PassportRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.impl.PassportServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = PassportServiceImpl.class)
class PassportServiceImplTest {
    private static final Long ID = 23L;
    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "userId";

    private Optional<Passport> passport;
    private Passport passportToUpdate;

    @Autowired
    PassportService passportService;
    @MockBean
    PassportRepository passportRepository;

    @BeforeEach
    void initData() {
        passport = Optional.of(new Passport());
        passportToUpdate = new Passport();
    }

    @Test
    void whenFindById_andOk() {
        Mockito
                .when(passportRepository.findById(ID))
                .thenReturn(passport);

        var result = passportService.findById(ID);

        assertEquals(passport, result);
    }

    @Test
    void whenFindByUserId_andOk() {
        Mockito
                .when(passportRepository.findByUserId(ID))
                .thenReturn(passport);

        var result = passportService.findByUserId(ID);

        assertEquals(passport, result);
    }

    @Test
    void whenFindByAddressId_andOk() {
        Mockito
                .when(passportRepository.findByAddressId(ID))
                .thenReturn(passport);

        var result = passportService.findByAddressId(ID);

        assertEquals(passport, result);
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
        passport.get().setAddress(address);
        passport.get().setUser(user);
        passportToUpdate.setId(ID);

        Mockito
                .when(passportRepository.findById(passportToUpdate.getId()))
                .thenReturn(passport);

        passportService.update(passportToUpdate);

        assertEquals(passport.get().getUser(), passportToUpdate.getUser());
        assertEquals(passport.get().getAddress(), passportToUpdate.getAddress());

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
        passportService.deleteById(ID);

        Mockito
                .verify(passportRepository, Mockito.times(1))
                .deleteById(ID);
    }

    @Test
    void whenCreate_andOk() {
        passport.get().setId(ID);

        passportService.create(passport.get());

        assertNull(passport.get().getId());

        Mockito
                .verify(passportRepository, Mockito.times(1))
                .save(passport.get());
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