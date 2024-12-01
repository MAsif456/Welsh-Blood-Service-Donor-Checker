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
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName(""); // Invalid: empty
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("First name is required")));
    }

    @Test
    public void testFirstNameIsTooShort() {
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("A"); // Invalid: too short
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("First name must be between 2 and 50 characters")));
    }

    @Test
    public void testFirstNameContainsNonAlphabeticCharacters() {
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John123"); // Invalid: contains non-alphabetic characters
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("First name must contain only alphabetic characters")));
    }

    @Test
    public void testFirstNameIsValid() {
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John"); // Valid
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testLastNameIsEmpty() {
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName(""); // Invalid: empty
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Last name is required")));
    }

    @Test
    public void testLastNameIsTooShort() {
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("D"); // Invalid: too short
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Last name must be between 2 and 50 characters")));
    }

    @Test
    public void testLastNameContainsNonAlphabeticCharacters() {
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("Doe123"); // Invalid: contains non-alphabetic characters
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Last name must contain only alphabetic characters")));
    }

    @Test
    public void testLastNameIsValid() {
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("Doe"); // Valid
        donorForm.setDonorDob(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testDonorDobIsNull() {
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(null); // Invalid: null

        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Date of birth is required")));
    }

    @Test
    public void testDonorDobIsLessThan17YearsOld() {
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.now().minusYears(16)); // Invalid: less than 17 years old

        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Donor must be at least 17 years old")));
    }

    @Test
    public void testDonorDobIsValid() {
        DonorForm donorForm = new DonorForm();
        donorForm.setFirstName("John");
        donorForm.setLastName("Doe");
        donorForm.setDonorDob(LocalDate.now().minusYears(18)); // Valid

        Set<ConstraintViolation<DonorForm>> violations = validator.validate(donorForm, new Class<?>[0]);
        assertTrue(violations.isEmpty());
    }


}
