package com.andersen.banking.service.registry.meeting_impl.service.processing;

import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import com.andersen.banking.service.registry.meeting_db.entities.PassportEntity;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.mapping.PassportMapper;
import com.andersen.banking.service.registry.meeting_impl.service.local.PassportService;
import com.andersen.banking.service.registry.meeting_impl.service.processing.impl.PassportProcessingServiceImpl;
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

@SpringBootTest(classes = PassportProcessingServiceImpl.class)
class PassportProcessingServiceImplTest {
    private static final Long ID = 23L;
    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "userId";

    private PassportEntity passportEntity;
    private PassportDto passportDto;

    @Autowired
    PassportProcessingService passportProcessingService;
    @MockBean
    PassportMapper passportMapper;
    @MockBean
    PassportService passportService;

    @BeforeEach
    void initData() {
        passportEntity = new PassportEntity();
        passportDto = new PassportDto();
    }

    @Test
    void whenFindById_andOk() {
        Mockito
                .when(passportService.findById(ID))
                .thenReturn(passportEntity);
        Mockito
                .when(passportMapper.toPassportDto(passportEntity))
                .thenReturn(passportDto);

        PassportDto result = passportProcessingService.findById(ID);

        assertEquals(passportDto, result);
    }

    @Test
    void whenFindById_andNotFoundById_shouldThrowException() {
        Mockito
                .when(passportService.findById(ID))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> passportProcessingService.findById(ID));

        Mockito
                .verify(passportMapper, Mockito.times(0))
                .toPassportDto(Mockito.any(PassportEntity.class));
    }

    @Test
    void whenFindByUserId_andOk() {
        Mockito
                .when(passportService.findByUserId(ID))
                .thenReturn(passportEntity);
        Mockito
                .when(passportMapper.toPassportDto(passportEntity))
                .thenReturn(passportDto);

        PassportDto result = passportProcessingService.findByUserId(ID);

        assertEquals(passportDto, result);
    }

    @Test
    void whenFindByUserId_andNotFoundById_shouldThrowException() {
        Mockito
                .when(passportService.findByUserId(ID))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> passportProcessingService.findByUserId(ID));

        Mockito
                .verify(passportMapper, Mockito.times(0))
                .toPassportDto(Mockito.any(PassportEntity.class));
    }

    @Test
    void whenFindByAddressId_andOk() {
        Mockito
                .when(passportService.findByAddressId(ID))
                .thenReturn(passportEntity);
        Mockito
                .when(passportMapper.toPassportDto(passportEntity))
                .thenReturn(passportDto);

        PassportDto result = passportProcessingService.findByAddressId(ID);

        assertEquals(passportDto, result);
    }

    @Test
    void whenFindByAddressId_andNotFoundById_shouldThrowException() {
        Mockito
                .when(passportService.findByAddressId(ID))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> passportProcessingService.findByAddressId(ID));

        Mockito
                .verify(passportMapper, Mockito.times(0))
                .toPassportDto(Mockito.any(PassportEntity.class));
    }

    @Test
    void whenFindAll_andOk() {

        List<PassportEntity> passports = generatePassports();
        Pageable pageable = createPageable();
        Page<PassportEntity> page = new PageImpl<>(passports, pageable, SIZE_PAGE);

        Mockito
                .when(passportService.findAll(pageable))
                .thenReturn(page);
        Mockito
                .when(passportMapper.toPassportDto(Mockito.any(PassportEntity.class)))
                .thenAnswer(invocation -> {
                    PassportEntity entity = invocation.getArgument(0);
                    if (passports.contains(entity)) {
                        return passportDto;
                    }
                    return new PassportDto();
                });

        Page<PassportDto> result = passportProcessingService.findAll(pageable);

        result.forEach(resultDto -> assertEquals(passportDto, resultDto));
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
