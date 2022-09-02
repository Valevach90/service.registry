package com.andersen.banking.meeting_impl.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CityNameValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CityNameConstraint {
    String message() default "MY CUSTOM <MESSAGE>";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
