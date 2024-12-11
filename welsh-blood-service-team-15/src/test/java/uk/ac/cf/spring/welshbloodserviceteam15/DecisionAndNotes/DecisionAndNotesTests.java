package uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DecisionAndNotesTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DecisionAndNotesService decisionAndNotesService;

    @BeforeEach
    public void setup() {
        // Any common setup can be done here
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void CheckIfDonorIsReceivedOntoPage() throws Exception {
        // Given a staff member is filling out the decision and notes form,
        DonorDetails mockDonor = new DonorDetails(
                3,
                "John",
                "Doe",
                LocalDate.of(1985, 4, 12),
                null,
                null,
                null
        );
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        // When the staff member navigates to the decision and notes page,
        mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"))
                // Then the form should display the donor's information.
                .andExpect(status().isOk())
                .andExpect(view().name("DecisionAndNotes/decisionAndNotesPage"))
                .andExpect(model().attributeExists("donor"))
                .andExpect(model().attribute("donor", Matchers.hasProperty("donorId", Matchers.equalTo(3))))
                .andExpect(model().attribute("donor", Matchers.hasProperty("firstName", Matchers.equalTo("John"))))
                .andExpect(model().attribute("donor", Matchers.hasProperty("lastName", Matchers.equalTo("Doe"))));

        Mockito.verify(decisionAndNotesService).getDonorInfo(1);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void CheckIfDonorIsUpdated() throws Exception {
        // Given the user has selected a decision and written a note,
        DonorDetails mockDonor = new DonorDetails(
                1,
                "John",
                "Cena",
                LocalDate.of(1985, 4, 12),
                null,
                null,
                null
        );
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"));

        // When they submit the form,
        mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "Donor has been cleared for donation")
                        .with(csrf()))
                // Then the system should save the final decision and note to the donor's record.
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/confirmation?donorId=" + mockDonor.getDonorId()));

        Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(1, "Eligible to donate today", "Donor has been cleared for donation", "admin");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testValidDecisionOptionsAndNotes() throws Exception {
        // Given a staff member is filling out the decision and notes form,
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), null, null, null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        // When the staff member submits the form with valid data,
        mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "Valid note")
                        .with(csrf()))
                // Then the data should be sent to the database without any inconsistencies.
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/confirmation?donorId=1"));

        Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(1, "Eligible to donate today", "Valid note", "admin");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testNoteValidationIfItsEmpty() throws Exception {
        // Given a staff member is filling out the decision and notes form,
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), "Eligible to donate today", "", null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("DecisionAndNotes/decisionAndNotesPage"))
                .andExpect(model().attributeExists("donor"))
                .andExpect(model().attribute("donor", Matchers.hasProperty("donorId", Matchers.equalTo(1))));

        // When the staff member enters a note,
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "")
                        .with(csrf()))
                // Then the form should validate that the note is not empty and does not exceed a specified character limit.
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("Please enter a note between 10 - 500 characters"));
        assertTrue(content.contains("Please enter a note"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testNoteValidationIfItsOverCharacterCount() throws Exception {
        // Given a staff member is filling out the decision and notes form,
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), "Eligible to donate today", "", null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("DecisionAndNotes/decisionAndNotesPage"))
                .andExpect(model().attributeExists("donor"))
                .andExpect(model().attribute("donor", Matchers.hasProperty("donorId", Matchers.equalTo(1))));

        // When the staff member enters a note,
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "a".repeat(501))
                        .with(csrf()))
                // Then the form should validate that the note is not empty and does not exceed a specified character limit.
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("Please enter a note between 10 - 500 characters"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testValidDataSubmission() throws Exception {
        // Given a staff member is filling out the decision and notes form,
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), null, null, null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        // When the staff member submits the form with valid data,
        mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "Valid note")
                        .with(csrf()))
                // Then the data should be sent to the database without any inconsistencies.
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/confirmation?donorId=1"));

        Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(1, "Eligible to donate today", "Valid note", "admin");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDecisionValidation() throws Exception {
        // Given a staff member is filling out the decision and notes form,
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), "Eligible to donate today", "", null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("DecisionAndNotes/decisionAndNotesPage"))
                .andExpect(model().attributeExists("donor"))
                .andExpect(model().attribute("donor", Matchers.hasProperty("donorId", Matchers.equalTo(1))));

        // When the staff member enters a decision,
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "")
                        .param("donorNote", "Valid note")
                        .with(csrf()))
                // Then the form should validate that the decision is one of the predefined valid options.
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("Please enter a decision"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testRemoveErrorMessagesOnCorrection() throws Exception {
        // Given a staff member is filling out the decision and notes form,
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), "Eligible to donate today", "", null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        // When the staff member enters invalid data,
        MvcResult resultWithErrors = mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "")
                        .with(csrf()))
                // Then the form should display appropriate error messages indicating the validation issues.
                .andReturn();

        String contentWithErrors = resultWithErrors.getResponse().getContentAsString();
        assertTrue(contentWithErrors.contains("Please enter a note between 10 - 500 characters"));
        assertTrue(contentWithErrors.contains("Please enter a note"));

        // When the staff member corrects the invalid data,
        MvcResult resultWithoutErrors = mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "Valid note")
                        .with(csrf()))
                // Then the form should remove the error messages and allow the form to be submitted successfully.
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/confirmation?donorId=1"))
                .andReturn();

        String contentWithoutErrors = resultWithoutErrors.getResponse().getContentAsString();
        assertFalse(contentWithoutErrors.contains("Please enter a note between 10 - 500 characters"));
        assertFalse(contentWithoutErrors.contains("Please enter a note"));

        Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(1, "Eligible to donate today", "Valid note", "admin");
    }
}