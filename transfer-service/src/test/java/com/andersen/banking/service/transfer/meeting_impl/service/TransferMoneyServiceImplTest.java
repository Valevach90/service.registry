package com.andersen.banking.service.transfer.meeting_impl.service;

import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_db.repository.TransferStatusRepository;
import com.andersen.banking.meeting_impl.mapper.TransferStatusMapper;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import com.andersen.banking.meeting_impl.service.impl.TransferMoneyServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;


@SpringBootTest(classes = TransferMoneyServiceImpl.class)
public class TransferMoneyServiceImplTest {
    private static final Long ID = 23L;

    private final static Optional<TransferStatus> transferStatusOptional = Optional.of(new TransferStatus());

    @MockBean
    TransferStatusRepository transferStatusRepository;
    @Autowired
    TransferStatusMapper transferStatusMapper;
    @Autowired
    TransferMoneyService transferMoneyService;

    @Test
    void whenGetTransferStatus_andOk() {
        Mockito
                .when(transferStatusRepository.findById(ID))
                .thenReturn(transferStatusOptional);

        var result = Optional.of(transferStatusMapper
                .TransferStatusResponseDto2transferStatus(transferMoneyService.getTransferStatus(ID)));

        assertEquals(transferStatusOptional, result);
    }
}
