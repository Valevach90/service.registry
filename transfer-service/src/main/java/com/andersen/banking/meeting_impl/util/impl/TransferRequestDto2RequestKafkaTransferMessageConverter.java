package com.andersen.banking.meeting_impl.util.impl;

import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.util.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransferRequestDto2RequestKafkaTransferMessageConverter implements Converter<RequestKafkaTransferMessage, Transfer> {

    /**
     * @param transfer
     * @return
     */

    @Override
    public RequestKafkaTransferMessage convert(Transfer transfer) {

        log.info("Converting to RequestKafkaTransferMessage transfer : {}", transfer);

        RequestKafkaTransferMessage requestKafkaTransferMessage = RequestKafkaTransferMessage.builder()
                .transferId(transfer.getId())
                .userId(transfer.getUserId())
                .currencyName(transfer.getCurrency().getName())
                .amount(transfer.getAmount())
                .sourceNumber(transfer.getSourceNumber())
                .sourceType(transfer.getSourcePaymentType().getName())
                .destinationNumber(transfer.getDestinationNumber())
                .destinationType(transfer.getDestinationPaymentType().getName())
                .build();

        log.info("Converted to RequestKafkaTransferMessage transfer : {}", transfer);

        return requestKafkaTransferMessage;
    }
}
