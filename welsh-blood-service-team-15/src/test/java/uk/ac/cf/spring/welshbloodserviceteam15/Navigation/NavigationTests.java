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

    @Mock
    private TravelQuestionnaireService travelQuestionnaireService;




    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(donorController, travelFormController, decisionAndNotesController).build();
    }

    // Test the home page and checking if when Check Eligibility button is clicked, it redirects to the Donors Form Page
    @Test
    public void testCheckEligibilityLink() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/donor-form"))
                // Assert: Verify the response
                .andExpect(status().isOk())
                .andExpect(view().name("Donor/donor-form"));
    }

    // I will now test the navigation from the Donor Form Page to the Travel Form Page


    @Test
    public void testDonorFormSubmissionRedirectsToTravelForm() throws Exception {
        // Arrange
        Donor mockDonor = new Donor();
        when(donorRepository.save(any(Donor.class))).thenAnswer(invocation -> {
            Donor donor = invocation.getArgument(0);
            donor.setDonorId(6);
            return donor;
        });

        // Act & Assert
        mvc.perform(post("/donor-form")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("donorDob", "1990-01-01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/travel-form/6"));
    }

    // I will now test the navigation from the Travel Form Page to the Donor Summary Page


    @Test
    @WithMockUser(username = "clinician", roles = {"CLINICIAN"})
    public void testNavigationFromTravelFormToDonorSummary() throws Exception {
        // Mock Donor
        Donor mockDonor = new Donor();
        mockDonor.setDonorId(6); // Example donor ID
        mockDonor.setFirstName("John");
        mockDonor.setLastName("Doe");
        mockDonor.setDonorDob(LocalDate.of(1990, 1, 1));
        when(donorService.getDonorSummary(6)).thenReturn(mockDonor); // Mocking donor retrieval

        // Mock Donor's Answers
        Long donorId = 6L;
        List<Integer> questionIds = List.of(1, 4);
        String answer = "Yes";
        List<Questionnaire> mockAnswers = questionIds.stream()
                .map(questionId -> {
                    Questionnaire questionnaire = new Questionnaire();
                    Questions question = new Questions();
                    question.setQuestionId(questionId);
                    questionnaire.setQuestion(question);
                     questionnaire.setDonor(mockDonor);
                    questionnaire.setAnswer(answer);
                    return questionnaire;
                })
                .collect(Collectors.toList());
        when(questionnaireService.getDonorsAnswers(6)).thenReturn(mockAnswers);

        // Mock Questions
        List<Questions> mockQuestions = questionIds.stream()
                .map(questionId -> {
                    Questions question = new Questions();
                    question.setQuestionId(questionId.intValue());
                    question.setQuestion("Question " + questionId);
                    return question;
                })
                .collect(Collectors.toList());
        when(questionService.getQuestions()).thenReturn(mockQuestions);

        // Mock Countries
        List<Country> mockCountries = List.of(
                new Country() {{ setCountryId(1); setCountryName("Argentina"); }},
                new Country() {{ setCountryId(2); setCountryName("Brazil"); }},
                new Country() {{ setCountryId(3); setCountryName("Canada"); }}
        );
        when(countryService.getAllCountries()).thenReturn(mockCountries);

        // Step 1: Access Travel Form Page
        mvc.perform(get("/travel-form/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("travelform/travelForm"))
                .andExpect(model().attributeExists("questions", "countries", "donorId", "donorTravelledToCountries"));

        // Step 2: Submit Travel Form
        MvcResult submitFormResult = mvc.perform(post("/submit-travel-form")
                        .param("donorId", "6")
                        .param("question1", "Yes")
                        .param("question4", "Yes")
                        .param("country", "1")
                        .param("date", "2024-11-04")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/travel-form/6"))
                .andReturn();

        // Step 3: Access Donor Summary Page
        mvc.perform(get("/donor/summary/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("Donor/donorSummary"))
                .andExpect(model().attribute("donor", mockDonor))
                .andExpect(model().attribute("donorsAnswers", mockAnswers));
    }

// I will now test the navigation from the Donor Summary Page to the Decision and Notes Page
@Test
public void testNavigationFromDonorSummaryToDecisionAndNotes() throws Exception {
    // Mock Donor
    Donor mockDonor = new Donor();
    mockDonor.setDonorId(6); // Example donor ID
    mockDonor.setFirstName("John");
    mockDonor.setLastName("Doe");
    mockDonor.setDonorDob(LocalDate.of(1990, 1, 1));
    when(donorService.getDonorSummary(6)).thenReturn(mockDonor); // Mocking donor retrieval

    // Mock Donor's Answers
    Long donorId = 6L;
    List<Integer> questionIds = List.of(1, 4);
    String answer = "Yes";
    List<Questionnaire> mockAnswers = questionIds.stream()
            .map(questionId -> {
                Questionnaire questionnaire = new Questionnaire();
                Questions question = new Questions();
                question.setQuestionId(questionId);
                questionnaire.setQuestion(question);
                 questionnaire.setDonor(mockDonor);
                questionnaire.setAnswer(answer);
                return questionnaire;
            })
            .collect(Collectors.toList());
    when(questionnaireService.getDonorsAnswers(6)).thenReturn(mockAnswers);

    // Mock Questions
    List<Questions> mockQuestions = questionIds.stream()
            .map(questionId -> {
                Questions question = new Questions();
                question.setQuestionId(questionId.intValue());
                question.setQuestion("Question " + questionId);
                return question;
            })
            .collect(Collectors.toList());
    when(questionService.getQuestions()).thenReturn(mockQuestions);

    // Mock Donor Details for Decision and Notes
    DonorDetails mockDonorDetails = new DonorDetails();
    mockDonorDetails.setDonorId(6);
    mockDonorDetails.setFirstName("John");
    mockDonorDetails.setLastName("Doe");
    when(decisionAndNotesService.getDonorInfo(6)).thenReturn(mockDonorDetails);

    // Step 1: Access Donor Summary Page
    mvc.perform(get("/donor/summary/6"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("Donor/donorSummary"))
            .andExpect(model().attribute("donor", mockDonor))
            .andExpect(model().attribute("donorsAnswers", mockAnswers));

    // Step 2: Navigate to Decision and Notes Page
    MvcResult decisionAndNotesResult = mvc.perform(get("/decisionAndNotes/6")
                    .param("donorId", "6"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("DecisionAndNotes/decisionAndNotesPage"))
            .andExpect(model().attribute("donor", mockDonorDetails))
            .andReturn();
}

    // I will now test the navigation from the Decision and Notes Page to the Confirmation Page
    //This has been completed in the DecisionAndNotesTests.java file
}







