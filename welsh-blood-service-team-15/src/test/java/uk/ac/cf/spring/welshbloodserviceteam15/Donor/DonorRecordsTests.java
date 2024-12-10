package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes.DecisionAndNotesService;
import uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes.DonorDetails;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.QuestionnaireService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DonorRecordsTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionnaireService questionnaireService;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private DonorDetails donorDetails;

    @MockBean
    private DonorService donorService;

    @MockBean
    private DecisionAndNotesService decisionAndNotesService;


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

    @Test
    @WithMockUser(username = "clinician", roles = {"CLINICIAN"})
    public void testClinicianSeesListOfDonorsWaitingForDecision() throws Exception {
        // Mock the donor service to return a list of donors waiting for a decision
        Donor donor1 = new Donor();
        donor1.setDonorId(1);
        donor1.setFirstName("John");
        donor1.setLastName("Doe");
        donor1.setDonorDecision(null);
        donor1.setDonorNote(null);

        Donor donor2 = new Donor();
        donor2.setDonorId(2);
        donor2.setFirstName("Jane");
        donor2.setLastName("Smith");
        donor2.setDonorDecision(null);
        donor2.setDonorNote(null);

        when(donorService.getAllDonors()).thenReturn(List.of(donor1, donor2));

        // Perform the request as a clinician
        MvcResult result = mvc.perform(get("/donor-records").with(user("clinician").roles("CLINICIAN")))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Verify that the list of donors waiting for a decision is displayed
        assertThat(content).contains("<td>John</td>");
        assertThat(content).contains("<td>Doe</td>");
        assertThat(content).contains("<td>Jane</td>");
        assertThat(content).contains("<td>Smith</td>");
        assertThat(content).contains("<td>Pending</td>");
    }

    @Test
    @WithMockUser(username = "clinician", roles = {"CLINICIAN"})
    public void testClinicianViewsDonorSummaries() throws Exception {
        // Mock the donor service to return a list of donors
        Donor donor1 = new Donor();
        donor1.setDonorId(1);
        donor1.setFirstName("John");
        donor1.setLastName("Doe");
        donor1.setDonorDob(LocalDate.of(1990, 1, 1));
        donor1.setDonorDecision(null);
        donor1.setDonorNote(null);

        Donor donor2 = new Donor();
        donor2.setDonorId(2);
        donor2.setFirstName("Jane");
        donor2.setLastName("Smith");
        donor2.setDonorDob(LocalDate.of(1985, 5, 15));
        donor2.setDonorDecision(null);
        donor2.setDonorNote(null);

        when(donorService.getAllDonors()).thenReturn(List.of(donor1, donor2));

        // Perform the request as a clinician to view donor records
        MvcResult result = mvc.perform(get("/donor-records").with(user("clinician").roles("CLINICIAN")))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the list of donors waiting for a decision is displayed
        String content = result.getResponse().getContentAsString();
        assertThat(content).contains("<td>John</td>");
        assertThat(content).contains("<td>Doe</td>");
        assertThat(content).contains("<td>Jane</td>");
        assertThat(content).contains("<td>Smith</td>");
        assertThat(content).contains("<td>Pending</td>");

        // Mock the donor service to return a specific donor summary
        when(donorService.getDonorSummary(1)).thenReturn(donor1);
        when(questionnaireService.getDonorsAnswers(1)).thenReturn(List.of());
        when(questionService.getQuestions()).thenReturn(List.of());

        // Perform the request as a clinician to view the donor summary
        MvcResult summaryResult = mvc.perform(get("/donor/summary/1").with(user("clinician").roles("CLINICIAN")))
                .andExpect(status().isOk())
                .andReturn();

        String summaryContent = summaryResult.getResponse().getContentAsString();

        // Verify that the summary of the donor is displayed
        assertThat(summaryContent).contains("<span>John Doe</span>");
        assertThat(summaryContent).contains("<span>1990-01-01</span>");
        assertThat(summaryContent).contains("<span class=\"decision\">Awaiting decision</span>");
    }


    @Test
    @WithMockUser(username = "clinician", roles = {"CLINICIAN"})
    public void testClinicianMakesDecisionForDonors() throws Exception {
        // Arrange: Set up a mock donor object and mock the service response
        Donor donor1 = new Donor();
        donor1.setDonorId(1);
        donor1.setFirstName("John");
        donor1.setLastName("Doe");
        donor1.setDonorDob(LocalDate.of(1990, 1, 1));
        donor1.setDonorDecision(null);
        donor1.setDonorNote(null);

        when(donorService.getAllDonors()).thenReturn(List.of(donor1));

        // Act: Perform a GET request to view donor records
        MvcResult donorRecordsResult = mvc.perform(get("/donor-records").with(user("clinician").roles("CLINICIAN")))
                .andExpect(status().isOk())
                .andReturn();

        // Assert: Verify that the list of donors waiting for a decision is displayed
        String donorRecordsContent = donorRecordsResult.getResponse().getContentAsString();
        assertThat(donorRecordsContent).contains("<td>John</td>");
        assertThat(donorRecordsContent).contains("<td>Doe</td>");
        assertThat(donorRecordsContent).contains("<td>Pending</td>");

        // Mock the donor service to return a specific donor summary
        when(donorService.getDonorSummary(1)).thenReturn(donor1);
        when(questionnaireService.getDonorsAnswers(1)).thenReturn(List.of());
        when(questionService.getQuestions()).thenReturn(List.of());

        // Act: Perform a GET request to view the donor summary
        MvcResult donorSummaryResult = mvc.perform(get("/donor/summary/1").with(user("clinician").roles("CLINICIAN")))
                .andExpect(status().isOk())
                .andReturn();

        // Assert: Verify that the summary of the donor is displayed
        String donorSummaryContent = donorSummaryResult.getResponse().getContentAsString();
        assertThat(donorSummaryContent).contains("<span>John Doe</span>");
        assertThat(donorSummaryContent).contains("<span>1990-01-01</span>");
        assertThat(donorSummaryContent).contains("<span class=\"decision\">Awaiting decision</span>");

        // Mock the DecisionAndNotesService to return a specific donor details
        DonorDetails donorDetails = new DonorDetails();
        donorDetails.setDonorId(1);
        donorDetails.setFirstName("John");
        donorDetails.setLastName("Doe");
        donorDetails.setDonorDecision("Eligible to donate today");
        donorDetails.setDonorNote("This is a test note");

        when(decisionAndNotesService.getDonorInfo(1)).thenReturn(donorDetails);

        // Act: Perform a GET request to view the decision and notes page
        MvcResult decisionAndNotesResult = mvc.perform(get("/decisionAndNotes/1").with(user("clinician").roles("CLINICIAN")))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult postMapping = mvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "Valid note")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/confirmation?donorId=1"))
                .andReturn();

        // Verify the service method was called with correct parameters
        Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(1, "Eligible to donate today", "Valid note", "clinician");
    }


}
