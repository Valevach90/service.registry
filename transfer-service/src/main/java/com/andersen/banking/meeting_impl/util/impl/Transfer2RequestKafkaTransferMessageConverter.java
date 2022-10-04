package com.andersen.banking.meeting_impl.util.impl;

import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.util.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Transfer2RequestKafkaTransferMessageConverter implements
        Converter<RequestTransferMessage, Transfer> {

    private static final String CARD = "CARD";

    private static final String DEPOSIT = "DEPOSIT";

    /**
     * @param transfer
     * @return
     */
    @Override
    public RequestTransferMessage convert(Transfer transfer) throws RuntimeException {

        log.info("Converting to RequestKafkaTransferMessage transfer : {}", transfer);

        RequestTransferMessage requestTransferMessage = RequestTransferMessage.builder()
                .transferId(transfer.getId())
                .userId(transfer.getUserId())
                .currencyName(transfer.getCurrency().getName())
                .amount(transfer.getAmount())
                .sourceNumber(transfer.getSourceNumber())
                .destinationNumber(transfer.getDestinationNumber())
                .sourceType(transfer.getSourcePaymentType().getName())
                .destinationType(
                        transfer.getDestinationPaymentType().getName())
                .status(transfer.getStatus())
                .build();
        log.info("Converted to RequestKafkaTransferMessage transfer : {}", transfer);

        return requestTransferMessage;
    }
}
