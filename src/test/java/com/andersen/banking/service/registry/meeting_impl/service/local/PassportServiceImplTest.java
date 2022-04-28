package com.andersen.banking.service.registry.meeting_impl.service.local;

import com.andersen.banking.service.registry.meeting_db.entities.PassportEntity;
import com.andersen.banking.service.registry.meeting_db.repositories.PassportRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.local.impl.PassportServiceImpl;
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

@SpringBootTest(classes = PassportServiceImpl.class)
class PassportServiceImplTest {
    private static final Long ID = 23L;
    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "userId";

    private PassportEntity passportEntity;

    @Autowired
    PassportService passportService;
    @MockBean
    PassportRepository passportRepository;

    @BeforeEach
    void initData() {
        passportEntity = new PassportEntity();
    }

    @Test
    void whenFindById_andOk() {
        Mockito
                .when(passportRepository.findById(ID))
                .thenReturn(Optional.of(passportEntity));

        PassportEntity result = passportService.findById(ID);

        assertEquals(passportEntity, result);
    }

    @Test
    void whenFindById_andNotFound_shouldThrowException() {
        Mockito
                .when(passportRepository.findById(ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> passportService.findById(ID));
    }

    @Test
    void whenFindByUserId_andOk() {
        Mockito
                .when(passportRepository.findByUserId(ID))
                .thenReturn(Optional.of(passportEntity));

        PassportEntity result = passportService.findByUserId(ID);

        assertEquals(passportEntity, result);
    }

    @Test
    void whenFindByUserId_andNotFound_shouldThrowException() {
        Mockito
                .when(passportRepository.findByUserId(ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> passportService.findById(ID));
    }

    @Test
    void whenFindByAddressId_andOk() {
        Mockito
                .when(passportRepository.findByAddressId(ID))
                .thenReturn(Optional.of(passportEntity));

        PassportEntity result = passportService.findByAddressId(ID);

        assertEquals(passportEntity, result);
    }

    @Test
    void whenFindByAddressId_andNotFound_shouldThrowException() {
        Mockito
                .when(passportRepository.findByAddressId(ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> passportService.findByAddressId(ID));
    }

    @Test
    void whenFindAll_andOk() {
        List<PassportEntity> passports = generatePassports();
        Pageable pageable = createPageable();
        Page<PassportEntity> page = new PageImpl<>(passports, pageable, SIZE_PAGE);

        Mockito
                .when(passportRepository.findAll(pageable))
                .thenReturn(page);

        Page<PassportEntity> result = passportService.findAll(pageable);

        assertEquals(page, result);
    }

    private List<PassportEntity> generatePassports() {
        return Stream
                .generate(PassportEntity::new)
                .limit(23)
                .collect(Collectors.toList());
    }

    private Pageable createPageable() {
        Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
        return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
    }
}