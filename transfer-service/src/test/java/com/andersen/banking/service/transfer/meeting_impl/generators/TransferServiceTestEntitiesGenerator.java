package com.andersen.banking.service.transfer.meeting_impl.generators;

import com.andersen.banking.meeting_api.dto.message.TransferKafkaDeposit;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;

import java.util.ArrayList;
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

    public static List<TransferKafkaDeposit> generateListOfTransferKafkaDeposit() {
        List<TransferKafkaDeposit> list = new ArrayList<>();

        list.add(generateTransferKafkaDeposit(11L));
        list.add(generateTransferKafkaDeposit(2L));
        list.add(generateTransferKafkaDeposit(3L));
        list.add(generateTransferKafkaDeposit(10L));
        list.add(generateTransferKafkaDeposit(25L));

        return list;
    }

    public static TransferKafkaDeposit generateTransferKafkaDeposit(Long userId) {
        TransferKafkaDeposit transfer = new TransferKafkaDeposit();

        transfer.setUserId(userId);
        transfer.setAmount(1000L);
        transfer.setInterestRate(55L);
        transfer.setAccrued(550L);
        transfer.setCurrency("EUR");

        return transfer;
    }
}
