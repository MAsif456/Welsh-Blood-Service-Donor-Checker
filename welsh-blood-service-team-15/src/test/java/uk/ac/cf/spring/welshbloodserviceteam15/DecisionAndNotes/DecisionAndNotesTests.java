package uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes.DecisionAndNotesService;
import uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes.DonorDetails;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DecisionAndNotesTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DecisionAndNotesService decisionAndNotesService;

    @Test
    public void CheckIfDonorIsReceivedOntoPage() throws Exception {
            // Arrange: Set up a mock donor object and mock the service response
            DonorDetails mockDonor = new DonorDetails(
                    3,
                    "John",
                    "Doe",
                    LocalDate.of(1985, 4, 12),
                    "Low",
                    null,
                    null
            );
            Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

            // Act: Perform a GET request to the endpoint
            mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"))
                    // Assert: Verify the response
                    .andExpect(status().isOk())
                    .andExpect(view().name("decisionAndNotesPage"))
                    .andExpect(model().attributeExists("donor"))
                    .andExpect(model().attribute("donor", Matchers.hasProperty("donorId", Matchers.equalTo(3))))
                    .andExpect(model().attribute("donor", Matchers.hasProperty("firstName", Matchers.equalTo("John"))))
                    .andExpect(model().attribute("donor", Matchers.hasProperty("lastName", Matchers.equalTo("Doe"))));


            // Verify that the service method was called with the correct parameter
            Mockito.verify(decisionAndNotesService).getDonorInfo(1);
        }

        @Test
        public void CheckIfDonorIsUpdated() throws Exception {
            // Arrange: Set up a mock donor object and mock the service response
            DonorDetails mockDonor = new DonorDetails(
                    1,
                    "John",
                    "Cena",
                    LocalDate.of(1985, 4, 12),
                    "Low",
                    null,
                    null
            );
            Mockito.when(decisionAndNotesService.getDonorInfo(1)).thenReturn(mockDonor);

            // Act: Perform a GET request to retrieve the donor details
            mockMvc.perform(MockMvcRequestBuilders.get("/decisionAndNotes/1"));

            // Act: Perform a POST request to update the donor's decision and notes
            mockMvc.perform(MockMvcRequestBuilders.post("/submit-decisions")
                            .param("donorId", "1")
                            .param("decision", "Eligible to donate today")
                            .param("notes", "Donor has been cleared for donation"))
                    .andExpect(status().is3xxRedirection())

                    // Assert: Verify the response to be redirected to the confirmation page
                    .andExpect(redirectedUrl("/confirmation?donorId="+mockDonor.getDonorId()));

            // Assert: Verify that the service method was called with correct parameters
            Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(1, "Eligible to donate today", "Donor has been cleared for donation");

            // Assert: Additional verification of contents
            // I used ArgumentCaptor as this would allow us to capture an argument passed to a method to inspect it
            ArgumentCaptor<Integer> donorIdCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<String> decisionCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> notesCaptor = ArgumentCaptor.forClass(String.class);

            Mockito.verify(decisionAndNotesService).updateDonorDecisionAndNotes(
                    donorIdCaptor.capture(),
                    decisionCaptor.capture(),
                    notesCaptor.capture()
            );

            // Check captured values
            assertEquals(1, donorIdCaptor.getValue());
            assertEquals("Eligible to donate today", decisionCaptor.getValue());
            assertEquals("Donor has been cleared for donation", notesCaptor.getValue());
        }

    }


