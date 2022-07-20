package com.andersen.banking.deposit.service.deposit_impl.service;

import com.andersen.banking.deposit.service.deposit_db.entities.Transfer;
import com.andersen.banking.deposit.service.deposit_db.repositories.TransferRepository;
import com.andersen.banking.deposit.service.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit.service.deposit_impl.service.impl.TransferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static com.andersen.banking.deposit.service.deposit_impl.generators.DepositServiceTestEntitiesGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = TransferServiceImpl.class)
public class TransferServiceImplTest {

    private Transfer transfer;
    private Long id;
    private Optional<Transfer> transferOptional;

    @SpyBean
    TransferService transferService;

    @MockBean
    TransferRepository transferRepository;

    @BeforeEach
    void initialize(){
        transfer = generateTransfer();
        id = transfer.getId();
        transferOptional = Optional.of(transfer);
    }

    @Test
    void create_whenOk_shouldReturnSavedTransfer(){
        Mockito
                .when(transferRepository.save(transfer))
                .thenReturn(transfer);

        Transfer result = transferService.create(transfer);

        assertEquals(transfer, result);
    }

    @Test
    void findById_whenOk_shouldReturnFoundTransfer(){
        Mockito
                .when(transferRepository.findById(id))
                .thenReturn(transferOptional);

        Optional<Transfer> result = transferService.findById(id);

        assertEquals(transferOptional, result);
    }

    @Test
    void findById_whenNotFound_shouldReturnEmptyOptional(){
        Optional<Transfer> empty = Optional.empty();
        Optional<Transfer> result = transferService.findById(id);

        assertEquals(empty, result);
    }

    @Test
    void findAll_whenOk_shouldReturnPageOfTransfers(){
        Pageable pageable = createPageable();
        Page<Transfer> pageOfTransfers = generatePageOfTransfers(pageable);

        Mockito
                .when(transferRepository.findAll(pageable))
                .thenReturn(pageOfTransfers);

        Page<Transfer> result = transferService.findAll(pageable);

        assertEquals(pageOfTransfers, result);
    }

    @Test
    void update_whenOk(){
        Mockito
                .when(transferRepository.findById(id))
                .thenReturn(transferOptional);

        transferService.update(transfer);

        Mockito
                .verify(transferRepository, Mockito.times(1))
                .save(transfer);
    }

    @Test
    void update_whenNotFound_shouldThrowNotFoundException(){

        Mockito
                .when(transferRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> transferService.update(transfer));
        Mockito
                .verify(transferRepository, Mockito.never())
                .save(transfer);
    }

    @Test
    void delete_whenOk(){
        Mockito
                .when(transferRepository.findById(id))
                .thenReturn(transferOptional);

        transferService.deleteById(id);

        Mockito
                .verify(transferRepository, Mockito.times(1))
                .deleteById(id);
    }

    @Test
    void delete_whenNotFound_shouldThrowNotFoundException(){
        Mockito
                .when(transferRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> transferService.deleteById(id));
        Mockito
                .verify(transferRepository, Mockito.never())
                .deleteById(id);
    }
}
