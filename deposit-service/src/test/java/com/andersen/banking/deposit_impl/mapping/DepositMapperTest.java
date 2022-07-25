package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.DepositDto;
import com.andersen.banking.deposit_db.entities.Deposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.andersen.banking.deposit_impl.generators.DepositServiceTestEntitiesGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DepositMapperImpl.class)
public class DepositMapperTest {

    private Deposit deposit;
    private DepositDto depositDto;

    @Autowired
    DepositMapper depositMapper;

    @BeforeEach
    void initialize(){
        deposit = generateDeposit();
        depositDto = generateDepositDto(deposit);
    }

    @Test
    void toDeposit_whenOk_shouldReturnDeposit(){
        Deposit result = depositMapper.toDeposit(depositDto);

        assertEquals(deposit, result);
    }

    @Test
    void toDepositDto_whenOk_shouldReturnDepositDto(){
        DepositDto result = depositMapper.toDepositDto(deposit);

        assertEquals(depositDto, result);
    }
}
