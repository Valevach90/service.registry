package com.andersen.banking.deposit.service.deposit_impl.mapping;

import com.andersen.banking.deposit.service.deposit_api.dto.TransferDto;
import com.andersen.banking.deposit.service.deposit_db.entities.Transfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.andersen.banking.deposit.service.deposit_impl.generators.DepositServiceTestEntitiesGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TransferMapperImpl.class)
public class TransferMapperTest {

    private Transfer transfer;
    private TransferDto transferDto;

    @Autowired
    TransferMapper transferMapper;

    @BeforeEach
    void initialize(){
        transfer = generateTransfer();
        transferDto = generateTransferDto(transfer);
    }

    @Test
    void toTransfer_whenOk_shouldReturnTransfer(){
        Transfer result = transferMapper.toTransfer(transferDto);

        assertEquals(transfer, result);
    }

    @Test
    void toTransferDto_whenOk_shouldReturnTransferDto(){
        TransferDto result = transferMapper.toTransferDto(transfer);

        assertEquals(transferDto, result);
    }
}
