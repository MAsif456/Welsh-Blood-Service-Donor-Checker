package uk.ac.cf.spring.welshbloodserviceteam15.Country.Validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;

public class EndDateValidator implements ConstraintValidator<ValidEndDate, CountryDisease> {

    @Override
    public boolean isValid(CountryDisease countryDisease, ConstraintValidatorContext context) {
        if (countryDisease.getStartDate() == null || countryDisease.getEndDate() == null) {
            return true; // Let @NotNull handle null cases
        }
        boolean isValid = countryDisease.getEndDate().isAfter(countryDisease.getStartDate());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("End Date must be after Start Date")
                    .addPropertyNode("endDate")
                    .addConstraintViolation();
        }
        return isValid;
    }


}