package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.error.NotFoundError;
import com.andersen.banking.deposit_impl.config.MapperConfig;
import com.andersen.banking.deposit_impl.exceptions.NotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.http.HttpStatus;

/**
 * Mapper errors.
 */
@Mapper(
        config = MapperConfig.class,
        imports = HttpStatus.class
)
public interface ErrorMapper {

    @Mapping(target = "message", source = "exception.message")
    @Mapping(target = "errorCode", expression = "java(HttpStatus.NOT_FOUND.value())")
    NotFoundError toNotFoundError(NotFoundException exception);
}