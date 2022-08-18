package com.andersen.banking.deposit_impl.kafka;

import com.andersen.banking.deposit_api.dto.messages.AccruedAmount;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Random;
import java.util.concurrent.Future;
import static com.andersen.banking.deposit_impl.generators.DepositServiceTestEntitiesGenerator.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = KafkaProducer.class)
class KafkaProducerTest {

    private AccruedAmount accruedAmount;

    @MockBean
    KafkaProducer kafkaProducer;

    @BeforeEach
    void initialize(){
        accruedAmount = generateAccruedAmount();
    }

    @Test
    void sendMessage() {
        MockProducer<String, AccruedAmount> mockProducer = new MockProducer<>(true, new StringSerializer(),
                (topic, accruedAmount) -> {
                    byte[] key = new byte[16];
                    Random random = new Random();
                    random.nextBytes(key);
                    return key;
                });
        kafkaProducer = new KafkaProducer(mockProducer);
        Future<RecordMetadata> recordMetadataFuture =
                kafkaProducer.sendMessage("interest_calculation", accruedAmount);

        assertEquals(1, mockProducer.history().size());
    }
}