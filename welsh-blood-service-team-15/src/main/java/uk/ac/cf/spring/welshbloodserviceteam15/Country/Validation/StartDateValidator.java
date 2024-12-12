package uk.ac.cf.spring.welshbloodserviceteam15.Country.Validation;

import jakarta.validation.ConstraintValidator;

import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class StartDateValidator implements ConstraintValidator<ValidStartDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate startDate, ConstraintValidatorContext context) {
        return startDate == null || !startDate.isAfter(LocalDate.now());
    }
}