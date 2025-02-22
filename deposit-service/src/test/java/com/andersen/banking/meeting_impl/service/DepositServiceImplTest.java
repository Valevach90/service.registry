package com.andersen.banking.meeting_impl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_db.repositories.DepositRepository;
import com.andersen.banking.meeting_db.repositories.TransferRepository;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator;
import com.andersen.banking.meeting_impl.mapping.DepositMapper;
import com.andersen.banking.meeting_impl.mapping.TransferMapper;
import com.andersen.banking.meeting_impl.service.impl.DepositServiceImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SpringBootTest(classes = DepositServiceImpl.class)
public class DepositServiceImplTest {

    private Deposit deposit;
    private UUID id;
    private Optional<Deposit> depositOptional;

    @SpyBean
    DepositService depositService;

    @MockBean
    DepositRepository depositRepository;

    @MockBean
    DepositMapper depositMapper;
    @MockBean
    TransferRepository transferRepository;
    @MockBean
    TransferMapper transferMapper;
    @MockBean
    TransferMoneyServiceKafkaResponseProducer transferMoneyServiceKafkaResponseProducer;

    @BeforeEach
    void initialize() {
        deposit = DepositServiceTestEntitiesGenerator.generateDeposit();
        id = deposit.getId();
        depositOptional = Optional.of(deposit);
    }

    @Test
    void create_whenOk_shouldReturnSavedDeposit() {
        Mockito
            .when(depositRepository.save(deposit))
            .thenReturn(deposit);

        Deposit result = depositService.create(deposit);

        assertEquals(deposit, result);
    }

    @Test
    void findById_whenOk_shouldReturnFoundDeposit() {
        Mockito
            .when(depositRepository.findById(id))
            .thenReturn(depositOptional);

        Optional<Deposit> result = depositService.findById(id);

        assertEquals(depositOptional, result);
    }

    @Test
    void findById_whenNotFound_shouldReturnEmptyOptional() {
        Optional<Deposit> empty = Optional.empty();
        Optional<Deposit> result = depositService.findById(id);

        assertEquals(empty, result);
    }

    @Test
    void findAll_whenOk_shouldReturnPageOfDeposit() {
        Pageable pageable = DepositServiceTestEntitiesGenerator.createPageable();
        Page<Deposit> pageOfDeposits = DepositServiceTestEntitiesGenerator.generatePageOfDeposits(
            pageable);

        Mockito
            .when(depositRepository.findAll(pageable))
            .thenReturn(pageOfDeposits);

        Page<Deposit> result = depositService.findAll(pageable);

        assertEquals(pageOfDeposits, result);
    }

    @Test
    void update_whenOk() {
        Mockito
            .when(depositRepository.findById(id))
            .thenReturn(depositOptional);

        depositService.update(deposit);

        Mockito
            .verify(depositRepository, Mockito.times(1))
            .save(deposit);
    }

    @Test
    void update_whenNotFound_shouldThrowNotFoundException() {

        Mockito
            .when(depositRepository.findById(id))
            .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> depositService.update(deposit));
        Mockito
            .verify(depositRepository, Mockito.never())
            .save(deposit);
    }

    @Test
    void delete_whenOk() {
        Mockito
            .when(depositRepository.findById(id))
            .thenReturn(depositOptional);

        depositService.deleteById(id);

        Mockito
            .verify(depositRepository, Mockito.times(1))
            .deleteById(id);
    }

    @Test
    void delete_whenNotFound_shouldThrowNotFoundException() {
        Mockito
            .when(depositRepository.findById(id))
            .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> depositService.deleteById(id));
        Mockito
            .verify(depositRepository, Mockito.never())
            .deleteById(id);
    }
}
