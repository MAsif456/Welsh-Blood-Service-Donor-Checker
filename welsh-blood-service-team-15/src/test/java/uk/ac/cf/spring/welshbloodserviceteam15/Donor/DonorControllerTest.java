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
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Disease;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.DiseaseRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.QuestionnaireService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    private DiseaseRepository diseaseRepository;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private CountryDiseaseRepository countryDiseaseRepository;


    @Test
    public void shouldGetDonorInfoWithNoRisks() throws Exception {
        // Given: A donor who has not travelled to any countries
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1985, 6, 15));

        // Create multiple countries with active diseases
        List<Country> countries = Arrays.asList(
                new Country(),
                new Country(),
                new Country()
        );
        countries.get(0).setCountryName("Country1");
        countries.get(1).setCountryName("Country2");
        countries.get(2).setCountryName("Country3");
        countryRepository.saveAll(countries);

        Disease disease = new Disease();
        disease.setDiseaseName("Disease");
        diseaseRepository.save(disease);

        countries.forEach(country -> {
            CountryDisease countryDisease = new CountryDisease();
            countryDisease.setCountry(country);
            countryDisease.setDisease(disease);
            countryDisease.setStartDate(LocalDate.of(2022, 1, 1));
            countryDisease.setEndDate(LocalDate.of(2022, 12, 31));
            countryDiseaseRepository.save(countryDisease);
        });

        // Mock the donor repository to return the donor
        when(donorService.getDonorSummary(1)).thenReturn(donor);

        // Mock the donor service to return no relevant diseases
        when(donorService.getRelevantDiseasesForDonor(1)).thenReturn(Collections.emptyList());

        // Mock the questionnaire service to return donor's answers
        when(questionnaireService.getDonorsAnswers(1)).thenReturn(Collections.emptyList());

        // Mock the question service to return questions
        when(questionService.getQuestions()).thenReturn(Collections.emptyList());

        // When we check for relevant country diseases for the donor,
        MvcResult result = mvc.perform(get("/donor/summary-risks/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // Then: The response should contain a message that there are no risks
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("This donor has not travelled to a country at the time of any disease outbreak."));
    }
}