package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseDTO;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Disease;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.QuestionnaireService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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

    @MockBean
    private DonorService donorService;

    @MockBean
    private QuestionnaireService questionnaireService;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private CountryDiseaseRepository countryDiseaseRepository;

    /**
     * This test verifies the behavior when a donor has not travelled to any country in the last 12 months,
     * and all other countries have active diseases.
     */
    @Test
    public void testDonorWithNoTravelHistoryAndOtherCountriesWithDiseases() throws Exception {
        // Given a donor with no travel history and all other countries have active diseases
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1985, 6, 15));

        // Mock the donor repository to return the donor
        when(donorService.getDonorSummary(1)).thenReturn(donor);

        // Mock the donor service to return no relevant diseases for this donor
        when(donorService.getRelevantDiseasesForDonor(1)).thenReturn(Collections.emptyList());

        // Mock other countries with active diseases using CountryDisease
        CountryDisease countryDisease1 = new CountryDisease();
        Country country1 = new Country();
        country1.setCountryName("Country1");
        Disease disease1 = new Disease();
        disease1.setDiseaseName("Disease1");
        countryDisease1.setCountry(country1);
        countryDisease1.setDisease(disease1);
        countryDisease1.setNote("Bad disease");
        countryDisease1.setStartDate(LocalDate.now().minusMonths(5));
        countryDisease1.setEndDate(LocalDate.now());

        CountryDisease countryDisease2 = new CountryDisease();
        Country country2 = new Country();
        country2.setCountryName("Country2");
        Disease disease2 = new Disease();
        disease2.setDiseaseName("Disease2");
        countryDisease2.setCountry(country2);
        countryDisease2.setDisease(disease2);
        countryDisease2.setNote("Bad disease");
        countryDisease2.setStartDate(LocalDate.now().minusMonths(5));
        countryDisease2.setEndDate(LocalDate.now());

        when(countryDiseaseRepository.findAll()).thenReturn(List.of(countryDisease1, countryDisease2));

        // Mock the questionnaire service to return donor's answers
        when(questionnaireService.getDonorsAnswers(1)).thenReturn(Collections.emptyList());

        // Mock the question service to return questions
        when(questionService.getQuestions()).thenReturn(Collections.emptyList());

        // When the endpoint is called
        MvcResult result = mvc.perform(get("/donor/summary-risks/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        // Then the response should contain the donor's name and a message that the donor has not travelled to any country
        // in the last 12 months, and the diseases from other countries should not be displayed
        assertTrue(content.contains("This donor has not travelled to any country in the last 12 months."));
        assertTrue(content.contains("John Doe"));
        assertFalse(content.contains("Country1"));
        assertFalse(content.contains("Disease1"));
        assertFalse(content.contains("Country2"));
        assertFalse(content.contains("Disease2"));
    }
}