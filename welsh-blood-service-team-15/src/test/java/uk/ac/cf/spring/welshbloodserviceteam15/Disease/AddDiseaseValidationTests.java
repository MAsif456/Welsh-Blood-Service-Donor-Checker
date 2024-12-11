package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddDiseaseValidationTests {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidDiseaseDTO() {
        // Given
        DiseaseDTO diseaseDTO = new DiseaseDTO();
        diseaseDTO.setCountryId(1); // Just filling it in with random data to test validation
        diseaseDTO.setDiseaseName("Test Disease");
        diseaseDTO.setStartDate(LocalDate.now().minusDays(1));
        diseaseDTO.setEndDate(LocalDate.now());

        // When
        Set<ConstraintViolation<DiseaseDTO>> violations = validator.validate(diseaseDTO);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidDiseaseDTO() {
        // Given
        DiseaseDTO diseaseDTO = new DiseaseDTO();
        diseaseDTO.setCountryId(null); // Invalid data so should fail
        diseaseDTO.setDiseaseName(null); // Invalid data so should fail
        diseaseDTO.setStartDate(LocalDate.now().plusDays(1)); // Invalid data so should fail
        diseaseDTO.setEndDate(LocalDate.now().plusDays(1)); // Invalid data so should fail

        // When
        Set<ConstraintViolation<DiseaseDTO>> violations = validator.validate(diseaseDTO);

        // Then
        assertEquals(4, violations.size());
    }
}