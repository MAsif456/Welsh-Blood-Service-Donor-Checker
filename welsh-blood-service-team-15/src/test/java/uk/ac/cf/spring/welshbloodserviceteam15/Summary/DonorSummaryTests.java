package uk.ac.cf.spring.welshbloodserviceteam15.Summary;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountry;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class DonorSummaryTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DonorRepository donorRepository;

    private Donor makeMockDonor() {
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1990, 1, 1));
        donor.setDonorRisk("Low");
        return donor;
    }

    private DonorTravelledToCountry makeMockTravel(Donor donor) {
        DonorTravelledToCountry travel = new DonorTravelledToCountry();
        Country country = new Country();
        country.setCountryId(1);
        country.setCountryName("Spain");
        travel.setDonor(donor);
        travel.setCountry(country);
        travel.setEndDate(LocalDate.of(2024, 11, 19));
        return travel;
    }


    @Test
    public void shouldGetDonorInfo() throws Exception {
        // Given
        Donor donor = makeMockDonor();

        // Mock repository behavior
        when(donorRepository.findByDonorId(1)).thenReturn(donor);

        // When
        MvcResult result = mvc
                .perform(get("/donor/summary/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Then
        assertTrue(content.contains("<span>John Doe</span>"));
        assertTrue(content.contains("<span>1990-01-01</span>"));
        assertTrue(content.contains("<span class=\"risk low\">Low risk</span>"));
    }

    @Test
    public void shouldGetNoTravelData () throws Exception {
        // Given
        Donor donor = makeMockDonor();

        // Mock repository behavior
        when(donorRepository.findByDonorId(1)).thenReturn(donor);

        // When
        MvcResult result = mvc
                .perform(get("/donor/summary/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Then
        assertTrue(content.contains("<p>No travel history available.</p>"));
    }

    @Test
    public void shouldGetTravelData () throws Exception {
        // Given
        Donor donor = makeMockDonor();

        DonorTravelledToCountry travel = makeMockTravel(donor);

        donor.setTravelHistory(List.of(travel));

        // Mock repository behavior
        when(donorRepository.findByDonorId(1)).thenReturn(donor);

        // When
        MvcResult result = mvc
                .perform(get("/donor/summary/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Then
         assertTrue(content.contains("<p><strong>Country:</strong> <span>Spain</span></p>"));
         assertTrue(content.contains("<p><strong>Visit Dates:</strong> <span>2024-11-14</span> to\n" +
                 "                    <span>2024-11-19</span></p>"));
    }
}
