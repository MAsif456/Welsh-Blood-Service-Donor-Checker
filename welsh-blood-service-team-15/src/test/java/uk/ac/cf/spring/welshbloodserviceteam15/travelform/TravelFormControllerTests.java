package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryService;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorDonorTravelledToCountryService;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountry;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TravelFormControllerTests {

    @Mock
    private QuestionService questionService;

    @Mock
    private CountryService countryService;

    @Mock
    private DonorDonorTravelledToCountryService donorTravelledToCountryService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private TravelFormController travelFormController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test to see if the travel form is displayed correctly
     */

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testShowTravelForm() {
        int donorId = 1;
        List<Questions> questions = Collections.emptyList();
        List<Country> countries = Collections.emptyList();
        List<DonorTravelledToCountry> donorTravelledToCountries = Collections.emptyList();

        when(questionService.getQuestions()).thenReturn(questions);
        when(countryService.getAllCountries()).thenReturn(countries);
        when(donorTravelledToCountryService.findByDonorId(donorId)).thenReturn(donorTravelledToCountries);

        ModelAndView mv = travelFormController.showTravelForm(donorId);

        assertEquals("travelform/travelForm", mv.getViewName());
        assertEquals(questions, mv.getModel().get("questions"));
        assertEquals(countries, mv.getModel().get("countries"));
        assertEquals(donorId, mv.getModel().get("donorId"));
        assertEquals(donorTravelledToCountries, mv.getModel().get("donorTravelledToCountries"));
    }

    /**
     * Test to see if the travel form is submitted correctly based on answer to question
     */

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testSubmitNewTravelForm_Question4Yes() {
        String donorId = "1";
        String question1 = "No";
        String question4 = "Yes";
        String countryId = "1";
        String date = "2024-12-10";

        when(redirectAttributes.addAttribute(anyString(), any())).thenReturn(redirectAttributes);

        String viewName = travelFormController.submitNewTravelForm(donorId, question1, question4, countryId, null, date, model, redirectAttributes);

        verify(donorTravelledToCountryService, never()).saveFromForm(1, 1, date);
        assertEquals("redirect:/travel-form/{donorId}", viewName);
    }

    @Autowired
    private TravelQuestionnaireRepository travelQuestionnaireRepository;



    /**
     * Test to see if the travel form is submitted correctly
     * into the database based on the answers to the questions
     * as there are different tables for general questions
     * and conditional questions.
     */

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void givenGeneralQuestions_whenSubmitTravelForm_thenDataIsSavedInTravelQuestionnaire() throws Exception {
        // Given
        Long donorId = 1L;
        List<Long> generalQuestionIds = List.of(1L, 4L);
        String answer = "Yes";

        // Simulate form submission by saving data directly to the repository
        for (Long questionId : generalQuestionIds) {
            TravelQuestionnaire questionnaire = new TravelQuestionnaire();
            questionnaire.setDonorId(donorId);
            questionnaire.setQuestionId(questionId);
            questionnaire.setAnswer(answer);
            travelQuestionnaireRepository.save(questionnaire);
        }

        // Then
        for (Long questionId : generalQuestionIds) {
            Optional<TravelQuestionnaire> savedData = travelQuestionnaireRepository.findByDonorIdAndQuestionId(donorId, questionId);
            assertThat(savedData).isPresent();
            assertThat(savedData.get().getAnswer()).isEqualTo(answer);
        }
    }
}