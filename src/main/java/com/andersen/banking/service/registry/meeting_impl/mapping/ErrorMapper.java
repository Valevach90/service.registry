package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.error.NotFoundError;
import com.andersen.banking.service.registry.meeting_impl.config.MapperConfig;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
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
