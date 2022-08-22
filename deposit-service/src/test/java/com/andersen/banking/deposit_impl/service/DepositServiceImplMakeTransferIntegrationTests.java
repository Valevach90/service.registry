package com.andersen.banking.deposit_impl.service;

import com.andersen.banking.deposit_api.dto.kafka.RequestTransferKafkaMessage;
import com.andersen.banking.deposit_api.dto.kafka.ResponseKafkaTransferMessage;
import com.andersen.banking.deposit_db.entities.Deposit;
import com.andersen.banking.deposit_db.repositories.*;
import com.andersen.banking.deposit_impl.kafka.TransferMoneyServiceKafkaResponseProducer;
import com.andersen.banking.deposit_impl.service.impl.DepositServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import static com.andersen.banking.deposit_impl.generators.DepositServiceTestEntitiesGenerator.*;
import static com.andersen.banking.deposit_impl.generators.DepositServiceTestEntitiesGenerator.generateRequestTransferKafkaMessage;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DepositServiceImplMakeTransferIntegrationTests {

    private Deposit sourceDeposit;
    private Deposit sourceDepositWithNotEnoughMoney;

    private static final Long SOURCE_DEPOSIT_ID = 1L;
    private static final Long DESTINATION_DEPOSIT_ID = 2L;
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

        sourceDeposit = generateDeposit();
        sourceDeposit.setId(SOURCE_DEPOSIT_ID);
        sourceDeposit.setDepositNumber(SOURCE_DEPOSIT_NUMBER);
        depositRepository.save(sourceDeposit);

        sourceDepositWithNotEnoughMoney = generateDeposit();
        sourceDepositWithNotEnoughMoney.setId(DESTINATION_DEPOSIT_ID);
        sourceDepositWithNotEnoughMoney.setDepositNumber(DESTINATION_DEPOSIT_NUMBER);
        depositRepository.save(sourceDepositWithNotEnoughMoney);
        depositRepository.flush();
    }

    @Test
    public void transferTest_whenTransferBetweenDepositsAndOk() {

        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);
        ResponseKafkaTransferMessage response = generateResponseKafkaTransferMessage_WithSuccessfulResult(request);

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
        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(wrongSourceDepositNumber, DESTINATION_DEPOSIT_NUMBER);
        ResponseKafkaTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);
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
        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, wrongDestinationDepositNumber);
        ResponseKafkaTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);
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

        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);
        ResponseKafkaTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);

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

        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);
        String destinationType = "Account";
        request.setDestinationType(destinationType);
        ResponseKafkaTransferMessage response = generateResponseKafkaTransferMessage_WithSuccessfulResult(request);

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

        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);
        String destinationType = "Account";
        request.setDestinationType(destinationType);
        ResponseKafkaTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);

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
        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(wrongSourceDepositNumber, DESTINATION_DEPOSIT_NUMBER);
        String destinationType = "Account";
        request.setDestinationType(destinationType);
        ResponseKafkaTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);
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

        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, DESTINATION_DEPOSIT_NUMBER);
        String sourceType = "Account";
        request.setSourceType(sourceType);
        ResponseKafkaTransferMessage response = generateResponseKafkaTransferMessage_WithSuccessfulResult(request);

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
        RequestTransferKafkaMessage request = generateRequestTransferKafkaMessage(SOURCE_DEPOSIT_NUMBER, wrongDestinationDepositNumber);
        String sourceType = "Account";
        request.setSourceType(sourceType);
        ResponseKafkaTransferMessage response = generateResponseKafkaTransferMessage_WithUnsuccessfulResult(request);
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
