package com.andersen.banking.service.registry.meeting_impl.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PatronymicValidator implements ConstraintValidator<PatronymicConstraint, String> {

    @Override
    public boolean isValid(String patronymic, ConstraintValidatorContext context) {

        if(patronymic != null && !patronymic.isEmpty()) {
            return patronymic.matches("(?=.{1,30}$)([a-zA-Z]+(?:[-]?[a-zA-Z]+))|[a-zA-Z]{1,30}");
        }

        return true;
    }
}
