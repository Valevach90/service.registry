package com.andersen.banking.service.transfer.meeting_impl.kafka;

import com.andersen.banking.meeting_api.dto.message.TransferKafkaDeposit;
import com.andersen.banking.meeting_impl.kafka.listener.KafkaListenerDeposit;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import static com.andersen.banking.service.transfer.meeting_impl.generators.TransferServiceTestEntitiesGenerator.*;

@SpringBootTest(classes = KafkaListenerDeposit.class)
class KafkaListenerDepositTest {

    private final List<TransferKafkaDeposit> messages = generateListOfTransferKafkaDeposit();

    @MockBean
    KafkaListenerDeposit kafkaListenerDeposit;

    @Test
    void receive_WhenIsOk () {
        kafkaListenerDeposit.receive(messages);
        Mockito
                .verify(kafkaListenerDeposit, Mockito.times(1))
                .receive(messages);
    }
}