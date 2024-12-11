package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionnaireServiceTests {

    @MockBean
    private QuestionnaireService questionnaireService;

    private Questions makeMockQuestion() {
        Questions question = new Questions();
        question.setQuestion_id(1);
        question.setQuestion("Do you have any allergies?");
        return question;
    }


    private Questionnaire makeMockQuestionnaire() {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestionnaireId(1);
        Questions question = makeMockQuestion();
        questionnaire.setQuestion(question);
        questionnaire.setAnswer("No");
        return questionnaire;
    }

    private Donor makeMockDonor() {
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1990, 1, 1));
        return donor;
    }

    @Test
    public void shouldGetDonorsAnswers() {
        // Given
        Donor donor = makeMockDonor();
        Questionnaire questionnaire = makeMockQuestionnaire();
        questionnaire.setDonor(donor);

        // When
        when(questionnaireService.getDonorsAnswers(1)).thenReturn(List.of(questionnaire));
        List<Questionnaire> questionnaires = questionnaireService.getDonorsAnswers(1);

        // Then
        assertEquals(1, questionnaires.size());
        assertEquals("Do you have any allergies?", questionnaires.get(0).getQuestion().getQuestion());
        assertEquals("No", questionnaires.get(0).getAnswer());
        assertEquals(1, questionnaires.get(0).getQuestionnaireId());
        assertEquals(1, questionnaires.get(0).getDonor().getDonorId());
    }
}
