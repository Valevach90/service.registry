package com.andersen.banking.service.transfer.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_impl.mapper.TransferStatusMapper;
import com.andersen.banking.meeting_impl.mapper.TransferStatusMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TransferStatusMapperImpl.class)
public class TransferStatusMapperTest {

    private TransferStatus transferStatus;
    private TransferStatusResponseDto transferStatusResponseDto;

    @Autowired
    TransferStatusMapper transferStatusMapper;

    @BeforeEach
    void initData() {
        transferStatus = new TransferStatus();
        transferStatus.setName("ERROR");
        transferStatus.setDescription("Transfer interrupted");

        transferStatusResponseDto = new TransferStatusResponseDto();
        transferStatusResponseDto.setName("ERROR");
        transferStatusResponseDto.setDescription("Transfer interrupted");
    }

    @Test
    void whenTransferStatus2TransferStatusResponseDto_andOk() {
        var result = transferStatusMapper.
                transferStatus2TransferStatusResponseDto(transferStatus);
        checkForEquals(transferStatus, result);
    }

    @Test
    void whenTransferStatusResponseDto2TransferStatus_andOk() {
        var result = transferStatusMapper
                .TransferStatusResponseDto2transferStatus(transferStatusResponseDto);
        checkForEquals(result, transferStatusResponseDto);
    }

    private void checkForEquals(TransferStatus transferStatus, TransferStatusResponseDto result) {
        assertEquals(transferStatus.getName(), transferStatusResponseDto.getName());
        assertEquals(transferStatus.getDescription(), transferStatusResponseDto.getDescription());
    }
}
