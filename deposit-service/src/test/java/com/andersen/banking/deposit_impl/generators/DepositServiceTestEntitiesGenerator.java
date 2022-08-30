package com.andersen.banking.deposit_impl.generators;

import com.andersen.banking.deposit_api.dto.*;
import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseKafkaTransferMessage;
import com.andersen.banking.deposit_db.entities.*;
import com.andersen.banking.deposit_db.entities.Currency;
import org.springframework.data.domain.*;

import java.sql.Date;
import java.util.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DepositServiceTestEntitiesGenerator {

    public static final Long ID = 1L;
    public static final UUID UUID_ID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
    public static final String DEPOSIT_NUMBER = "0001";
    public static final String CURRENCY_NAME = "EUR";
    public static final List<String> possibleCurrencyNames = List.of("USD", "RUB", "EU");
    public static final String DEPOSIT_TYPE_NAME = "Deposit";
    public static final String DEPOSIT_NAME = "First Deposit";
    public static final Integer MIN_TERM_IN_MONTHS = 1;
    public static final Integer MAX_TERM_IN_MONTHS = 12;
    public static final Long MIN_AMOUNT = 1_000L;
    public static final Long MAX_AMOUNT = 1_000_000L;
    public static final Integer MIN_INTEREST_RATE = 3;
    public static final Integer MAX_INTEREST_RATE = 6;

    public static final String REPLENISHMENT_SOURCE_TYPE = "Card";
    public static final String WITHDRAWAL_DESTINATION_TYPE = "Card";
    public static final String FROM_CARD_NUMBER = "0000_0000_0000_0001";
    public static final String TO_CARD_NUMBER = "0000_0000_0000_0002";

    public static final String TRANSFER_STATUS_DESCRIPTION = "Transfer successful";
    private static final Integer PAGE_NUMBER = 1;
    private static final Integer PAGE_SIZE = 10;
    private static final String PAGE_SORT_FIELD = "id";

    private static final String TRANSFER_WITH_DEPOSIT_TYPE = "Deposit";

    public static Currency generateCurrency(){
        Currency currency = new Currency();
        currency.setId(ID);
        currency.setName(CURRENCY_NAME);

        return currency;
    }

    public static Currency generateRandomCurrency() {
        Currency currency = new Currency();

        Random random = new Random();

        currency.setId(random.nextLong(1_000_000));
        currency.setName(possibleCurrencyNames.get(random.nextInt(3)));

        return currency;
    }

    public static CurrencyDto generateCurrencyDto(Currency currency) {
        CurrencyDto currencyDto = new CurrencyDto();

        currencyDto.setId(currency.getId());
        currencyDto.setName(currency.getName());

        return currencyDto;
    }

    public static DepositType generateDepositType() {
        DepositType type = new DepositType();

        type.setId(ID);
        type.setName(DEPOSIT_TYPE_NAME);

        return type;
    }

    public static DepositTypeDto generateDepositTypeDto(DepositType type) {
        DepositTypeDto typeDto = new DepositTypeDto();

        typeDto.setId(type.getId());
        typeDto.setName(type.getName());

        return typeDto;
    }

    public static DepositProduct generateDepositProduct() {
        DepositProduct product = new DepositProduct();

        product.setId(ID);
        product.setDepositName(DEPOSIT_NAME);
        product.setType(generateDepositType());
        product.setCurrency(generateCurrency());

        return completeDepositProductGeneration(product);
    }

    public static DepositProduct generateSemiEmptyDepositProduct() {
        DepositProduct product = new DepositProduct();

        Random random = new Random();

        return completeDepositProductGeneration(product);
    }

    private static DepositProduct completeDepositProductGeneration(DepositProduct product) {
        product.setMinTermMonths(MIN_TERM_IN_MONTHS);
        product.setMaxTermMonths(MAX_TERM_IN_MONTHS);
        product.setMinAmount(MIN_AMOUNT);
        product.setMaxAmount(MAX_AMOUNT);
        product.setMinInterestRate(MIN_INTEREST_RATE);
        product.setMaxInterestRate(MAX_INTEREST_RATE);
        product.setFixedInterest(true);
        product.setSubsequentReplenishment(true);
        product.setEarlyWithdrawal(true);
        product.setInterestWithdrawal(true);
        product.setCapitalization(true);
        product.setIsRevocable(true);
        product.setIsCustomizable(true);
        product.setIsActive(true);

        return product;
    }

    public static DepositProductDto generateDepositProductDto(DepositProduct product) {
        DepositProductDto productDto = new DepositProductDto();

        productDto.setId(product.getId());
        productDto.setDepositName(product.getDepositName());
        productDto.setType(generateDepositTypeDto(product.getType()));
        productDto.setCurrency(generateCurrencyDto(product.getCurrency()));
        productDto.setMinTermMonths(product.getMinTermMonths());
        productDto.setMaxTermMonths(product.getMaxTermMonths());
        productDto.setMinAmount(product.getMinAmount());
        productDto.setMaxAmount(product.getMaxAmount());
        productDto.setMinInterestRate(product.getMinInterestRate());
        productDto.setMaxInterestRate(product.getMaxInterestRate());
        productDto.setFixedInterest(product.getFixedInterest());
        productDto.setSubsequentReplenishment(product.getSubsequentReplenishment());
        productDto.setEarlyWithdrawal(product.getEarlyWithdrawal());
        productDto.setInterestWithdrawal(product.getInterestWithdrawal());
        productDto.setCapitalization(product.getCapitalization());
        productDto.setIsRevocable(product.getIsRevocable());
        productDto.setIsCustomizable(product.getIsCustomizable());
        productDto.setIsActive(product.getIsActive());

        return productDto;
    }

    public static Deposit generateDeposit() {
        Deposit deposit = new Deposit();
        List<Transfer> transfers = new ArrayList<>();

        deposit.setId(ID);
        deposit.setDepositNumber(DEPOSIT_NUMBER);
        deposit.setDepositProduct(generateDepositProduct());
        deposit.setType(generateDepositType());
        deposit.setCurrency(generateCurrency());
        deposit.setTermMonths(MIN_TERM_IN_MONTHS);
        deposit.setOpenDate(new Date(System.currentTimeMillis()));
        deposit.setAmount(MIN_AMOUNT);
        deposit.setInterestRate(MIN_INTEREST_RATE);
        deposit.setFixedInterest(true);
        deposit.setReplenishmentSourceNumber(FROM_CARD_NUMBER);
        deposit.setSubsequentReplenishment(true);
        deposit.setWithdrawalDestinationNumber(TO_CARD_NUMBER);
        deposit.setEarlyWithdrawal(true);
        deposit.setInterestWithdrawal(true);
        deposit.setCapitalization(true);
        deposit.setIsRevocable(true);
        deposit.setUserId(ID);
        deposit.setTransfers(transfers);

        return deposit;
    }

    public static DepositDto generateDepositDto(Deposit deposit) {
        DepositDto depositDto = new DepositDto();
        List<TransferDto> transfersDto = new ArrayList<>();

        depositDto.setId(                       deposit.getId());
        depositDto.setDepositNumber(            deposit.getDepositNumber());
        depositDto.setDepositProduct(           generateDepositProductDto(deposit.getDepositProduct()));
        depositDto.setType(                     generateDepositTypeDto(deposit.getType()));
        depositDto.setCurrency(                 generateCurrencyDto(deposit.getCurrency()));
        depositDto.setTermMonths(               deposit.getTermMonths());
        depositDto.setOpenDate(                 deposit.getOpenDate());
        depositDto.setAmount(                   deposit.getAmount());
        depositDto.setInterestRate(             deposit.getInterestRate());
        depositDto.setFixedInterest(            deposit.getFixedInterest());
        depositDto.setReplenishmentSourceNumber(deposit.getReplenishmentSourceNumber());
        depositDto.setSubsequentReplenishment(  deposit.getSubsequentReplenishment());
        depositDto.setWithdrawalDestinationNumber(deposit.getWithdrawalDestinationNumber());
        depositDto.setEarlyWithdrawal(          deposit.getEarlyWithdrawal());
        depositDto.setInterestWithdrawal(       deposit.getInterestWithdrawal());
        depositDto.setCapitalization(           deposit.getCapitalization());
        depositDto.setIsRevocable(              deposit.getIsRevocable());
        depositDto.setUserId(                   deposit.getUserId());

        for (Transfer transfer : deposit.getTransfers()){
            transfersDto.add(generateTransferDto(transfer));
        }
        depositDto.setTransfersDto(transfersDto);

        return depositDto;
    }

    public static Transfer generateTransfer() {
        Transfer transfer = new Transfer();

        transfer.setTransferId(UUID_ID);
        transfer.setUserId(ID);
        transfer.setDeposit(generateDeposit());
        transfer.setSourceNumber(FROM_CARD_NUMBER);
        transfer.setSourceType(REPLENISHMENT_SOURCE_TYPE);
        transfer.setDestinationNumber(TO_CARD_NUMBER);
        transfer.setDestinationType(WITHDRAWAL_DESTINATION_TYPE);
        transfer.setAmount(MIN_AMOUNT);
        transfer.setCurrencyName(CURRENCY_NAME);
        transfer.setTime(new Timestamp(System.currentTimeMillis()));
        transfer.setResult(true);
        transfer.setStatusDescription(TRANSFER_STATUS_DESCRIPTION);

        return transfer;
    }

    public static TransferDto generateTransferDto(Transfer transfer) {
        TransferDto transferDto = new TransferDto();

        transferDto.setTransferId(transfer.getTransferId());
        transferDto.setUserId(transfer.getUserId());
        transferDto.setDeposit(generateDepositDto(transfer.getDeposit()));
        transferDto.setSourceNumber(transfer.getSourceNumber());
        transferDto.setSourceType(transfer.getSourceType());
        transferDto.setDestinationNumber(transfer.getDestinationNumber());
        transferDto.setDestinationType(transfer.getDestinationType());
        transferDto.setAmount(transfer.getAmount());
        transferDto.setCurrencyName(transfer.getCurrencyName());
        transferDto.setTime(transfer.getTime());
        transferDto.setResult(transfer.getResult());
        transferDto.setStatusDescription(transfer.getStatusDescription());

        return transferDto;
    }

    public static RequestKafkaTransferMessage generateRequestTransferKafkaMessage(String sourceNumber, String destinationNumber) {

        RequestKafkaTransferMessage message = new RequestKafkaTransferMessage();

        message.setTransferId(UUID_ID);
        message.setUserId(ID);
        message.setSourceNumber(sourceNumber);
        message.setSourceType(TRANSFER_WITH_DEPOSIT_TYPE);
        message.setDestinationNumber(destinationNumber);
        message.setDestinationType(TRANSFER_WITH_DEPOSIT_TYPE);
        message.setAmount(MIN_AMOUNT);
        message.setCurrencyName(CURRENCY_NAME);

        return message;
    }

    public static ResponseKafkaTransferMessage generateResponseKafkaTransferMessage_WithSuccessfulResult(RequestKafkaTransferMessage request){

        ResponseKafkaTransferMessage message = new ResponseKafkaTransferMessage();

        message.setTransferId(request.getTransferId());
        message.setResult(true);

        return message;
    }

    public static ResponseKafkaTransferMessage generateResponseKafkaTransferMessage_WithUnsuccessfulResult(RequestKafkaTransferMessage request){

        ResponseKafkaTransferMessage message = new ResponseKafkaTransferMessage();

        message.setTransferId(request.getTransferId());
        message.setResult(false);

        return message;
    }

    public static Page<DepositProduct> generatePageOfDepositProducts(Pageable pageable){

        List<DepositProduct> listOfProducts = Stream
                .generate(DepositProduct::new)
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());

        Page<DepositProduct> pageOfProducts = new PageImpl<>(listOfProducts, pageable, PAGE_SIZE);

        return pageOfProducts;
    }

    public static Page<DepositProductDto> generatePageOfDepositProductsDto(Page<DepositProduct> pageOfDepositProducts) {
        Pageable pageable = pageOfDepositProducts.getPageable();

        List<DepositProductDto> listOfProductsDto = Stream
                .generate(DepositProductDto::new)
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());

        Page<DepositProductDto> pageOfProductsDto = new PageImpl<>(listOfProductsDto, pageable, PAGE_SIZE);

        return pageOfProductsDto;
    }

    public static Page<Deposit> generatePageOfDeposits(Pageable pageable) {
        List<Deposit> listOfDeposits = Stream
                .generate(Deposit::new)
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());

        Page<Deposit> pageOfDeposits = new PageImpl<>(listOfDeposits, pageable, PAGE_SIZE);

        return pageOfDeposits;
    }

    public static Page<DepositDto> generatePageOfDepositsDto(Page<Deposit> pageOfDeposits) {
        Pageable pageable = pageOfDeposits.getPageable();

        List<DepositDto> listOfDepositsDto = Stream
                .generate(DepositDto::new)
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());

        Page<DepositDto> pageOfDepositsDto = new PageImpl<>(listOfDepositsDto, pageable, PAGE_SIZE);

        return pageOfDepositsDto;
    }

    public static Page<Transfer> generatePageOfTransfers(Pageable pageable) {
        List<Transfer> listOfTransfers = Stream
                .generate(Transfer::new)
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());

        Page<Transfer> pageOfTransfers = new PageImpl<>(listOfTransfers, pageable, PAGE_SIZE);

        return pageOfTransfers;
    }

    public static Pageable createPageable() {
        return PageRequest.of(PAGE_NUMBER, PAGE_SIZE, Sort.by(Sort.Direction.ASC, PAGE_SORT_FIELD));
    }
    public static Pageable createCustomPageable(Integer pageNumber, Integer pageSize) {
        return PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, PAGE_SORT_FIELD));
    }
}
