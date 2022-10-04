package com.andersen.banking.meeting_impl.kafka;

import com.andersen.banking.meeting_impl.kafka.message.request.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import java.util.concurrent.ExecutionException;

import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class TransferMoneyToDepositWithEmbeddedKafkaIntegrationTests {

    @Value("${spring.kafka.topicTransferResponse}")
    private String responseTopic;

    @Autowired
    private KafkaTemplate<String, ResponseTransferMessage> kafkaTemplate;

    @Test
    public void sender_whenOk() throws InterruptedException, ExecutionException {

        String sourceDepositNumber = "0001";
        String destinationDepositNumber = "0002";

        RequestTransferMessage request = generateRequestTransferKafkaMessage(sourceDepositNumber, destinationDepositNumber);
        ResponseTransferMessage response = generateResponseKafkaTransferMessage_WithSuccessfulResult(request);

        ListenableFuture<SendResult<String, ResponseTransferMessage>> future = kafkaTemplate.send(responseTopic, response);

        Thread.sleep(10000);

        future.addCallback(new ListenableFutureCallback<SendResult<String, ResponseTransferMessage>>() {

            @Override
            public void onSuccess(SendResult<String, ResponseTransferMessage> result) {
                System.out.println("Response sent successful with offset = " + result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable exception) {
                System.out.println("Unable to send response " + exception);
            }
        });

        assertNull(future.get().getProducerRecord().key());
        assertEquals(response, future.get().getProducerRecord().value());
    }
}