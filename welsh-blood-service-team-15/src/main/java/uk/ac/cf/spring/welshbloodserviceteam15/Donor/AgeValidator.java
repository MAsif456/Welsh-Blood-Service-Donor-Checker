package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

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
        return Period.between(donorDob, LocalDate.now()).getYears() >= 17;
    }
}
