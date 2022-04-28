package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.PassportController;
import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.processing.PassportProcessingService;
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

    @Autowired
    PassportController passportController;
    @MockBean
    PassportProcessingService passportProcessingService;

    @BeforeEach
    void initData() {
        passportDto = new PassportDto();
    }

    @Test
    void whenFindById_andOk() {
        Mockito
                .when(passportProcessingService.findById(ID))
                .thenReturn(passportDto);

        PassportDto result = passportController.findById(ID);

        assertEquals(passportDto, result);
    }

    @Test
    void whenFindById_andNotFound_shouldThrowException() {
        Mockito
                .when(passportProcessingService.findById(ID))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> passportController.findById(ID));
    }

    @Test
    void whenFindByUserId_andOk() {
        Mockito
                .when(passportProcessingService.findByUserId(ID))
                .thenReturn(passportDto);

        PassportDto result = passportController.findByUserId(ID);

        assertEquals(passportDto, result);
    }

    @Test
    void whenFindByUserId_andNotFound_shouldThrowException() {
        Mockito
                .when(passportProcessingService.findByUserId(ID))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> passportController.findByUserId(ID));
    }

    @Test
    void whenFindByAddressId_andOk() {
        Mockito
                .when(passportProcessingService.findByAddressId(ID))
                .thenReturn(passportDto);

        PassportDto result = passportController.findByAddressId(ID);

        assertEquals(passportDto, result);
    }

    @Test
    void whenFindByAddressId_andNotFound_shouldThrowException() {
        Mockito
                .when(passportProcessingService.findByAddressId(ID))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> passportController.findByAddressId(ID));
    }

    @Test
    void whenFindAll_andOk() {
        List<PassportDto> passports = generatePassports();
        Pageable pageable = createPageable();
        Page<PassportDto> page = new PageImpl<>(passports, pageable, SIZE_PAGE);

        Mockito
                .when(passportProcessingService.findAll(pageable))
                .thenReturn(page);

        Page<PassportDto> result = passportController.findAll(pageable);

        assertEquals(page, result);
    }

    private List<PassportDto> generatePassports() {
        return Stream
                .generate(PassportDto::new)
                .limit(23)
                .collect(Collectors.toList());
    }

    private Pageable createPageable() {
        Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
        return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
    }
}
