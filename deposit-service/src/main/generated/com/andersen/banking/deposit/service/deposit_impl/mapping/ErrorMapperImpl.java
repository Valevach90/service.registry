package com.andersen.banking.deposit.service.deposit_impl.mapping;

import com.andersen.banking.deposit.service.deposit_api.error.NotFoundError;
import com.andersen.banking.deposit.service.deposit_impl.exceptions.NotFoundException;
import javax.annotation.processing.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-22T09:11:46+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ErrorMapperImpl implements ErrorMapper {

    @Override
    public NotFoundError toNotFoundError(NotFoundException exception) {
        if ( exception == null ) {
            return null;
        }

        NotFoundError notFoundError = new NotFoundError();

        notFoundError.setMessage( exception.getMessage() );

        notFoundError.setErrorCode( HttpStatus.NOT_FOUND.value() );

        return notFoundError;
    }
}