package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.CurrencyDto;
import com.andersen.banking.deposit_api.dto.DepositDto;
import com.andersen.banking.deposit_api.dto.DepositProductDto;
import com.andersen.banking.deposit_api.dto.DepositTypeDto;
import com.andersen.banking.deposit_api.dto.TransferDto;
import com.andersen.banking.deposit_db.entities.Currency;
import com.andersen.banking.deposit_db.entities.Deposit;
import com.andersen.banking.deposit_db.entities.DepositProduct;
import com.andersen.banking.deposit_db.entities.DepositType;
import com.andersen.banking.deposit_db.entities.Transfer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-09T00:36:37+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class DepositMapperImpl implements DepositMapper {

    @Override
    public DepositDto toDepositDto(Deposit deposit) {
        if ( deposit == null ) {
            return null;
        }

        DepositDto depositDto = new DepositDto();

        depositDto.setTransfersDto( transferListToTransferDtoList( deposit.getTransfers() ) );
        depositDto.setId( deposit.getId() );
        depositDto.setDepositNumber( deposit.getDepositNumber() );
        depositDto.setDepositProduct( depositProductToDepositProductDto( deposit.getDepositProduct() ) );
        depositDto.setType( depositTypeToDepositTypeDto( deposit.getType() ) );
        depositDto.setCurrency( currencyToCurrencyDto( deposit.getCurrency() ) );
        depositDto.setTermMonths( deposit.getTermMonths() );
        depositDto.setOpenDate( deposit.getOpenDate() );
        depositDto.setCloseDate( deposit.getCloseDate() );
        depositDto.setAmount( deposit.getAmount() );
        depositDto.setInterestRate( deposit.getInterestRate() );
        depositDto.setFixedInterest( deposit.getFixedInterest() );
        depositDto.setReplenishmentSourceNumber( deposit.getReplenishmentSourceNumber() );
        depositDto.setSubsequentReplenishment( deposit.getSubsequentReplenishment() );
        depositDto.setWithdrawalDestinationNumber( deposit.getWithdrawalDestinationNumber() );
        depositDto.setEarlyWithdrawal( deposit.getEarlyWithdrawal() );
        depositDto.setInterestWithdrawal( deposit.getInterestWithdrawal() );
        depositDto.setCapitalization( deposit.getCapitalization() );
        depositDto.setIsRevocable( deposit.getIsRevocable() );
        depositDto.setUserId( deposit.getUserId() );

        return depositDto;
    }

    @Override
    public Deposit toDeposit(DepositDto depositDto) {
        if ( depositDto == null ) {
            return null;
        }

        Deposit deposit = new Deposit();

        deposit.setTransfers( transferDtoListToTransferList( depositDto.getTransfersDto() ) );
        deposit.setId( depositDto.getId() );
        deposit.setDepositNumber( depositDto.getDepositNumber() );
        deposit.setDepositProduct( depositProductDtoToDepositProduct( depositDto.getDepositProduct() ) );
        deposit.setType( depositTypeDtoToDepositType( depositDto.getType() ) );
        deposit.setCurrency( currencyDtoToCurrency( depositDto.getCurrency() ) );
        deposit.setTermMonths( depositDto.getTermMonths() );
        deposit.setOpenDate( depositDto.getOpenDate() );
        deposit.setCloseDate( depositDto.getCloseDate() );
        deposit.setAmount( depositDto.getAmount() );
        deposit.setInterestRate( depositDto.getInterestRate() );
        deposit.setFixedInterest( depositDto.getFixedInterest() );
        deposit.setReplenishmentSourceNumber( depositDto.getReplenishmentSourceNumber() );
        deposit.setSubsequentReplenishment( depositDto.getSubsequentReplenishment() );
        deposit.setWithdrawalDestinationNumber( depositDto.getWithdrawalDestinationNumber() );
        deposit.setEarlyWithdrawal( depositDto.getEarlyWithdrawal() );
        deposit.setInterestWithdrawal( depositDto.getInterestWithdrawal() );
        deposit.setCapitalization( depositDto.getCapitalization() );
        deposit.setIsRevocable( depositDto.getIsRevocable() );
        deposit.setUserId( depositDto.getUserId() );

        return deposit;
    }

    protected TransferDto transferToTransferDto(Transfer transfer) {
        if ( transfer == null ) {
            return null;
        }

        TransferDto transferDto = new TransferDto();

        transferDto.setTransferId( transfer.getTransferId() );
        transferDto.setUserId( transfer.getUserId() );
        transferDto.setDeposit( toDepositDto( transfer.getDeposit() ) );
        transferDto.setSourceNumber( transfer.getSourceNumber() );
        transferDto.setSourceType( transfer.getSourceType() );
        transferDto.setDestinationNumber( transfer.getDestinationNumber() );
        transferDto.setDestinationType( transfer.getDestinationType() );
        transferDto.setAmount( transfer.getAmount() );
        transferDto.setCurrencyName( transfer.getCurrencyName() );
        transferDto.setTime( transfer.getTime() );
        transferDto.setResult( transfer.getResult() );
        transferDto.setStatusDescription( transfer.getStatusDescription() );

        return transferDto;
    }

    protected List<TransferDto> transferListToTransferDtoList(List<Transfer> list) {
        if ( list == null ) {
            return null;
        }

        List<TransferDto> list1 = new ArrayList<TransferDto>( list.size() );
        for ( Transfer transfer : list ) {
            list1.add( transferToTransferDto( transfer ) );
        }

        return list1;
    }

    protected DepositTypeDto depositTypeToDepositTypeDto(DepositType depositType) {
        if ( depositType == null ) {
            return null;
        }

        DepositTypeDto depositTypeDto = new DepositTypeDto();

        depositTypeDto.setId( depositType.getId() );
        depositTypeDto.setName( depositType.getName() );

        return depositTypeDto;
    }

    protected CurrencyDto currencyToCurrencyDto(Currency currency) {
        if ( currency == null ) {
            return null;
        }

        CurrencyDto currencyDto = new CurrencyDto();

        currencyDto.setId( currency.getId() );
        currencyDto.setName( currency.getName() );

        return currencyDto;
    }

    protected DepositProductDto depositProductToDepositProductDto(DepositProduct depositProduct) {
        if ( depositProduct == null ) {
            return null;
        }

        DepositProductDto depositProductDto = new DepositProductDto();

        depositProductDto.setId( depositProduct.getId() );
        depositProductDto.setDepositName( depositProduct.getDepositName() );
        depositProductDto.setType( depositTypeToDepositTypeDto( depositProduct.getType() ) );
        depositProductDto.setCurrency( currencyToCurrencyDto( depositProduct.getCurrency() ) );
        depositProductDto.setMinTermMonths( depositProduct.getMinTermMonths() );
        depositProductDto.setMaxTermMonths( depositProduct.getMaxTermMonths() );
        depositProductDto.setMinAmount( depositProduct.getMinAmount() );
        depositProductDto.setMaxAmount( depositProduct.getMaxAmount() );
        depositProductDto.setMinInterestRate( depositProduct.getMinInterestRate() );
        depositProductDto.setMaxInterestRate( depositProduct.getMaxInterestRate() );
        depositProductDto.setFixedInterest( depositProduct.getFixedInterest() );
        depositProductDto.setSubsequentReplenishment( depositProduct.getSubsequentReplenishment() );
        depositProductDto.setEarlyWithdrawal( depositProduct.getEarlyWithdrawal() );
        depositProductDto.setInterestWithdrawal( depositProduct.getInterestWithdrawal() );
        depositProductDto.setCapitalization( depositProduct.getCapitalization() );
        depositProductDto.setIsRevocable( depositProduct.getIsRevocable() );
        depositProductDto.setIsCustomizable( depositProduct.getIsCustomizable() );
        depositProductDto.setIsActive( depositProduct.getIsActive() );

        return depositProductDto;
    }

    protected Transfer transferDtoToTransfer(TransferDto transferDto) {
        if ( transferDto == null ) {
            return null;
        }

        Transfer transfer = new Transfer();

        transfer.setTransferId( transferDto.getTransferId() );
        transfer.setUserId( transferDto.getUserId() );
        transfer.setDeposit( toDeposit( transferDto.getDeposit() ) );
        transfer.setSourceNumber( transferDto.getSourceNumber() );
        transfer.setSourceType( transferDto.getSourceType() );
        transfer.setDestinationNumber( transferDto.getDestinationNumber() );
        transfer.setDestinationType( transferDto.getDestinationType() );
        transfer.setAmount( transferDto.getAmount() );
        transfer.setCurrencyName( transferDto.getCurrencyName() );
        transfer.setTime( transferDto.getTime() );
        transfer.setResult( transferDto.getResult() );
        transfer.setStatusDescription( transferDto.getStatusDescription() );

        return transfer;
    }

    protected List<Transfer> transferDtoListToTransferList(List<TransferDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Transfer> list1 = new ArrayList<Transfer>( list.size() );
        for ( TransferDto transferDto : list ) {
            list1.add( transferDtoToTransfer( transferDto ) );
        }

        return list1;
    }

    protected DepositType depositTypeDtoToDepositType(DepositTypeDto depositTypeDto) {
        if ( depositTypeDto == null ) {
            return null;
        }

        DepositType depositType = new DepositType();

        depositType.setId( depositTypeDto.getId() );
        depositType.setName( depositTypeDto.getName() );

        return depositType;
    }

    protected Currency currencyDtoToCurrency(CurrencyDto currencyDto) {
        if ( currencyDto == null ) {
            return null;
        }

        Currency currency = new Currency();

        currency.setId( currencyDto.getId() );
        currency.setName( currencyDto.getName() );

        return currency;
    }

    protected DepositProduct depositProductDtoToDepositProduct(DepositProductDto depositProductDto) {
        if ( depositProductDto == null ) {
            return null;
        }

        DepositProduct depositProduct = new DepositProduct();

        depositProduct.setId( depositProductDto.getId() );
        depositProduct.setDepositName( depositProductDto.getDepositName() );
        depositProduct.setType( depositTypeDtoToDepositType( depositProductDto.getType() ) );
        depositProduct.setCurrency( currencyDtoToCurrency( depositProductDto.getCurrency() ) );
        depositProduct.setMinTermMonths( depositProductDto.getMinTermMonths() );
        depositProduct.setMaxTermMonths( depositProductDto.getMaxTermMonths() );
        depositProduct.setMinAmount( depositProductDto.getMinAmount() );
        depositProduct.setMaxAmount( depositProductDto.getMaxAmount() );
        depositProduct.setMinInterestRate( depositProductDto.getMinInterestRate() );
        depositProduct.setMaxInterestRate( depositProductDto.getMaxInterestRate() );
        depositProduct.setFixedInterest( depositProductDto.getFixedInterest() );
        depositProduct.setSubsequentReplenishment( depositProductDto.getSubsequentReplenishment() );
        depositProduct.setEarlyWithdrawal( depositProductDto.getEarlyWithdrawal() );
        depositProduct.setInterestWithdrawal( depositProductDto.getInterestWithdrawal() );
        depositProduct.setCapitalization( depositProductDto.getCapitalization() );
        depositProduct.setIsRevocable( depositProductDto.getIsRevocable() );
        depositProduct.setIsCustomizable( depositProductDto.getIsCustomizable() );
        depositProduct.setIsActive( depositProductDto.getIsActive() );

        return depositProduct;
    }
}
