package uk.ac.cf.spring.welshbloodserviceteam15.Questions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionsTests {

    @Test
    public void testQuestionCreation() {
        // Given
        Questions question = new Questions();
        question.setQuestion_id(1);
        question.setQuestion("Do you have any allergies?");
        // When
        // Then
        assertEquals(1, question.getQuestion_id());
        assertEquals("Do you have any allergies?", question.getQuestion());
    }
}
