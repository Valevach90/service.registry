package com.andersen.banking.deposit.service.deposit_impl.generators;

import com.andersen.banking.deposit.service.deposit_api.dto.CurrencyDto;
import com.andersen.banking.deposit.service.deposit_api.dto.DepositProductDto;
import com.andersen.banking.deposit.service.deposit_api.dto.DepositTypeDto;
import com.andersen.banking.deposit.service.deposit_db.entities.Currency;
import com.andersen.banking.deposit.service.deposit_db.entities.DepositProduct;
import com.andersen.banking.deposit.service.deposit_db.entities.DepositType;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DepositServiceEntitiesGenerator {

    public static final Long ID = 1L;
    public static final String CURRENCY_NAME = "EUR";
    public static final String DEPOSIT_TYPE_NAME = "Deposit";
    public static final String DEPOSIT_NAME = "First Deposit";
    public static final Integer MIN_TERM_IN_MONTHS = 1;
    public static final Integer MAX_TERM_IN_MONTHS = 12;
    public static final Long MIN_AMOUNT = 1_000L;
    public static final Long MAX_AMOUNT = 1_000_000L;
    public static final Integer MIN_INTEREST_RATE = 3;
    public static final Integer MAX_INTEREST_RATE = 6;

    private static final Integer PAGE_NUMBER = 1;
    private static final Integer PAGE_SIZE = 10;
    private static final String PAGE_SORT_FIELD = "id";

    public static Currency generateCurrency(){
        Currency currency = new Currency();

        currency.setId(ID);
        currency.setName(CURRENCY_NAME);

        return currency;
    }

    public static CurrencyDto generateCurrencyDto(Currency currency){
        CurrencyDto currencyDto = new CurrencyDto();

        currencyDto.setId(currency.getId());
        currencyDto.setName(currency.getName());

        return currencyDto;
    }

    public static DepositType generateDepositType(){
        DepositType type = new DepositType();

        type.setId(ID);
        type.setName(DEPOSIT_TYPE_NAME);

        return type;
    }

    public static DepositTypeDto generateDepositTypeDto(DepositType type){
        DepositTypeDto typeDto = new DepositTypeDto();

        typeDto.setId(type.getId());
        typeDto.setName(type.getName());

        return typeDto;
    }

    public static DepositProduct generateDepositProduct(){
        DepositProduct product = new DepositProduct();

        product.setId(ID);
        product.setDepositName(DEPOSIT_NAME);
        product.setType(generateDepositType());
        product.setCurrency(generateCurrency());
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

    public static DepositProductDto generateDepositProductDto(DepositProduct product){
        DepositProductDto productDto = new DepositProductDto();

        productDto.setId(                       product.getId());
        productDto.setDepositName(              product.getDepositName());
        productDto.setType(                     generateDepositTypeDto(product.getType()));
        productDto.setCurrency(                 generateCurrencyDto(product.getCurrency()));
        productDto.setMinTermMonths(            product.getMinTermMonths());
        productDto.setMaxTermMonths(            product.getMaxTermMonths());
        productDto.setMinAmount(                product.getMinAmount());
        productDto.setMaxAmount(                product.getMaxAmount());
        productDto.setMinInterestRate(          product.getMinInterestRate());
        productDto.setMaxInterestRate(          product.getMaxInterestRate());
        productDto.setFixedInterest(            product.getFixedInterest());
        productDto.setSubsequentReplenishment(  product.getSubsequentReplenishment());
        productDto.setEarlyWithdrawal(          product.getEarlyWithdrawal());
        productDto.setInterestWithdrawal(       product.getInterestWithdrawal());
        productDto.setCapitalization(           product.getCapitalization());
        productDto.setIsRevocable(              product.getIsRevocable());
        productDto.setIsCustomizable(           product.getIsCustomizable());
        productDto.setIsActive(                 product.getIsActive());

        return productDto;
    }

    public static Page<DepositProduct> generatePageOfDepositProducts(Pageable pageable){
        List<DepositProduct> listOfProducts = Stream
                                                    .generate(DepositProduct::new)
                                                    .limit(pageable.getPageSize())
                                                    .collect(Collectors.toList());

        Page<DepositProduct> pageOfProducts = new PageImpl<>(listOfProducts, pageable, PAGE_SIZE);

        return pageOfProducts;
    }

    public static Page<DepositProductDto> generatePageOfDepositProductsDto(Page<DepositProduct> pageOfDepositProduct){
        Pageable pageable = pageOfDepositProduct.getPageable();

        List<DepositProductDto> listOfProductsDto = Stream
                .generate(DepositProductDto::new)
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());

        Page<DepositProductDto> pageOfProductsDto = new PageImpl<>(listOfProductsDto, pageable, PAGE_SIZE);

        return pageOfProductsDto;
    }
    public static Pageable createPageable() {
        return PageRequest.of(PAGE_NUMBER, PAGE_SIZE, Sort.by(Sort.Direction.ASC, PAGE_SORT_FIELD));
    }
}
