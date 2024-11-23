package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DonorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DonorRepository donorRepository;

    /**
     * This method performs a test to get donor information with no risks.
     *
     * @throws Exception if an error occurs during the test execution
     */

    @Test
    public void shouldGetDonorInfoWithNoRisks() throws Exception {
        // Create a donor with no travel history
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("Jane");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1992, 2, 2));

        // Mock the repository to return the donor
        when(donorRepository.findById(1)).thenReturn(Optional.of(donor));

        // Perform the request and assert the expected text is present
        MvcResult result = mvc.perform(get("/donor/summary-risks/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("This donor has not travelled to a country at the time of any disease outbreak."));
        assertFalse(content.contains("Unexpected content"));
    }

}
