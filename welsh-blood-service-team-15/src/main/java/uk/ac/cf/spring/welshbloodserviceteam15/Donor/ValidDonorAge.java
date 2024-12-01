package uk.ac.cf.spring.welshbloodserviceteam15.Donor;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Specifies which class will implement the logic to validate the annotation
@Constraint(validatedBy = AgeValidator.class)
//This annotation is used to shows that this annotation can be used on fields
@Target({ ElementType.FIELD })
//This annotation is used to show that this annotation will be available at runtime
//This is necessary for the validation to work, as the validation is done at runtime
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDonorAge {
    String message() default "Donor must be at least 17 years old";
    //Allows for multiple constraints to be applied to the same element
    Class<?>[] groups() default {};
    //Allows for additional information to be passed to the constraint
    Class<? extends Payload>[] payload() default {};
}