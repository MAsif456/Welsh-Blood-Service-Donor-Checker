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
        // Arrange: Set up a mock donor object and mock the service response
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

        // Act: Perform a GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"))
                // Assert: Verify the response
                .andExpect(status().isOk())
                .andExpect(view().name("DecisionAndNotes/decisionAndNotesPage"))
                .andExpect(model().attributeExists("donor"))
                .andExpect(model().attribute("donor", Matchers.hasProperty("donorId", Matchers.equalTo(3))))
                .andExpect(model().attribute("donor", Matchers.hasProperty("firstName", Matchers.equalTo("John"))))
                .andExpect(model().attribute("donor", Matchers.hasProperty("lastName", Matchers.equalTo("Doe"))));

        // Verify that the service method was called with the correct parameter
        Mockito.verify(decisionAndNotesService).getDonorInfo(1);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void CheckIfDonorIsUpdated() throws Exception {
        // Arrange: Set up a mock donor object and mock the service response
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

        // Act: Perform a GET request to retrieve the donor details
        mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"));

        // Act: Perform a POST request to update the donor's decision and notes
        mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "Donor has been cleared for donation")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/confirmation?donorId=" + mockDonor.getDonorId()));

        // Assert: Verify that the service method was called with correct parameters
        Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(1, "Eligible to donate today", "Donor has been cleared for donation", "admin");

        // Assert: Additional verification of contents
        // Use ArgumentCaptor to capture arguments passed to the method
        ArgumentCaptor<Integer> donorIdCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> decisionCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> notesCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(
                donorIdCaptor.capture(),
                decisionCaptor.capture(),
                notesCaptor.capture(),
                usernameCaptor.capture()
        );

        // Check captured values
        assertEquals(1, donorIdCaptor.getValue());
        assertEquals("Eligible to donate today", decisionCaptor.getValue());
        assertEquals("Donor has been cleared for donation", notesCaptor.getValue());
        assertEquals("admin", usernameCaptor.getValue());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testValidDecisionOptionsAndNotes() throws Exception {
        // Arrange: Set up a mock donor object
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), null, null, null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        // Act: Perform a POST request with a valid decision
        mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "Valid note")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/confirmation?donorId=1"));

        // Assert: Verify the service method was called with correct parameters
        Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(1, "Eligible to donate today", "Valid note", "admin");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testNoteValidationIfItsEmpty() throws Exception {
        // Arrange: Set up a mock donor object
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), "Eligible to donate today", "", null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        // Act: Perform a GET request to set up the donor object in the model
        mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("DecisionAndNotes/decisionAndNotesPage"))
                .andExpect(model().attributeExists("donor"))
                .andExpect(model().attribute("donor", Matchers.hasProperty("donorId", Matchers.equalTo(1))));

        // Act: Perform a POST request with an invalid note (empty)
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "")
                        .with(csrf()))
                .andReturn();

        // Assert: Verify the error messages in the response content
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("Please enter a note between 10 - 500 characters"));
        assertTrue(content.contains("Please enter a note"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testNoteValidationIfItsOverCharacterCount() throws Exception {
        // Arrange: Set up a mock donor object
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), "Eligible to donate today", "", null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        // Act: Perform a GET request to set up the donor object in the model
        mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("DecisionAndNotes/decisionAndNotesPage"))
                .andExpect(model().attributeExists("donor"))
                .andExpect(model().attribute("donor", Matchers.hasProperty("donorId", Matchers.equalTo(1))));

        // Act: Perform a POST request with a note that is over the character limit
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "a".repeat(501))
                        .with(csrf()))
                .andReturn();

        // Assert: Verify the error messages in the response content
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("Please enter a note between 10 - 500 characters"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testValidDataSubmission() throws Exception {
        // Arrange: Set up a mock donor object
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), null, null, null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        // Act: Perform a POST request with valid data
        mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "Valid note")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/confirmation?donorId=1"));

        // Assert: Verify the service method was called with correct parameters
        Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(1, "Eligible to donate today", "Valid note", "admin");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDecisionValidation() throws Exception {
        // Arrange: Set up a mock donor object
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), "Eligible to donate today", "", null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        // Act: Perform a GET request to set up the donor object in the model
        mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("DecisionAndNotes/decisionAndNotesPage"))
                .andExpect(model().attributeExists("donor"))
                .andExpect(model().attribute("donor", Matchers.hasProperty("donorId", Matchers.equalTo(1))));

        // Act: Perform a POST request with an unselected decision
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "")
                        .param("donorNote", "Valid note")
                        .with(csrf()))
                .andReturn();

        // Assert: Verify the error messages in the response content
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("Please enter a decision"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testRemoveErrorMessagesOnCorrection() throws Exception {
        // Arrange: Set up a mock donor object
        DonorDetails mockDonor = new DonorDetails(1, "John", "Doe", LocalDate.of(1985, 4, 12), "Eligible to donate today", "", null);
        Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

        // Act: Perform a POST request with an invalid note (empty)
        MvcResult resultWithErrors = mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "")
                        .with(csrf()))
                .andReturn();

        // Assert: Verify the error messages in the response content
        String contentWithErrors = resultWithErrors.getResponse().getContentAsString();
        assertTrue(contentWithErrors.contains("Please enter a note between 10 - 500 characters"));
        assertTrue(contentWithErrors.contains("Please enter a note"));

        // Act: Perform a POST request with a valid note
        MvcResult resultWithoutErrors = mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                        .param("donorId", "1")
                        .param("donorDecision", "Eligible to donate today")
                        .param("donorNote", "Valid note")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/confirmation?donorId=1"))
                .andReturn();

        // Assert: Verify that the error messages are not present in the response content
        String contentWithoutErrors = resultWithoutErrors.getResponse().getContentAsString();
        assertFalse(contentWithoutErrors.contains("Please enter a note between 10 - 500 characters"));
        assertFalse(contentWithoutErrors.contains("Please enter a note"));

        // Verify the service method was called with correct parameters
        Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(1, "Eligible to donate today", "Valid note", "admin");
    }
}