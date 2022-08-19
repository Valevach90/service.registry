package com.andersen.banking.deposit_impl.kafka;

import com.andersen.banking.deposit_api.dto.kafka.RequestTransferKafkaMessage;
import com.andersen.banking.deposit_api.dto.kafka.ResponseKafkaTransferMessage;
import com.andersen.banking.deposit_db.entities.Deposit;
import com.andersen.banking.deposit_db.repositories.*;
import com.andersen.banking.deposit_impl.kafka.utils.TransferServiceKafkaResponseConsumerImitation;
import com.andersen.banking.deposit_impl.kafka.utils.TransferServiceKafkaRequestProducerImitation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static com.andersen.banking.deposit_impl.generators.DepositServiceTestEntitiesGenerator.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class TransferMoneyToDepositWithEmbeddedKafkaIntegrationTests {

    @Autowired
    private TransferServiceKafkaResponseConsumerImitation consumer;
    @Autowired
    private TransferServiceKafkaRequestProducerImitation producer;

    @Value("${spring.kafka.topicTransferRequest}")
    private String requestTopic;

    private Deposit sourceDeposit;
    private Deposit destinationDeposit;

    private static final Long SOURCE_DEPOSIT_ID = 1L;
    private static final Long DESTINATION_DEPOSIT_ID = 2L;
    private static final String SOURCE_DEPOSIT_NUMBER = "0001";
    private static final String DESTINATION_DEPOSIT_NUMBER = "0002";

    @Autowired
    private DepositTypeRepository depositTypeRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private DepositProductRepository depositProductRepository;
    @Autowired
    private DepositRepository depositRepository;

    @BeforeEach
    public void setUp() {
        depositTypeRepository.save(generateDepositType());
        currencyRepository.save(generateCurrency());
        depositProductRepository.save(generateDepositProduct());

        sourceDeposit = generateDeposit();
        sourceDeposit.setId(SOURCE_DEPOSIT_ID);
        sourceDeposit.setDepositNumber(SOURCE_DEPOSIT_NUMBER);
        depositRepository.save(sourceDeposit);

        destinationDeposit = generateDeposit();
        destinationDeposit.setId(DESTINATION_DEPOSIT_ID);
        destinationDeposit.setDepositNumber(DESTINATION_DEPOSIT_NUMBER);
        depositRepository.save(destinationDeposit);

        depositRepository.flush();
    }

    @Test
    public void transferToDepositTest_whenOk() throws InterruptedException {

        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);

        producer.send(requestTopic, request);

        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);

        ResponseKafkaTransferMessage receivedResponse = consumer.getMessage();

        assertEquals(request.getTransferId(), receivedResponse.getTransferId());
        assertTrue(receivedResponse.getResult());
    }

    @Test
    public void transferToDepositTest_whenSourceDepositNotFound() throws InterruptedException {

        String wrongSourceDepositNumber = "9999";
        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(wrongSourceDepositNumber, DESTINATION_DEPOSIT_NUMBER);

        producer.send(requestTopic, request);

        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);

        System.out.println();
        System.out.println(depositRepository.findAll());
        System.out.println();
        ResponseKafkaTransferMessage receivedResponse = consumer.getMessage();

        assertEquals(request.getTransferId(), receivedResponse.getTransferId());
        assertFalse(receivedResponse.getResult());
    }

    @Test
    public void transferToDepositTest_whenDestinationDepositNotFound() throws InterruptedException {

        String wrongDestinationDepositNumber = "9999";
        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, wrongDestinationDepositNumber);

        producer.send(requestTopic, request);

        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);

        ResponseKafkaTransferMessage receivedResponse = consumer.getMessage();

        assertEquals(request.getTransferId(), receivedResponse.getTransferId());
        assertFalse(receivedResponse.getResult());
    }

    @Test
    public void transferToDepositTest_whenNotEnoughMoneyInSourceDeposit() throws InterruptedException {

        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);

        destinationDeposit = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get();
        destinationDeposit.setAmount(0L);
        depositRepository.save(destinationDeposit);
        depositRepository.flush();

        producer.send(requestTopic, request);

        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);

        ResponseKafkaTransferMessage receivedResponse = consumer.getMessage();

        assertEquals(request.getTransferId(), receivedResponse.getTransferId());
        assertFalse(receivedResponse.getResult());
    }
}
