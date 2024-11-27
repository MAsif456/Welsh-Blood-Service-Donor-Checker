package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;


@SpringBootTest
public class DonorRepositoryTest {

    @Autowired
    private DonorRepository donorRepository;


    /**
     * This method tests saving a donor to the repository and then finding the donor.
     */
    @Test
    public void testSaveAndFindDonor() {
        // Given: A new donor record is created
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("Jane");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1992, 2, 2));

        // When: The donor is saved to the repository and retrieved
        donorRepository.save(donor);
        Optional<Donor> retrievedDonor = donorRepository.findById(1);

        // Then: The retrieved donor should match the saved donor details
        assertTrue(retrievedDonor.isPresent(), "The donor should be found in the repository");
        assertEquals("Jane", retrievedDonor.get().getFirstName(), "The donor's first name should match");
        assertEquals("Doe", retrievedDonor.get().getLastName(), "The donor's last name should match");
        assertEquals(LocalDate.of(1992, 2, 2), retrievedDonor.get().getDonorDob(), "The donor's date of birth should match");
    }

}