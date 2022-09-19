package com.andersen.banking.meeting_impl.mapping;

import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateTransfer;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateTransferDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andersen.banking.meeting_api.dto.TransferDto;
import com.andersen.banking.meeting_db.entities.Transfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferMapperTest {

    private final TransferMapper transferMapper = new TransferMapperImpl();

    private Transfer transfer;

    private TransferDto transferDto;


    @BeforeEach
    void initialize() {
        transfer = generateTransfer();
        transferDto = generateTransferDto(transfer);
    }

    @Test
    void toTransfer_whenOk_shouldReturnTransfer() {
        Transfer result = transferMapper.toTransfer(transferDto);

        transfer.setDeposit(null);
        assertEquals(transfer, result);
    }

    @Test
    void toTransferDto_whenOk_shouldReturnTransferDto() {
        TransferDto result = transferMapper.toTransferDto(transfer);

        transferDto.setDeposit(null);
        assertEquals(transferDto, result);
    }
}
