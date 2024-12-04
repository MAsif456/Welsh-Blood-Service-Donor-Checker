package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DonorRecordsTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DonorService donorService;

    @Test
    public void shouldShowDonorRecords() throws Exception {
        // Mock the donor service to return a list of donors
        Donor donor1 = new Donor();
        donor1.setDonorId(1);
        donor1.setFirstName("John");
        donor1.setLastName("Doe");

        Donor donor2 = new Donor();
        donor2.setDonorId(2);
        donor2.setFirstName("Jane");
        donor2.setLastName("Smith");

        when(donorService.getAllDonors()).thenReturn(List.of(donor1, donor2));

        MvcResult result = mvc.perform(get("/donor-records"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("<td>John</td>"));
        assertTrue(content.contains("<td>Jane</td>"));
    }

    @Test
    public void shouldShowAllDonorsWhenIdIsNull() throws Exception {
        // Mock the donor service to return a list of donors
        Donor donor1 = new Donor();
        donor1.setDonorId(1);
        donor1.setFirstName("John");
        donor1.setLastName("Doe");

        Donor donor2 = new Donor();
        donor2.setDonorId(2);
        donor2.setFirstName("Jane");
        donor2.setLastName("Smith");

        when(donorService.getAllDonors()).thenReturn(List.of(donor1, donor2));

        MvcResult result = mvc.perform(get("/search-donor"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("An ID is required to search."));
        assertTrue(content.contains("<td>Doe</td>"));
        assertTrue(content.contains("<td>Smith</td>"));
    }

    @Test
    public void shouldShowErrorWhenDonorNotFound() throws Exception {
        // Mock the donor service to return null for a specific ID
        when(donorService.searchDonorById(1)).thenReturn(null);
        when(donorService.getAllDonors()).thenReturn(Collections.emptyList());

        MvcResult result = mvc.perform(get("/search-donor").param("id", "1"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("No donor found with the provided ID."));
    }

    @Test
    public void shouldShowDonorWhenIdIsProvided() throws Exception {
        // Mock the donor service to return a specific donor
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        when(donorService.searchDonorById(1)).thenReturn(donor);

        MvcResult result = mvc.perform(get("/search-donor").param("id", "1"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("<td>John</td>"));
        assertTrue(content.contains("<td>Doe</td>"));
    }
}
