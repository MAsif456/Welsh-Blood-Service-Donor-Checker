package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionnaireTests {

    @Test
    public void testQuestionnaireCreation() {
        // Given
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestionnaireId(1);

        Questions questions = new Questions();
        questions.setQuestion_id(1);
        questions.setQuestion("Do you have any allergies?");

        questionnaire.setQuestion(questions);
        questionnaire.setAnswer("No");
        // When
        // Then
        assertEquals(1, questionnaire.getQuestionnaireId());
        assertEquals("No", questionnaire.getAnswer());
        assertEquals(questions, questionnaire.getQuestion());
    }
}
