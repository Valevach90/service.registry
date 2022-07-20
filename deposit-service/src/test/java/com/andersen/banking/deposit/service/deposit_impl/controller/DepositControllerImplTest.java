package com.andersen.banking.deposit.service.deposit_impl.controller;

import com.andersen.banking.deposit.service.deposit_api.controller.DepositController;
import com.andersen.banking.deposit.service.deposit_api.dto.DepositDto;
import com.andersen.banking.deposit.service.deposit_db.entities.Deposit;
import com.andersen.banking.deposit.service.deposit_impl.exceptions.MapperException;
import com.andersen.banking.deposit.service.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit.service.deposit_impl.mapping.DepositMapper;
import com.andersen.banking.deposit.service.deposit_impl.service.DepositService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static com.andersen.banking.deposit.service.deposit_impl.generators.DepositServiceTestEntitiesGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = DepositControllerImpl.class)
public class DepositControllerImplTest {

    private Deposit deposit;
    private Long id;
    private Optional<Deposit> depositOptional;
    private DepositDto depositDto;


    @Autowired
    DepositController depositController;
    @MockBean
    DepositService depositService;
    @MockBean
    DepositMapper depositMapper;

    @BeforeEach
    void initialize(){
        deposit = generateDeposit();
        id = deposit.getId();
        depositOptional = Optional.of(deposit);
        depositDto = generateDepositDto(deposit);
    }

    @Test
    void create_whenOk_shouldReturnSavedDepositDto(){
        Mockito
                .when(depositMapper.toDeposit(depositDto))
                .thenReturn(deposit);
        Mockito
                .when(depositService.create(deposit))
                .thenReturn(deposit);
        Mockito
                .when(depositMapper.toDepositDto(deposit))
                .thenReturn(depositDto);

        DepositDto result = depositController.create(depositDto);

        assertEquals(depositDto, result);
    }

    @Test
    void create_whenDtoIsIncorrect_shouldThrowMapperException(){
        Mockito
                .when(depositMapper.toDeposit(depositDto))
                .thenThrow(new MapperException());

        assertThrows(MapperException.class, () -> depositController.create(depositDto));
    }

    @Test
    void findById_whenOk_shouldReturnFoundDepositDto(){
        Mockito
                .when(depositService.findById(id))
                .thenReturn(depositOptional);
        Mockito
                .when(depositMapper.toDepositDto(depositOptional.get()))
                .thenReturn(depositDto);

        DepositDto result = depositController.findById(id);

        assertEquals(depositDto, result);
    }

    @Test
    void findById_whenNotFound_shouldThrowNotFoundException(){
        Mockito
                .when(depositService.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> depositController.findById(id));
        Mockito
                .verify(depositMapper, Mockito.never())
                .toDepositDto(Mockito.any(Deposit.class));
    }

    @Test
    void findAll_whenOk_shouldReturnPageOfDepositDto(){
        Pageable pageable = createPageable();
        Page<Deposit> pageOfDeposits = generatePageOfDeposits(pageable);
        Page<DepositDto> pageOfDepositsDto = generatePageOfDepositsDto(pageOfDeposits);

        Mockito
                .when(depositService.findAll(pageable))
                .thenReturn(pageOfDeposits);

        Mockito
                .when(depositMapper.toDepositDto(Mockito.any(Deposit.class)))
                .thenReturn(new DepositDto());

        Page<DepositDto> result = depositController.findAll(pageable);

        assertEquals(pageOfDepositsDto, result);
    }

    @Test
    void update_whenOk(){
        Mockito
                .when(depositMapper.toDeposit(depositDto))
                .thenReturn(deposit);

        depositController.update(depositDto);

        Mockito
                .verify(depositService, Mockito.times(1))
                .update(deposit);
    }

    @Test
    void update_whenNotFound_shouldThrowNotFoundException(){
        Mockito
                .when(depositMapper.toDeposit(depositDto))
                .thenReturn(deposit);
        Mockito
                .doThrow(NotFoundException.class)
                .when(depositService)
                .update(deposit);

        assertThrows(NotFoundException.class, () -> depositController.update(depositDto));
    }

    @Test
    void delete_whenOk(){
        depositController.deleteById(id);

        Mockito
                .verify(depositService, Mockito.times(1))
                .deleteById(id);
    }

    @Test
    void delete_whenNotFound_shouldThrowNotFoundException(){
        Mockito
                .doThrow(NotFoundException.class)
                .when(depositService)
                .deleteById(id);

        assertThrows(NotFoundException.class, () -> depositController.deleteById(id));
    }
}
