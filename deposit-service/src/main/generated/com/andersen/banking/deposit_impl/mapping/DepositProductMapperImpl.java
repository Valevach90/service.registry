package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.CurrencyDto;
import com.andersen.banking.deposit_api.dto.DepositProductDto;
import com.andersen.banking.deposit_api.dto.DepositTypeDto;
import com.andersen.banking.deposit_db.entities.Currency;
import com.andersen.banking.deposit_db.entities.DepositProduct;
import com.andersen.banking.deposit_db.entities.DepositType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-17T15:16:15+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class DepositProductMapperImpl implements DepositProductMapper {

    @Override
    public DepositProductDto toDepositProductDto(DepositProduct depositProduct) {
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

    @Override
    public DepositProduct toDepositProduct(DepositProductDto depositProductDto) {
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
}
