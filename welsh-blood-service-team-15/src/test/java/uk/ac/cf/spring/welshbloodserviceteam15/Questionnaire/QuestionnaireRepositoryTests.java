package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionnaireRepositoryTests {

    @Mock
    private QuestionnaireRepository questionnaireRepository;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

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
    public void shouldFindByDonorId() {
        // Given
        Donor donor = makeMockDonor();
        Questionnaire questionnaire = makeMockQuestionnaire();
        questionnaire.setDonor(donor);

        // behavior of mock methods
        when(questionnaireRepository.save(any(Questionnaire.class))).thenReturn(questionnaire);
        when(questionnaireRepository.findByDonor_DonorId(1)).thenReturn(List.of(questionnaire));

        // When
        questionnaireRepository.save(questionnaire);
        List<Questionnaire> questionnaires = questionnaireRepository.findByDonor_DonorId(1);

        // Then
        assertEquals(1, questionnaires.size());
        assertEquals(questionnaire, questionnaires.get(0));

        // Verify interactions
        verify(questionnaireRepository, times(1)).save(any(Questionnaire.class));
        verify(questionnaireRepository, times(1)).findByDonor_DonorId(1);
    }
}
