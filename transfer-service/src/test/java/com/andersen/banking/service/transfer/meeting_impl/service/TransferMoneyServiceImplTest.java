package com.andersen.banking.service.transfer.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_db.repository.TransferStatusRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.TransferStatusMapper;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import com.andersen.banking.meeting_impl.service.impl.TransferMoneyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest(classes = TransferMoneyServiceImpl.class)
public class TransferMoneyServiceImplTest {
    private static final Long ID = 23L;

    private TransferStatusResponseDto transferStatusResponseDto;
    @MockBean
    TransferStatusRepository transferStatusRepository;
    @MockBean
    TransferStatusMapper transferStatusMapper;
    @Autowired
    TransferMoneyService transferMoneyService;

    @Test
    void whenGetTransferStatus_andOk() {
        TransferStatus transferStatus = transferStatusRepository
                .findById(ID).orElseThrow(() -> new NotFoundException(TransferStatus.class, ID));

        Mockito
                .when(transferStatusMapper.transferStatus2TransferStatusResponseDto(transferStatus))
                .thenReturn(transferStatusResponseDto);

        var result = transferMoneyService.getTransferStatus(ID);

        Assertions.assertEquals(transferStatusResponseDto, result);
    }
}
