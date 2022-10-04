package com.andersen.banking.meeting_impl.util.impl;

import com.andersen.banking.meeting_db.entity.Transfer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class Transfer2RequestKafkaTransferMessageConverterTest {

    @Mock
    Transfer transfer;

    @InjectMocks
    Transfer2RequestKafkaTransferMessageConverter transfer2RequestKafkaTransferMessageConverter;


    @Test
    void convert_ShouldReturnRuntimeException_WhenTransferIdIsEqualsNull() {

        assertThrows(NullPointerException.class, () -> transfer2RequestKafkaTransferMessageConverter.convert(transfer));

    }
}