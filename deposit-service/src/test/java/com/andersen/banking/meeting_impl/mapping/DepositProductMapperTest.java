package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductResponseDto;
import com.andersen.banking.meeting_db.entities.DepositProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DepositProductMapperImpl.class)
public class DepositProductMapperTest {

    private DepositProduct product;
    private DepositProductResponseDto productDto;

    @Autowired
    DepositProductMapper depositProductMapper;

    @BeforeEach
    void initialize(){
        product = generateDepositProduct();
        productDto = generateDepositProductDto(product);
    }

    @Test
    void toDepositProduct_whenOk_shouldReturnDepositProduct(){
        DepositProduct result = depositProductMapper.toDepositProduct(productDto);

        assertEquals(product, result);
    }

    @Test
    void toDepositProductDto_whenOk_shouldReturnDepositProductDto(){
        DepositProductResponseDto result = depositProductMapper.toDepositProductDto(product);

        assertEquals(productDto, result);
    }
}
