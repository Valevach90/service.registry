package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_impl.kafka.config.KafkaProperties;
import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferExecutor;
import com.andersen.banking.meeting_impl.service.TransferMoneyTopicSender;
import com.andersen.banking.meeting_impl.service.TransferService;
import com.andersen.banking.meeting_impl.util.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferExecutorInternalService implements TransferExecutor {

    private final static String CARD = "CARD";

    private final static String DEPOSIT = "DEPOSIT";

    private final TransferService transferService;

    private final KafkaProperties kafkaProperties;

    private final TransferMoneyTopicSender transferMoneyTopicSender;

    private final Converter<RequestKafkaTransferMessage, Transfer> converter;


    /**
     * @param transferRequestDto
     * @return
     */


    @Override
    @Transactional
    public TransferResponseDto run(TransferRequestDto transferRequestDto) {

        log.info("Running internal transfer for transfer request : {}", transferRequestDto);

        Transfer transfer = transferService.create(transferRequestDto);
        RequestKafkaTransferMessage requestKafkaTransferMessage = converter.convert(transfer);

        transferMoneyTopicSender.sendRequestMessage(

                getTopicNameByPaymentTypes(requestKafkaTransferMessage.getSourceType(), requestKafkaTransferMessage.getDestinationType()), requestKafkaTransferMessage);

        log.info("Ran internal transfer for transfer request : {}", transferRequestDto);
        return transferService.findById(transfer.getId());
    }

    String getTopicNameByPaymentTypes(String source, String destination) {

        if (CARD.equalsIgnoreCase(source) & source.equalsIgnoreCase(destination)) {
            return kafkaProperties.getPayment_transfer_request_topic_name();
        } else if (DEPOSIT.equalsIgnoreCase(source) & source.equalsIgnoreCase(destination)) {
            return kafkaProperties.getDeposit_transfer_request_topic_name();
        } else throw new RuntimeException("Not found topic for using payment-types");
    }

}
