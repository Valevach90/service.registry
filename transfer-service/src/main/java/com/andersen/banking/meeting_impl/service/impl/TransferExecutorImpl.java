package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferExecutor;
import com.andersen.banking.meeting_impl.util.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Sinks;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferExecutorImpl implements TransferExecutor {

    private final Converter<RequestTransferMessage, Transfer> converter;

    private final Sinks.Many<RequestTransferMessage> transferSink;

    /**
     * @param transfer
     * @return
     */
    @Override
    @Transactional
    public void execute(Transfer transfer) {

        log.info("Executing transfer for transfer request : {}", transfer);

        RequestTransferMessage message = converter.convert(transfer);
        transferSink.tryEmitNext(message);

        log.info("Executed transfer for transfer request : {}", transfer);
    }
}
