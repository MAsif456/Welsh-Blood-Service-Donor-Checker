package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void shouldShowErrorMessageWhenIdIsNull() throws Exception {
        // Mock the donor service to return a list of donors
        Donor donor2 = new Donor();
        donor2.setDonorId(2);
        donor2.setFirstName("Jane");
        donor2.setLastName("Smith");

        when(donorService.searchDonorById(1)).thenReturn(donor2);

        MvcResult result = mvc.perform(get("/search-donor"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("An ID is required to search."));

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
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
    @WithMockUser(username = "admin", roles = {"ADMIN"})
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

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminSeesDecisionMadeByColumn() throws Exception {
        // Mock the donor service to return a list of donors
        Donor donor1 = new Donor();
        donor1.setDonorId(1);
        donor1.setFirstName("John");
        donor1.setLastName("Doe");
        donor1.setDecisionMade("Admin User");

        when(donorService.searchDonorById(1)).thenReturn(donor1);

        // Perform the request as an admin
        MvcResult When = mvc.perform(get("/donor-records").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andReturn();

        // Perform the request to search for a donor
        MvcResult Then = mvc.perform(get("/search-donor").param("id", "1"))
                .andExpect(status().isOk())
                .andReturn();


        String content = Then.getResponse().getContentAsString();

        // Verify that the "Decision Made By" column is present and contains the correct values
        assertTrue(content.contains("<th>Decision Made By</th>"));
        assertTrue(content.contains("<td>Admin User</td>"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"CLINICIAN"})
    public void testNonAdminDoesNotSeeDecisionMadeByColumn() throws Exception {
        // Mock the donor service to return a list of donors
        Donor donor1 = new Donor();
        donor1.setDonorId(1);
        donor1.setFirstName("John");
        donor1.setLastName("Doe");

        when(donorService.getAllDonors()).thenReturn(List.of(donor1));

        // Perform the request as a non-admin user
        MvcResult result = mvc.perform(get("/donor-records").with(user("user").roles("USER")))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Verify that the "Decision Made By" column is not present
        assertFalse(content.contains("<th>Decision Made By</th>"));
    }
}
