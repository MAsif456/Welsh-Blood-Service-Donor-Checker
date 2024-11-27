package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Disease;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.DiseaseRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questionnaire;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.QuestionnaireRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.QuestionnaireService;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DonorServiceTest {

    @Autowired
    private DonorService donorService;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private CountryDiseaseRepository countryDiseaseRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private DonorTravelledToCountryRepository donorTravelledToCountryRepository;

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionnaireService questionnaireService;


    /**
     * This method tests whether diseases are returned if its active dates do not overlap with the
     * donors visit dates.
     */
    @Test
    public void testDonorTravelWithoutOverlap() {
        // Given: A country, a disease active during a specific period, and a donor who traveled to the country outside the disease's active dates
        Country country = new Country();
        country.setCountryName("TestCountry");
        countryRepository.save(country);

        Disease disease = new Disease();
        disease.setDiseaseName("TestDisease");
        diseaseRepository.save(disease);

        CountryDisease countryDisease = new CountryDisease();
        countryDisease.setCountry(country);
        countryDisease.setDisease(disease);
        countryDisease.setStartDate(LocalDate.of(2022, 1, 1));
        countryDisease.setEndDate(LocalDate.of(2022, 1, 31));
        countryDiseaseRepository.save(countryDisease);

        Donor donor = new Donor();
        donor.setFirstName("John");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1990, 1, 1));
        donorRepository.save(donor);

        DonorTravelledToCountry travel = new DonorTravelledToCountry();
        travel.setDonor(donor);
        travel.setCountry(country);
        travel.setStartDate(LocalDate.of(2021, 12, 1));
        travel.setEndDate(LocalDate.of(2021, 12, 31));
        donorTravelledToCountryRepository.save(travel);

        // When: Checking for relevant diseases for the donor
        List<CountryDisease> diseases = donorService.getRelevantDiseasesForDonor(donor.getDonorId());

        // Then: No diseases should be returned
        assertTrue(diseases.isEmpty());
    }


    /**
     * This method tests whether diseases are returned if its active dates overlap with the
     * donors visit dates.
     */
    @Test
    public void testDonorTravelWithOverlap() {
        // Create and save a country
        Country country = new Country();
        country.setCountryName("TestCountry");
        countryRepository.save(country);

        // Create and save a disease
        Disease disease = new Disease();
        disease.setDiseaseName("TestDisease");
        diseaseRepository.save(disease);

        // Create and save a country disease
        CountryDisease countryDisease = new CountryDisease();
        countryDisease.setCountry(country);
        countryDisease.setDisease(disease);
        countryDisease.setStartDate(LocalDate.of(2022, 1, 1));
        countryDisease.setEndDate(LocalDate.of(2022, 1, 31));
        countryDiseaseRepository.save(countryDisease);

        // Create and save a donor
        Donor donor = new Donor();
        donor.setFirstName("John");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1990, 1, 1));
        donorRepository.save(donor);

        // Create and save a donor travel record with overlap
        DonorTravelledToCountry travel = new DonorTravelledToCountry();
        travel.setDonor(donor);
        travel.setCountry(country);
        travel.setStartDate(LocalDate.of(2022, 1, 15));
        travel.setEndDate(LocalDate.of(2022, 1, 20));
        donorTravelledToCountryRepository.save(travel);

        // Test for overlap
        List<CountryDisease> diseases = donorService.getRelevantDiseasesForDonor(donor.getDonorId());
        assertFalse(diseases.isEmpty());
    }


    /**
     * This method tests whether the diseases list is empty if the donor has not travelled.
     */
    @Test
    public void testDonorWithNoTravelHistory() {
        // Given: A donor who has no travel history
        Donor donor = new Donor();
        donor.setFirstName("Jane");
        donor.setLastName("Doe");
        donor.setDonorDob(LocalDate.of(1992, 2, 2));
        donorRepository.save(donor);

        // When: Checking for relevant diseases for the donor
        List<CountryDisease> diseases = donorService.getRelevantDiseasesForDonor(donor.getDonorId());

        // Then: No diseases should be returned
        assertTrue(diseases.isEmpty());
    }


    /**
     * This method tests whether any disease risks are returned if the donor has a safe travel
     * history.
     */
    @Test
    public void testDonorWithNoDiseaseRisk() {
        // Given: A country with no active diseases during a donor's travel period
        Country country = new Country();
        country.setCountryName("SafeCountry");
        countryRepository.save(country);

        Donor donor = new Donor();
        donor.setFirstName("Alice");
        donor.setLastName("Johnson");
        donor.setDonorDob(LocalDate.of(1985, 5, 5));
        donorRepository.save(donor);

        DonorTravelledToCountry travel = new DonorTravelledToCountry();
        travel.setDonor(donor);
        travel.setCountry(country);
        travel.setStartDate(LocalDate.of(2021, 12, 1));
        travel.setEndDate(LocalDate.of(2021, 12, 31));
        donorTravelledToCountryRepository.save(travel);

        // When: Checking for relevant diseases for the donor
        List<CountryDisease> diseases = donorService.getRelevantDiseasesForDonor(donor.getDonorId());

        // Then: No diseases should be returned
        assertTrue(diseases.isEmpty());
    }


    /**
     * This method tests whether the donor has multiple disease risks associated with them.
     */
    @Test
    public void testDonorWithMultipleDiseaseRisks() {
        // Given: A donor travels to multiple countries with overlapping active diseases
        Country country1 = new Country();
        country1.setCountryName("Country1");
        countryRepository.save(country1);

        Country country2 = new Country();
        country2.setCountryName("Country2");
        countryRepository.save(country2);

        Disease disease1 = new Disease();
        disease1.setDiseaseName("Disease1");
        diseaseRepository.save(disease1);

        Disease disease2 = new Disease();
        disease2.setDiseaseName("Disease2");
        diseaseRepository.save(disease2);

        CountryDisease countryDisease1 = new CountryDisease();
        countryDisease1.setCountry(country1);
        countryDisease1.setDisease(disease1);
        countryDisease1.setStartDate(LocalDate.of(2022, 1, 1));
        countryDisease1.setEndDate(LocalDate.of(2022, 1, 31));
        countryDiseaseRepository.save(countryDisease1);

        CountryDisease countryDisease2 = new CountryDisease();
        countryDisease2.setCountry(country2);
        countryDisease2.setDisease(disease2);
        countryDisease2.setStartDate(LocalDate.of(2022, 2, 1));
        countryDisease2.setEndDate(LocalDate.of(2022, 2, 28));
        countryDiseaseRepository.save(countryDisease2);

        Donor donor = new Donor();
        donor.setFirstName("Bob");
        donor.setLastName("Brown");
        donor.setDonorDob(LocalDate.of(1980, 3, 3));
        donorRepository.save(donor);

        DonorTravelledToCountry travel1 = new DonorTravelledToCountry();
        travel1.setDonor(donor);
        travel1.setCountry(country1);
        travel1.setStartDate(LocalDate.of(2022, 1, 15));
        travel1.setEndDate(LocalDate.of(2022, 1, 20));
        donorTravelledToCountryRepository.save(travel1);

        DonorTravelledToCountry travel2 = new DonorTravelledToCountry();
        travel2.setDonor(donor);
        travel2.setCountry(country2);
        travel2.setStartDate(LocalDate.of(2022, 2, 15));
        travel2.setEndDate(LocalDate.of(2022, 2, 20));
        donorTravelledToCountryRepository.save(travel2);

        // When: Checking for relevant diseases for the donor
        List<CountryDisease> diseases = donorService.getRelevantDiseasesForDonor(donor.getDonorId());

        // Then: Multiple diseases should be returned
        assertFalse(diseases.isEmpty());
        assertEquals(2, diseases.size());
    }

    /**
     * This method tests whether questionnaire answers are found in the list.
     */
    @Test
    public void testQuestionnaireAnswers() {
        // Given: A donor answers multiple questions in a questionnaire
        Donor donor = new Donor();
        donor.setFirstName("Charlie");
        donor.setLastName("Davis");
        donor.setDonorDob(LocalDate.of(1995, 4, 4));
        donorRepository.save(donor);

        Questionnaire answer1 = new Questionnaire();
        answer1.setDonorId(donor.getDonorId());
        answer1.setQuestionId(1);
        answer1.setAnswer("Yes");
        questionnaireRepository.save(answer1);

        Questionnaire answer2 = new Questionnaire();
        answer2.setDonorId(donor.getDonorId());
        answer2.setQuestionId(2);
        answer2.setAnswer("No");
        questionnaireRepository.save(answer2);

        // When: Retrieving questionnaire answers for the donor
        List<Questionnaire> answers = questionnaireService.getDonorsAnswers(donor.getDonorId());

        // Then: All stored answers should be retrieved
        assertFalse(answers.isEmpty());
        assertEquals(2, answers.size());

}}