package com.andersen.banking.service.registry.meeting_impl.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PatronymicValidator.class)
@Target( {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PatronymicConstraint {
    String message() default "Patronymic Validator";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}