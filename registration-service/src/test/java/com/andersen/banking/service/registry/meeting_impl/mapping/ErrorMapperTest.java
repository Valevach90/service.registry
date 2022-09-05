package com.andersen.banking.service.registry.meeting_impl.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.andersen.banking.service.registry.meeting_api.error.NotFoundError;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_test.generators.AddressGenerator;
import com.andersen.banking.service.registry.meeting_test.generators.UserGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(classes = {
        ErrorMapperImpl.class,
        AddressGenerator.class,
        UserGenerator.class})
class ErrorMapperTest {

    private Address address;

    @Autowired
    private ErrorMapper errorMapper;

    @BeforeEach
    void initData(
            @Autowired AddressGenerator addressGenerator,
            @Autowired UserGenerator userGenerator)
    {
        address = addressGenerator.generateAddress(userGenerator.generateUser());
    }

    @Test
    void whenMapToNotFoundError_andOK() {

        var notFoundError = new NotFoundError();
        var notFoundException = new NotFoundException(address.getClass(), address.getId());

        notFoundError.setMessage(String.format("Not found %s with %d id", address.getClass().getSimpleName(), address.getId()));
        notFoundError.setErrorCode(HttpStatus.NOT_FOUND.value());

        var returnedValue = errorMapper.toNotFoundError(notFoundException);

        assertNotNull(returnedValue);

        assertEquals(notFoundError.getMessage(), returnedValue.getMessage());
        assertEquals(notFoundError.getErrorCode(), returnedValue.getErrorCode());
    }
}