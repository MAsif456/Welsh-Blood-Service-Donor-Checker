package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountry;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountryRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.travelform.TravelQuestionnaire;
import uk.ac.cf.spring.welshbloodserviceteam15.travelform.TravelQuestionnaireRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

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
        List<Long> questionIds = List.of(1L, 5L, 9L, 13L, 17L, 21L);
        String answer = "Yes";

        // Simulate form submission by saving data directly to the repository
        for (Long questionId : questionIds) {
            TravelQuestionnaire questionnaire = new TravelQuestionnaire();
            questionnaire.setDonorId(donorId);
            questionnaire.setQuestionId(questionId);
            questionnaire.setAnswer(answer);
            travelQuestionnaireRepository.save(questionnaire);
        }

        // Then
        for (Long questionId : questionIds) {
            Optional<TravelQuestionnaire> savedData = travelQuestionnaireRepository.findByDonorIdAndQuestionId(donorId, questionId);
            assertThat(savedData).isPresent();
            assertThat(savedData.get().getAnswer()).isEqualTo(answer);
        }
    }

    @Test
    public void givenConditionalQuestions_whenSubmitTravelForm_thenDataIsSavedInDonorTravelledToCountry() throws Exception {
        // Given
        Donor donor = new Donor();
        donor.setDonorId(1); // Assuming the donor with ID 1 exists in the database

        Country country = new Country();
        country.setCountryId(1); // Assuming the country with ID 1 exists in the database

        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 10);

        // Simulate form submission by saving data directly to the repository
        DonorTravelledToCountry donorTravel = new DonorTravelledToCountry();
        donorTravel.setDonor(donor);
        donorTravel.setCountry(country);
        donorTravel.setStartDate(startDate);
        donorTravel.setEndDate(endDate);
        donorTravelledToCountryRepository.save(donorTravel);

        // Then
        Optional<DonorTravelledToCountry> savedData = donorTravelledToCountryRepository.findById(donorTravel.getId());
        assertThat(savedData).isPresent();
        assertThat(savedData.get().getStartDate()).isEqualTo(startDate);
        assertThat(savedData.get().getEndDate()).isEqualTo(endDate);
    }
}