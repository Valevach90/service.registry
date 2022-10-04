package com.andersen.banking.meeting_impl.service;

import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateCurrency;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateDeposit;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateDepositProduct;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateDepositType;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateRequestTransferKafkaMessage;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateResponseKafkaTransferMessage_WithSuccessfulResult;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateResponseKafkaTransferMessage_WithUnsuccessfulResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_db.entities.DepositProduct;
import com.andersen.banking.meeting_db.repositories.CurrencyRepository;
import com.andersen.banking.meeting_db.repositories.DepositProductRepository;
import com.andersen.banking.meeting_db.repositories.DepositRepository;
import com.andersen.banking.meeting_db.repositories.DepositTypeRepository;
import com.andersen.banking.meeting_impl.kafka.message.request.RequestTransferMessage;
import com.andersen.banking.meeting_impl.service.impl.DepositServiceImpl;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DepositServiceImplMakeTransferIntegrationTests {

    private Deposit sourceDeposit;
    private Deposit sourceDepositWithNotEnoughMoney;

    private DepositProduct depositProduct;

    private static final UUID SOURCE_DEPOSIT_ID = UUID.randomUUID();
    private static final UUID DESTINATION_DEPOSIT_ID = UUID.randomUUID();
    private static final String SOURCE_DEPOSIT_NUMBER = "0001";
    private static final String DESTINATION_DEPOSIT_NUMBER = "0002";

    @Autowired
    private DepositServiceImpl depositService;
    @Autowired
    private DepositTypeRepository depositTypeRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private DepositProductRepository depositProductRepository;
    @Autowired
    private DepositRepository depositRepository;
    @MockBean
    private TransferMoneyServiceKafkaResponseProducer transferMoneyServiceKafkaResponseProducer;

    @BeforeEach
    public void setUp() {
        depositTypeRepository.save(generateDepositType());
        currencyRepository.save(generateCurrency());
        depositProductRepository.save(generateDepositProduct());

        depositProduct = depositProductRepository.save(generateDepositProduct());

        sourceDeposit = generateDeposit();
        sourceDeposit.setDepositProduct(depositProduct);
        sourceDeposit.setId(SOURCE_DEPOSIT_ID);
        sourceDeposit.setDepositNumber(SOURCE_DEPOSIT_NUMBER);
        depositRepository.save(sourceDeposit);

        sourceDepositWithNotEnoughMoney = generateDeposit();
        sourceDepositWithNotEnoughMoney.setDepositProduct(depositProduct);
        sourceDepositWithNotEnoughMoney.setId(DESTINATION_DEPOSIT_ID);
        sourceDepositWithNotEnoughMoney.setDepositNumber(DESTINATION_DEPOSIT_NUMBER);
        depositRepository.save(sourceDepositWithNotEnoughMoney);
        depositRepository.flush();
    }

    @Test
    public void transferTest_whenTransferBetweenDepositsAndOk() {

        RequestTransferMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);
        ResponseTransferMessage response = generateResponseKafkaTransferMessage_WithSuccessfulResult(request);

        Long startedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();
        Long startedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();

        depositService.makeTransfer(request);

        Mockito
                .verify(transferMoneyServiceKafkaResponseProducer, Mockito.atLeastOnce())
                .sendResponse(response);

        Long finishedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();
        Long finishedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();
        Long transferAmount = request.getAmount();

        assertEquals(startedSourceAmount, finishedSourceAmount + transferAmount);
        assertEquals(startedDestinationAmount, finishedDestinationAmount - transferAmount);
        assertEquals(finishedSourceAmount + transferAmount, finishedDestinationAmount - transferAmount);
    }

    @Test
    public void transferTest_whenTransferBetweenDepositsAndSourceDepositNotFound() {

        String wrongSourceDepositNumber = "9999";
        RequestTransferMessage request = generateRequestTransferKafkaMessage(wrongSourceDepositNumber, DESTINATION_DEPOSIT_NUMBER);
        ResponseTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);
        response.setStatusDescription("Not found Deposit with number " + wrongSourceDepositNumber);

        Long startedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();
        Long startedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();

        depositService.makeTransfer(request);

        Mockito
                .verify(transferMoneyServiceKafkaResponseProducer, Mockito.atLeastOnce())
                .sendResponse(response);

        Long finishedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();
        Long finishedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();

        assertEquals(startedSourceAmount, finishedSourceAmount);
        assertEquals(startedDestinationAmount, finishedDestinationAmount);
    }

    @Test
    public void transferTest_whenTransferBetweenDepositsAndDestinationDepositNotFound() {

        String wrongDestinationDepositNumber = "9999";
        RequestTransferMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, wrongDestinationDepositNumber);
        ResponseTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);
        response.setStatusDescription("Not found Deposit with number " + wrongDestinationDepositNumber);

        Long startedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();
        Long startedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();

        depositService.makeTransfer(request);

        Mockito
                .verify(transferMoneyServiceKafkaResponseProducer, Mockito.atLeastOnce())
                .sendResponse(response);

        Long finishedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();
        Long finishedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();

        assertEquals(startedSourceAmount, finishedSourceAmount);
        assertEquals(startedDestinationAmount, finishedDestinationAmount);
    }

    @Test
    public void transferTest_whenTransferBetweenDepositsAndNotEnoughMoneyInSourceDeposit() {

        RequestTransferMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);
        ResponseTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);

        sourceDepositWithNotEnoughMoney = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get();
        sourceDepositWithNotEnoughMoney.setAmount(0L);
        depositRepository.save(sourceDepositWithNotEnoughMoney);
        depositRepository.flush();

        Long startedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();
        Long startedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();

        depositService.makeTransfer(request);

        Mockito
                .verify(transferMoneyServiceKafkaResponseProducer, Mockito.atLeastOnce())
                .sendResponse(response);

        Long finishedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();
        Long finishedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();

        assertEquals(startedSourceAmount, finishedSourceAmount);
        assertEquals(startedDestinationAmount, finishedDestinationAmount);
    }

    @Test
    public void transferToDepositTest_whenTransferFromDepositAndOk() {

        RequestTransferMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);
        String destinationType = "Account";
        request.setDestinationType(destinationType);
        ResponseTransferMessage response = generateResponseKafkaTransferMessage_WithSuccessfulResult(request);

        Long startedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();

        depositService.makeTransfer(request);

        Mockito
                .verify(transferMoneyServiceKafkaResponseProducer, Mockito.atLeastOnce())
                .sendResponse(response);

        Long finishedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();
        Long transferAmount = request.getAmount();

        assertEquals(startedSourceAmount, finishedSourceAmount + transferAmount);
    }

    @Test
    public void transferToDepositTest_whenTransferFromDepositAndNotEnoughMoneyInSourceDeposit() {

        RequestTransferMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);
        String destinationType = "Account";
        request.setDestinationType(destinationType);
        ResponseTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);

        sourceDepositWithNotEnoughMoney = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get();
        sourceDepositWithNotEnoughMoney.setAmount(0L);
        depositRepository.save(sourceDepositWithNotEnoughMoney);
        depositRepository.flush();

        Long startedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();

        depositService.makeTransfer(request);

        Mockito
                .verify(transferMoneyServiceKafkaResponseProducer, Mockito.atLeastOnce())
                .sendResponse(response);

        Long finishedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();

        assertEquals(startedSourceAmount, finishedSourceAmount);
    }

    @Test
    public void transferTest_whenTransferFromDepositsAndSourceDepositNotFound() {

        String wrongSourceDepositNumber = "9999";
        RequestTransferMessage request = generateRequestTransferKafkaMessage(wrongSourceDepositNumber, DESTINATION_DEPOSIT_NUMBER);
        String destinationType = "Account";
        request.setDestinationType(destinationType);
        ResponseTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);
        response.setStatusDescription("Not found Deposit with number " + wrongSourceDepositNumber);

        Long startedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();

        depositService.makeTransfer(request);

        Mockito
                .verify(transferMoneyServiceKafkaResponseProducer, Mockito.atLeastOnce())
                .sendResponse(response);

        Long finishedSourceAmount = depositRepository.findByDepositNumber(SOURCE_DEPOSIT_NUMBER).get().getAmount();

        assertEquals(startedSourceAmount, finishedSourceAmount);
    }

    @Test
    public void transferToDepositTest_whenTransferToDepositAndOk() {

        RequestTransferMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);
        String sourceType = "Account";
        request.setSourceType(sourceType);
        ResponseTransferMessage response = generateResponseKafkaTransferMessage_WithSuccessfulResult(request);

        Long startedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();

        depositService.makeTransfer(request);

        Mockito
                .verify(transferMoneyServiceKafkaResponseProducer, Mockito.atLeastOnce())
                .sendResponse(response);

        Long finishedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();
        Long transferAmount = request.getAmount();

        assertEquals(startedDestinationAmount, finishedDestinationAmount - transferAmount);
    }

    @Test
    public void transferTest_whenTransferToDepositsAndDestinationDepositNotFound() {

        String wrongDestinationDepositNumber = "9999";
        RequestTransferMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, wrongDestinationDepositNumber);
        String sourceType = "Account";
        request.setSourceType(sourceType);
        ResponseTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);
        response.setStatusDescription("Not found Deposit with number " + wrongDestinationDepositNumber);

        Long startedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();

        depositService.makeTransfer(request);

        Mockito
                .verify(transferMoneyServiceKafkaResponseProducer, Mockito.atLeastOnce())
                .sendResponse(response);

        Long finishedDestinationAmount = depositRepository.findByDepositNumber(DESTINATION_DEPOSIT_NUMBER).get().getAmount();

        assertEquals(startedDestinationAmount, finishedDestinationAmount);
    }
}
