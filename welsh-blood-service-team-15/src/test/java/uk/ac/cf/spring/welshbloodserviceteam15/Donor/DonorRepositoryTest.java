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
        // Create a new donor
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("Jane");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1992, 2, 2));

        // Save the donor to the repository
        donorRepository.save(donor);

        // Retrieve the donor from the repository
        Optional<Donor> retrievedDonor = donorRepository.findById(1);

        // Assert that the donor was found and the details are correct
        assertTrue(retrievedDonor.isPresent());
        assertEquals("Jane", retrievedDonor.get().getFirstName());
        assertEquals("Doe", retrievedDonor.get().getLastName());
        assertEquals(LocalDate.of(1992, 2, 2), retrievedDonor.get().getDonorDob());
    }
}