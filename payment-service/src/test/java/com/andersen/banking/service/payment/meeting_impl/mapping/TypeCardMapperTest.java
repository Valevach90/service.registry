package com.andersen.banking.service.payment.meeting_impl.mapping;

import com.andersen.banking.service.payment.meeting_api.dto.TypeCardResponseDto;
import com.andersen.banking.service.payment.meeting_api.dto.TypeCardUpdateDto;
import com.andersen.banking.service.payment.meeting_db.entities.TypeCard;
import com.andersen.banking.service.payment.meeting_impl.mapper.TypeCardMapper;
import com.andersen.banking.service.payment.meeting_impl.mapper.TypeCardMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.andersen.banking.service.payment.meeting_test.generators.CardUnitTestGenerator.populateTypeCard;
import static com.andersen.banking.service.payment.meeting_test.generators.CardUnitTestGenerator.populateTypeCardResponseDto;
import static com.andersen.banking.service.payment.meeting_test.generators.CardUnitTestGenerator.populateTypeCardUpdateDto;

@SpringBootTest(classes = TypeCardMapperImpl.class)
public class TypeCardMapperTest {

    private TypeCard typeCard;
    private TypeCardResponseDto typeCardResponseDto;
    private TypeCardUpdateDto typeCardUpdateDto;

    @Autowired
    TypeCardMapper typeCardMapper;

    @BeforeEach
    void initData() {
        typeCard = populateTypeCard();
        typeCardResponseDto = populateTypeCardResponseDto();
        typeCardUpdateDto = populateTypeCardUpdateDto();
    }

    @Test
    void whenTypeCard2TypeCardResponseDto_andOk() {
        var result = typeCardMapper.typeCard2TypeCardResponseDto(typeCard);
        checkForEqualsResponseDto(typeCard, result);
    }

    @Test
    void whenTypeCardResponseDto2TypeCard_andOk() {
        var result = typeCardMapper.typeCardResponseDto2TypeCard(typeCardResponseDto);
        checkForEqualsResponseDto(result, typeCardResponseDto);
    }

    @Test
    void whenTypeCard2TypeCardUpdateDto_andOk() {
        var result = typeCardMapper.typeCard2TypeCardUpdateDto(typeCard);
        checkForEqualsUpdateDto(typeCard, result);
    }

    @Test
    void whenTypeCardUpdateDto2TypeCard_andOk() {
        var result = typeCardMapper.typeCardUpdateDto2TypeCard(typeCardUpdateDto);
        checkForEqualsUpdateDto(result, typeCardUpdateDto);
    }


    private void checkForEqualsResponseDto(TypeCard typeCard, TypeCardResponseDto typeCardResponseDto) {
        assertEquals(typeCard.getId(), typeCardResponseDto.getId());
        assertEquals(typeCard.getTypeName(), typeCardResponseDto.getTypeName());
        assertEquals(typeCard.getPaymentSystem(), typeCardResponseDto.getPaymentSystem());
    }

    private void checkForEqualsUpdateDto(TypeCard typeCard, TypeCardUpdateDto typeCardUpdateDto) {
        assertEquals(typeCard.getId(), typeCardUpdateDto.getId());
        assertEquals(typeCard.getTypeName(), typeCardUpdateDto.getTypeName());
        assertEquals(typeCard.getPaymentSystem(), typeCardUpdateDto.getPaymentSystem());
    }
}
