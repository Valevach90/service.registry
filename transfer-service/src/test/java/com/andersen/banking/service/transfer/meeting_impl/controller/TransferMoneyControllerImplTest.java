package com.andersen.banking.service.transfer.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.TransferMoneyController;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_impl.controller.TransferMoneyControllerImpl;
import com.andersen.banking.meeting_impl.mapper.TransferStatusMapper;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;

@SpringBootTest(classes = TransferMoneyControllerImpl.class)
class TransferMoneyControllerImplTest {
    private static final Long ID = 23L;

    private TransferStatusResponseDto transferStatusResponseDto;
    private TransferStatus transferStatus;

    @Autowired
    TransferMoneyController transferMoneyController;
    @MockBean
    TransferMoneyService transferMoneyService;
    @MockBean
    TransferStatusMapper transferStatusMapper;

    @Test
    void whenFindTransferStatusById_andOk() {
        transferStatus = new TransferStatus();
        transferStatusResponseDto = new TransferStatusResponseDto();

        Mockito
                .when(transferMoneyService.getTransferStatus(ID))
                .thenReturn(transferStatusResponseDto);
        Mockito
                .when(transferStatusMapper.transferStatus2TransferStatusResponseDto(transferStatus))
                .thenReturn(transferStatusResponseDto);

        var result = transferMoneyController.findTransferStatusById(ID);
        
        Assertions.assertEquals(transferStatusResponseDto, result);
    }
}
