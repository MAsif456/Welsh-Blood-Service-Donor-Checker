package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
public class DonorFormTests {

    private jakarta.validation.Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testFirstNameIsEmpty() {
        // Given a staff member inputs a potential donor's first and last name,
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName(""); // Invalid: empty
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        // When the staff member submits the form,
        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);

        // Then the form should return an error and there should be validation in place.
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("First name is required")));
    }

    @Test
    public void testFirstNameIsTooShort() {
        // Given a staff member inputs a potential donor's first and last name,
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("A"); // Invalid: too short
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        // When the staff member submits the form,
        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);

        // Then the form should return an error and there should be validation in place.
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("First name must be between 2 and 50 characters")));
    }

    @Test
    public void testFirstNameContainsNonAlphabeticCharacters() {
        // Given a staff member inputs a potential donor's first and last name,
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John123"); // Invalid: contains non-alphabetic characters
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        // When the staff member submits the form,
        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);

        // Then the form should return an error and there should be validation in place.
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("First name must contain only alphabetic characters")));
    }

    @Test
    public void testFirstNameIsValid() {
        // Given a staff member inputs a potential donor's first and last name,
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John"); // Valid
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        // When the staff member submits the form,
        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);

        // Then the form should not return any errors.
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testLastNameIsEmpty() {
        // Given a staff member inputs a potential donor's first and last name,
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName(""); // Invalid: empty
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        // When the staff member submits the form,
        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);

        // Then the form should return an error and there should be validation in place.
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Last name is required")));
    }

    @Test
    public void testLastNameIsTooShort() {
        // Given a staff member inputs a potential donor's first and last name,
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("D"); // Invalid: too short
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        // When the staff member submits the form,
        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);

        // Then the form should return an error and there should be validation in place.
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Last name must be between 2 and 50 characters")));
    }

    @Test
    public void testLastNameContainsNonAlphabeticCharacters() {
        // Given a staff member inputs a potential donor's first and last name,
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("Doe123"); // Invalid: contains non-alphabetic characters
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        // When the staff member submits the form,
        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);

        // Then the form should return an error and there should be validation in place.
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Last name must contain only alphabetic characters")));
    }

    @Test
    public void testLastNameIsValid() {
        // Given a staff member inputs a potential donor's first and last name,
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("Doe"); // Valid
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        // When the staff member submits the form,
        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);

        // Then the form should not return any errors.
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testDonorDobIsNull() {
        // Given a staff member inputs a potential donor's first and last name,
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(null); // Invalid: null

        // When the staff member submits the form,
        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);

        // Then the form should return an error and there should be validation in place.
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Date of birth is required")));
    }

    @Test
    public void testDonorDobIsLessThan17YearsOld() {
        // Given a donor is under the age of 17,
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.now().minusYears(16)); // Invalid: less than 17 years old

        // When a staff member inputs their name and DOB information,
        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);

        // Then they should not be able to move onto the travel form and potentially donate.
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Donor must be at least 17 years old")));
    }

    @Test
    public void testDonorDobIsValid() {
        // Given a donor is or over the age of 17,
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.now().minusYears(18)); // Valid

        // When a staff member inputs their name and DOB information,
        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);

        // Then they should be able to move onto the travel form and potentially donate.
        assertTrue(violations.isEmpty());
    }


}
