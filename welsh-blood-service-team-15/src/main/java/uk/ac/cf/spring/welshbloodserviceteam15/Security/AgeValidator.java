package uk.ac.cf.spring.welshbloodserviceteam15.Security;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<ValidDonorAge, LocalDate> {

    @Override
    public void initialize(ValidDonorAge constraintAnnotation) {
    }



@Override
public boolean isValid(LocalDate donorDob, ConstraintValidatorContext context) {
    if (donorDob == null) {
        return false;
    }
    LocalDate minDate = LocalDate.of(1900, 1, 1);
    LocalDate maxDate = LocalDate.now();
    if (donorDob.isBefore(minDate) || donorDob.isAfter(maxDate)) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Invalid date used").addConstraintViolation();
        return false;
    }
    if (Period.between(donorDob, LocalDate.now()).getYears() < 17) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Donor must be at least 17 years old").addConstraintViolation();
        return false;
    }
    return true;
}
}

