package uk.ac.cf.spring.welshbloodserviceteam15.Country.Validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StartDateValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStartDate {
    String message() default "Start Date must be a valid date in the past or present";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}