package uk.ac.cf.spring.welshbloodserviceteam15.Navigation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes.DecisionAndNotesController;
import uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes.DecisionAndNotesService;
import uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes.DonorDetails;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.*;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questionnaire;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.QuestionnaireService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;
import uk.ac.cf.spring.welshbloodserviceteam15.Security.LoginController;
import uk.ac.cf.spring.welshbloodserviceteam15.travelform.*;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NavigationTests {

    @InjectMocks
    private DonorController donorController;

    @InjectMocks
    private TravelFormController travelFormController;

    @InjectMocks
    private DecisionAndNotesController decisionAndNotesController;

    @Autowired
    private MockMvc mvc;

    @Mock
    private DonorRepository donorRepository;

    @Mock
    private DonorDonorTravelledToCountryService donorTravelledToCountryService;

    @Mock
    private QuestionnaireService questionnaireService;

    @Mock
    private DonorService donorService;

    @Mock
    private QuestionService questionService;

    @Mock
    private DecisionAndNotesService decisionAndNotesService;

    @Mock
    private CountryService countryService;






    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(donorController, travelFormController, decisionAndNotesController).build();
    }

    // Test the home page and checking if when Check Eligibility button is clicked, it redirects to the Donors Form Page
    @Test
    public void testCheckEligibilityLink() throws Exception {
        // Given a user has pressed on check eligibility on the home page,
        mvc.perform(MockMvcRequestBuilders.get("/donor-form"))
                // When pressed it should,
                .andExpect(status().isOk())
                // Then transfer the user to the Donor's Form page.
                .andExpect(view().name("Donor/donor-form"));
    }

    @Test
    public void testDonorFormSubmissionRedirectsToTravelForm() throws Exception {
        // Given a user has pressed on the Submit Button on the Donor's Form page,
        Donor mockDonor = new Donor();
        mockDonor.setDonorId(6); // Example donor ID
        when(donorService.saveDonor(any(DonorForm.class))).thenReturn(mockDonor);

        mvc.perform(post("/donor-form")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("donorDob", "1990-01-01")
                        .with(csrf()))
                // When pressed it should,
                .andExpect(status().is3xxRedirection())
                // Then transfer the user to the Travel Form page.
                .andExpect(redirectedUrl("/travel-form/6"));
    }

    @Test
    @WithMockUser(username = "clinician", roles = {"CLINICIAN"})
    public void testNavigationFromTravelFormToTheConfirmationPage() throws Exception {
        // Given a user has pressed on the Submit Button on the Travel Form page,
        Donor mockDonor = new Donor();
        mockDonor.setDonorId(6); // Example donor ID
        mockDonor.setFirstName("John");
        mockDonor.setLastName("Doe");
        mockDonor.setDonorDob(LocalDate.of(1990, 1, 1));
        when(donorService.getDonorSummary(6)).thenReturn(mockDonor); // Mocking donor retrieval

        List<Integer> questionIds = List.of(1, 4);
        String answer = "Yes";
        List<Questionnaire> mockAnswers = questionIds.stream()
                .map(questionId -> {
                    Questionnaire questionnaire = new Questionnaire();
                    questionnaire.setDonorId(6);
                    questionnaire.setQuestionId(questionId);
                    questionnaire.setAnswer(answer);
                    return questionnaire;
                })
                .collect(Collectors.toList());
        when(questionnaireService.getDonorsAnswers(6)).thenReturn(mockAnswers);

        List<Questions> mockQuestions = questionIds.stream()
                .map(questionId -> {
                    Questions question = new Questions();
                    question.setQuestion_id(questionId.intValue());
                    question.setQuestion("Question " + questionId);
                    return question;
                })
                .collect(Collectors.toList());
        when(questionService.getQuestions()).thenReturn(mockQuestions);

        List<Country> mockCountries = List.of(
                new Country() {{
                    setCountryId(1);
                    setCountryName("Argentina");
                }},
                new Country() {{
                    setCountryId(2);
                    setCountryName("Brazil");
                }},
                new Country() {{
                    setCountryId(3);
                    setCountryName("Canada");
                }}
        );
        when(countryService.getAllCountries()).thenReturn(mockCountries);

        mvc.perform(get("/travel-form/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("travelform/travelForm"))
                .andExpect(model().attributeExists("questions", "countries", "donorId", "donorTravelledToCountries"));

        MvcResult submitFormResult = mvc.perform(post("/submit-travel-form")
                        .param("donorId", "6")
                        .param("question1", "Yes")
                        .param("question4", "Yes")
                        .param("country", "1")
                        .param("date", "2024-11-04")
                        .with(csrf()))
                // When pressed it should,
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                // Then transfer the user to the Donor confirmation page.
                .andExpect(redirectedUrl("/donorFormConfirmation?donorId=6&donorId=6"))
                .andReturn();
    }

    @Test
    public void testNavigationFromDonorSummaryToDecisionAndNotes() throws Exception {
        // Given a user has pressed on the View Decision and Notes button,
        Donor mockDonor = new Donor();
        mockDonor.setDonorId(6); // Example donor ID
        mockDonor.setFirstName("John");
        mockDonor.setLastName("Doe");
        mockDonor.setDonorDob(LocalDate.of(1990, 1, 1));
        when(donorService.getDonorSummary(6)).thenReturn(mockDonor); // Mocking donor retrieval

        Long donorId = 6L;
        List<Integer> questionIds = List.of(1, 4);
        String answer = "Yes";
        List<Questionnaire> mockAnswers = questionIds.stream()
                .map(questionId -> {
                    Questionnaire questionnaire = new Questionnaire();
                    questionnaire.setDonorId(6);
                    questionnaire.setQuestionId(questionId);
                    questionnaire.setAnswer(answer);
                    return questionnaire;
                })
                .collect(Collectors.toList());
        when(questionnaireService.getDonorsAnswers(6)).thenReturn(mockAnswers);

        List<Questions> mockQuestions = questionIds.stream()
                .map(questionId -> {
                    Questions question = new Questions();
                    question.setQuestion_id(questionId.intValue());
                    question.setQuestion("Question " + questionId);
                    return question;
                })
                .collect(Collectors.toList());
        when(questionService.getQuestions()).thenReturn(mockQuestions);

        DonorDetails mockDonorDetails = new DonorDetails();
        mockDonorDetails.setDonorId(6);
        mockDonorDetails.setFirstName("John");
        mockDonorDetails.setLastName("Doe");
        when(decisionAndNotesService.getDonorInfo(6)).thenReturn(mockDonorDetails);

        mvc.perform(get("/donor/summary/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("Donor/donorSummary"))
                .andExpect(model().attribute("donor", mockDonor))
                .andExpect(model().attribute("donorsAnswers", mockAnswers));

        MvcResult decisionAndNotesResult = mvc.perform(get("/decisionAndNotes/6")
                        .param("donorId", "6"))
                // When pressed it should,
                .andDo(print())
                .andExpect(status().isOk())
                // Then transfer the user to the Decision And Notes page.
                .andExpect(view().name("DecisionAndNotes/decisionAndNotesPage"))
                .andExpect(model().attribute("donor", mockDonorDetails))
                .andReturn();
    }

}







