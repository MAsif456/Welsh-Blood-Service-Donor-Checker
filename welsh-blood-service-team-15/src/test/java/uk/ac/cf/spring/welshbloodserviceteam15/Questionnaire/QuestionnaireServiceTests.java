package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionnaireServiceTests {

    @MockBean
    private QuestionnaireService questionnaireService;

    private Questionnaire questionnaire;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionRepository questionRepository;

    private Donor donor;
    private Questions question;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mock Donor and Question and Questionnaire
        donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1990, 1, 1));

        question = new Questions();
        question.setQuestion_id(1);
        question.setQuestion("Do you have any allergies?");

        questionnaire = new Questionnaire();
        questionnaire.setQuestionnaireId(1);
        questionnaire.setDonor(donor);
        questionnaire.setQuestion(question);
        questionnaire.setAnswer("No");
    }


    @Test
    public void shouldGetDonorsAnswers() {
        // Given
        // Get variables from setUp()
        when(questionnaireService.getDonorsAnswers(1)).thenReturn(List.of(questionnaire));

        // When
        List<Questionnaire> questionnaires = questionnaireService.getDonorsAnswers(1);

        // Then
        assertEquals(1, questionnaires.size());
        assertEquals("Do you have any allergies?", questionnaires.get(0).getQuestion().getQuestion());
        assertEquals("No", questionnaires.get(0).getAnswer());
        assertEquals(1, questionnaires.get(0).getQuestionnaireId());
        assertEquals(1, questionnaires.get(0).getDonor().getDonorId());
    }

    @Test
    public void shouldSaveFromForm() {
        // Given: mock the return values for donor and question
        when(donorRepository.findByDonorId(1)).thenReturn(donor);
        when(questionRepository.findByQuestionId(1)).thenReturn(question);

        // When:
        doNothing().when(questionnaireService).saveFromForm(1, 1, "No");
        questionnaireService.saveFromForm(1, 1, "No");

        // Then: verify that the save method was called on the repository
        verify(questionnaireRepository, times(1)).save(Mockito.any(Questionnaire.class));

        // Since saveFromForm doesn't return anything, we can check the state of the `questionnaire`
        assertEquals(1, questionnaire.getDonor().getDonorId());
        assertEquals(1, questionnaire.getQuestion().getQuestion_id());
        assertEquals("No", questionnaire.getAnswer());
    }

}
