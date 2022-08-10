package com.andersen.banking.service.transfer.meeting_impl.generators;

import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;

import java.util.List;
import java.util.Random;

public class TransferServiceTestEntitiesGenerator {

    public static final Long ID = 1L;
    public static final List<String> transferStatusNames = List.of("SUCCESS", "ERROR");
    public static final List<String> transferStatusDescriptions = List.of("Transaction completed successfully.", "The transaction failed.");

    public static TransferStatus generateTransferStatus () {
        TransferStatus transferStatus = new TransferStatus();
        Random random = new Random();

        transferStatus.setName(transferStatusNames.get(random.nextInt(2)));
        transferStatus.setDescription(transferStatusDescriptions.get(random.nextInt(2)));

        return transferStatus;
    }

    public static TransferStatusResponseDto generateTransferStatusResponseDto (TransferStatus transferStatus) {
        TransferStatusResponseDto transferStatusResponseDto = new TransferStatusResponseDto();

        transferStatusResponseDto.setName(transferStatus.getName());
        transferStatusResponseDto.setDescription(transferStatus.getDescription());

        return transferStatusResponseDto;
    }

    public static Long setId() {
        Long id = ID;
        return id;
    }
}
