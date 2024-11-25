package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.QuestionnaireService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionService;
import java.time.LocalDate;
import java.util.Collections;
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

    @MockBean
    private DonorService donorService;

    @MockBean
    private QuestionnaireService questionnaireService;

    @MockBean
    private QuestionService questionService;

    /**
     * This method tests getting a donor with no travel history and no relevant diseases.
     * @throws Exception if the test fails
     */

    @Test
    public void shouldGetDonorInfoWithNoRisks() throws Exception {
        // Create a donor with no travel history
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1985, 6, 15));

        // Mock the donor repository to return the donor
        when(donorService.getDonorSummary(1)).thenReturn(donor);

        // Mock the donor service to return no relevant diseases
        when(donorService.getRelevantDiseasesForDonor(1)).thenReturn(Collections.emptyList());

        // Mock the questionnaire service to return donor's answers
        when(questionnaireService.getDonorsAnswers(1)).thenReturn(Collections.emptyList());

        // Mock the question service to return questions
        when(questionService.getQuestions()).thenReturn(Collections.emptyList());

        MvcResult result = mvc.perform(get("/donor/summary-risks/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("This donor has not travelled to a country at the time of any disease outbreak."));
        assertTrue(content.contains("John Doe"));
    }
}