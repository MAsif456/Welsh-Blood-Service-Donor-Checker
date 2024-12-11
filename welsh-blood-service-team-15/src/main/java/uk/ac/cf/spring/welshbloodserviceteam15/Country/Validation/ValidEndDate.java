package uk.ac.cf.spring.welshbloodserviceteam15.Country.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EndDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEndDate {
    String message() default "End Date must be a valid date after the Start Date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
