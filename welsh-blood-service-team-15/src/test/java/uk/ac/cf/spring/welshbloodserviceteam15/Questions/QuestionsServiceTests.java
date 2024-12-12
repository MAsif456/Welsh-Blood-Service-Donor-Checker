package uk.ac.cf.spring.welshbloodserviceteam15.Questions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionsServiceTests {

    @MockBean
    private QuestionService questionService;

    private Questions makeMockQuestion() {
        Questions question = new Questions();
        question.setQuestion_id(1);
        question.setQuestion("Do you have any allergies?");
        return question;
    }

    @Test
    public void shouldGetQuestions() {
        // Given
        Questions question = makeMockQuestion();

        // When
        when(questionService.getQuestions()).thenReturn(List.of(question));
        List<Questions> questions = questionService.getQuestions();

        // Then
        assertEquals(1, questions.size());
        assertEquals(question, questions.get(0));
    }
}
