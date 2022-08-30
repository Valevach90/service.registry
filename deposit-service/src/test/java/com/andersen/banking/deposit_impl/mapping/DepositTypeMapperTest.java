package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.DepositTypeDto;
import com.andersen.banking.deposit_db.entities.DepositType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.andersen.banking.deposit_impl.generators.DepositServiceTestEntitiesGenerator.generateDepositType;
import static com.andersen.banking.deposit_impl.generators.DepositServiceTestEntitiesGenerator.generateDepositTypeDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DepositTypeMapperImpl.class)
public class DepositTypeMapperTest {

    private DepositType depositType;
    private DepositTypeDto depositTypeDto;

    private final DepositTypeMapper depositTypeMapper = new DepositTypeMapperImpl();

    @BeforeEach
    void initialize() {
        depositType = generateDepositType();
        depositTypeDto = generateDepositTypeDto(depositType);
    }

    @Test
    void toDepositType_whenOk_shouldReturnDepositType() {
        DepositType result = depositTypeMapper.toDepositType(depositTypeDto);

        assertEquals(depositType, result);
    }

    @Test
    void toDepositTypeDto_whenOk_shouldReturnDepositTypeDto() {
        DepositTypeDto result = depositTypeMapper.toDepositTypeDto(depositType);

        assertEquals(depositTypeDto, result);
    }
}
