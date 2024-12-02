package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountryRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.travelform.TravelQuestionnaireRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TravelFormControllerTests {

    @Autowired
    private TravelQuestionnaireRepository travelQuestionnaireRepository;

    @Autowired
    private DonorTravelledToCountryRepository donorTravelledToCountryRepository;

    @Test
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