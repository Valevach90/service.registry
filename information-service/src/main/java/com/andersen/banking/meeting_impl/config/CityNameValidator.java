package com.andersen.banking.meeting_impl.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CityNameValidator implements ConstraintValidator<CityNameConstraint, String> {

    @Override
    public void initialize(CityNameConstraint cityName) {
    }

    @Override
    public boolean isValid(String cityName, ConstraintValidatorContext context) {
        return cityName != null && cityName.length() > 2 && cityName.matches("[a-zA-Z]+");
    }
}
