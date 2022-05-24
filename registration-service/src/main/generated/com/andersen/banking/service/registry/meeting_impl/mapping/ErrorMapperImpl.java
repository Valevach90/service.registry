package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.error.NotFoundError;
import com.andersen.banking.service.registry.meeting_impl.exceptions.FoundException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import javax.annotation.processing.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-19T15:59:51+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
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

    @Override
    public NotFoundError toFoundError(FoundException exception) {
        if ( exception == null ) {
            return null;
        }

        NotFoundError notFoundError = new NotFoundError();

        notFoundError.setMessage( exception.getMessage() );

        notFoundError.setErrorCode( HttpStatus.NOT_FOUND.value() );

        return notFoundError;
    }
}
