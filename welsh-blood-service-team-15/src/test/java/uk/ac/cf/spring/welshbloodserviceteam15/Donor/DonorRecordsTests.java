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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @WithMockUser(username = "staff", roles = {"STAFF"})
    public void testViewDonorProfile() throws Exception {
        // Given a staff member has access to the donor management system,
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        when(donorService.getDonorSummary(1)).thenReturn(donor);
        when(questionnaireService.getDonorsAnswers(1)).thenReturn(List.of());
        when(questionService.getQuestions()).thenReturn(List.of());

        // When they navigate to the desired donor's profile,
        MvcResult result = mvc.perform(get("/donor/summary/1"))
                .andExpect(status().isOk())
                // Then they should be able to view the donor's previous records and submissions.
                .andExpect(view().name("Donor/donorSummary"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("<span>John Doe</span>"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testSearchNonExistentDonor() throws Exception {
        // Given a staff member is logged in,
        when(donorService.searchDonorById(1)).thenReturn(null);

        // When the admin searches for a donor with an ID that does not exist,
        MvcResult result = mvc.perform(get("/search-donor").param("id", "1"))
                .andExpect(status().isOk())
                // Then an appropriate error message should be displayed.
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("No donor found with the provided ID."));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testSearchDonorWithoutId() throws Exception {
        // Given a staff member is logged in,
        // When the admin navigates to the search donor page without providing an ID,
        MvcResult result = mvc.perform(get("/search-donor"))
                .andExpect(status().isOk())
                // Then an appropriate error message should be displayed.
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("An ID is required to search."));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminSeesDecisionMadeByColumn() throws Exception {
        // Given an admin is logged in
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        donor.setDecisionMade("admin");
        when(donorService.getAllDonors()).thenReturn(List.of(donor));

        // When the admin navigates to the donor records page
        MvcResult result = mvc.perform(get("/donor-records"))
                .andExpect(status().isOk())
                // Then the admin should see the "Decision Made By" column in the donor records table
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("<th>Decision Made By</th>"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testNonAdminDoesNotSeeDecisionMadeByColumn() throws Exception {
        // Given a non-admin user is logged in
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        when(donorService.getAllDonors()).thenReturn(List.of(donor));

        // When the user navigates to the donor records page
        MvcResult result = mvc.perform(get("/donor-records"))
                .andExpect(status().isOk())
                // Then the "Decision Made By" column should not be visible in the donor records table.
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertFalse(content.contains("<th>Decision Made By</th>"));
    }

    @Test
    @WithMockUser(username = "clinician", roles = {"CLINICIAN"})
    public void testClinicianSeesListOfDonorsWaitingForDecision() throws Exception {
        // Given a clinician is logged in,
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

        // When the clinician navigates to the donor records page,
        MvcResult result = mvc.perform(get("/donor-records"))
                .andExpect(status().isOk())
                // Then the clinician should see a list of donors that are waiting for a decision.
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).contains("<td>John</td>");
        assertThat(content).contains("<td>Doe</td>");
        assertThat(content).contains("<td>Jane</td>");
        assertThat(content).contains("<td>Smith</td>");
        assertThat(content).contains("<td>Pending</td>");
    }

    @Test
    @WithMockUser(username = "clinician", roles = {"CLINICIAN"})
    public void testClinicianViewsDonorSummaries() throws Exception {
        // Given a clinician is logged in,
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

        // When the clinician views the donor records page,
        MvcResult result = mvc.perform(get("/donor-records"))
                .andExpect(status().isOk())
                // Then the clinician should be able to view the summary of each donor in the queue.
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).contains("<td>John</td>");
        assertThat(content).contains("<td>Doe</td>");
        assertThat(content).contains("<td>Jane</td>");
        assertThat(content).contains("<td>Smith</td>");
        assertThat(content).contains("<td>Pending</td>");

        when(donorService.getDonorSummary(1)).thenReturn(donor1);
        when(questionnaireService.getDonorsAnswers(1)).thenReturn(List.of());
        when(questionService.getQuestions()).thenReturn(List.of());

        MvcResult summaryResult = mvc.perform(get("/donor/summary/1"))
                .andExpect(status().isOk())
                .andReturn();

        String summaryContent = summaryResult.getResponse().getContentAsString();
        assertThat(summaryContent).contains("<span>John Doe</span>");
        assertThat(summaryContent).contains("<span>1990-01-01</span>");
        assertThat(summaryContent).contains("<span class=\"decision\">Awaiting decision</span>");
    }

    @Test
    @WithMockUser(username = "clinician", roles = {"CLINICIAN"})
    public void testClinicianMakesDecisionForDonors() throws Exception {
        // Given a clinician is logged in,
        Donor donor1 = new Donor();
        donor1.setDonorId(1);
        donor1.setFirstName("John");
        donor1.setLastName("Doe");
        donor1.setDonorDob(LocalDate.of(1990, 1, 1));
        donor1.setDonorDecision(null);
        donor1.setDonorNote(null);

        when(donorService.getAllDonors()).thenReturn(List.of(donor1));

        // When the clinician views the donor records page,
        MvcResult donorRecordsResult = mvc.perform(get("/donor-records"))
                .andExpect(status().isOk())
                // Then the clinician should be able to make a decision for each donor in the queue.
                .andReturn();

        String donorRecordsContent = donorRecordsResult.getResponse().getContentAsString();
        assertThat(donorRecordsContent).contains("<td>John</td>");
        assertThat(donorRecordsContent).contains("<td>Doe</td>");
        assertThat(donorRecordsContent).contains("<td>Pending</td>");

        when(donorService.getDonorSummary(1)).thenReturn(donor1);
        when(questionnaireService.getDonorsAnswers(1)).thenReturn(List.of());
        when(questionService.getQuestions()).thenReturn(List.of());

        MvcResult donorSummaryResult = mvc.perform(get("/donor/summary/1"))
                .andExpect(status().isOk())
                .andReturn();

        String donorSummaryContent = donorSummaryResult.getResponse().getContentAsString();
        assertThat(donorSummaryContent).contains("<span>John Doe</span>");
        assertThat(donorSummaryContent).contains("<span>1990-01-01</span>");
        assertThat(donorSummaryContent).contains("<span class=\"decision\">Awaiting decision</span>");

        DonorDetails donorDetails = new DonorDetails();
        donorDetails.setDonorId(1);
        donorDetails.setFirstName("John");
        donorDetails.setLastName("Doe");
        donorDetails.setDonorDecision("Eligible to donate today");
        donorDetails.setDonorNote("This is a test note");

        when(decisionAndNotesService.getDonorInfo(1)).thenReturn(donorDetails);

        MvcResult decisionAndNotesResult = mvc.perform(get("/decisionAndNotes/1"))
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

        Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(1, "Eligible to donate today", "Valid note", "clinician");
    }


}
