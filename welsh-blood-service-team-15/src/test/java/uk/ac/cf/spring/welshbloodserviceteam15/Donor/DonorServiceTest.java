package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.*;
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

    @MockBean
    private CountryDiseaseRepository countryDiseaseRepository;

    @MockBean
    private DonorTravelledToCountryRepository donorTravelledToCountryRepository;




    /**
     * This method tests whether the risks for a country are correctly returned
     * when a donor who has traveled abroad in the last 12 months and returned from
     * a country after a disease has been active.
     */
    @Test
    public void testGetRelevantDiseasesForDonor() {
        // Given a donor has travelled abroad in the last 12 months,
        // and they return from a country after a disease has been active,
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        LocalDate diseaseStartDate = LocalDate.now().minusMonths(6);
        LocalDate diseaseEndDate = LocalDate.now().minusMonths(3);
        LocalDate travelEndDate = LocalDate.now().minusMonths(2);

        Country country = new Country();
        country.setCountryName("Test Country");

        Disease disease = new Disease();
        disease.setDiseaseName("Test Disease");

        CountryDisease countryDisease = new CountryDisease();
        countryDisease.setCountry(country);
        countryDisease.setDisease(disease);
        countryDisease.setStartDate(diseaseStartDate);
        countryDisease.setEndDate(diseaseEndDate);
        countryDisease.setNote("Test Note");

        DonorTravelledToCountry donorTravelledToCountry = new DonorTravelledToCountry();
        donorTravelledToCountry.setCountry(country);
        donorTravelledToCountry.setEndDate(travelEndDate);

        List<CountryDiseaseDTO> expectedDiseases = List.of(new CountryDiseaseDTO(
                country.getCountryName(),
                disease.getDiseaseName(),
                countryDisease.getNote(),
                countryDisease.getStartDate(),
                countryDisease.getEndDate(),
                null
        ));

        Mockito.when(countryDiseaseRepository.findRelevantDiseasesForDonor(donorId, oneYearAgo))
                .thenReturn(expectedDiseases);

        // When checking for relevant risks for the donor,
        List<CountryDiseaseDTO> actualDiseases = donorService.getRelevantDiseasesForDonor(donorId);

        // Then the risks for that country should be returned.
        assertEquals(expectedDiseases, actualDiseases);
    }



    /**
     * This method tests whether the country is returned with a message
     * displaying no risks when a donor has traveled abroad in the last
     * 12 months and returned from a country before a disease has been active.
     */

    @Test
    public void testDonorTravelBeforeDiseaseActive() {
        // Given a donor has travelled abroad in the last 12 months,
        // and they return from a country before a disease has been active,
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        LocalDate diseaseStartDate = LocalDate.now().minusMonths(3);
        LocalDate travelEndDate = LocalDate.now().minusMonths(6);

        Country country = new Country();
        country.setCountryName("Test Country");

        Disease disease = new Disease();
        disease.setDiseaseName("Test Disease");

        CountryDisease countryDisease = new CountryDisease();
        countryDisease.setCountry(country);
        countryDisease.setDisease(disease);
        countryDisease.setStartDate(diseaseStartDate);
        countryDisease.setEndDate(diseaseStartDate.plusMonths(1));
        countryDisease.setNote("Test Note");

        DonorTravelledToCountry donorTravelledToCountry = new DonorTravelledToCountry();
        donorTravelledToCountry.setCountry(country);
        donorTravelledToCountry.setEndDate(travelEndDate);

        List<CountryDiseaseDTO> expectedDiseases = List.of(new CountryDiseaseDTO(
                country.getCountryName(),
                disease.getDiseaseName(),
                countryDisease.getNote(),
                countryDisease.getStartDate(),
                countryDisease.getEndDate(),
                "No risk for donor"
        ));

        Mockito.when(countryDiseaseRepository.findRelevantDiseasesForDonor(donorId, oneYearAgo))
                .thenReturn(expectedDiseases);

        // When checking for relevant risks for the donor,
        List<CountryDiseaseDTO> actualDiseases = donorService.getRelevantDiseasesForDonor(donorId);

        // Then that country should be returned with a message displaying no risks.
        assertEquals(expectedDiseases, actualDiseases);
    }


    /**
     * This method tests whether multiple countries are returned with a message
     * displaying a risk message, after the donor has returned from multiple countries,
     * after the active disease dates.
     */

    @Test
    public void testDonorWithMultipleTravelAndReturnAfterActiveDiseases() {
        // Given a donor has travelled to 2 countries in the last 12 months
        // and returned after the active dates of the diseases in both countries
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        // Disease and travel dates for Country 1
        LocalDate diseaseStartDate1 = LocalDate.now().minusMonths(6);
        LocalDate diseaseEndDate1 = LocalDate.now().minusMonths(3);
        LocalDate travelEndDate1 = LocalDate.now().minusMonths(2);

        LocalDate diseaseStartDate2 = LocalDate.now().minusMonths(8);
        LocalDate diseaseEndDate2 = LocalDate.now().minusMonths(4);
        LocalDate travelEndDate2 = LocalDate.now().minusMonths(1);

        Country country1 = new Country();
        country1.setCountryName("Country1");

        Country country2 = new Country();
        country2.setCountryName("Country2");

        Disease disease1 = new Disease();
        disease1.setDiseaseName("Disease1");

        Disease disease2 = new Disease();
        disease2.setDiseaseName("Disease2");

        CountryDisease countryDisease1 = new CountryDisease();
        countryDisease1.setCountry(country1);
        countryDisease1.setDisease(disease1);
        countryDisease1.setStartDate(diseaseStartDate1);
        countryDisease1.setEndDate(diseaseEndDate1);
        countryDisease1.setNote("Note1");

        CountryDisease countryDisease2 = new CountryDisease();
        countryDisease2.setCountry(country2);
        countryDisease2.setDisease(disease2);
        countryDisease2.setStartDate(diseaseStartDate2);
        countryDisease2.setEndDate(diseaseEndDate2);
        countryDisease2.setNote("Note2");

        DonorTravelledToCountry donorTravel1 = new DonorTravelledToCountry();
        donorTravel1.setCountry(country1);
        donorTravel1.setEndDate(travelEndDate1);

        DonorTravelledToCountry donorTravel2 = new DonorTravelledToCountry();
        donorTravel2.setCountry(country2);
        donorTravel2.setEndDate(travelEndDate2);

        List<CountryDiseaseDTO> expectedDiseases = List.of(
                new CountryDiseaseDTO(
                        country1.getCountryName(),
                        disease1.getDiseaseName(),
                        countryDisease1.getNote(),
                        countryDisease1.getStartDate(),
                        countryDisease1.getEndDate(),
                        null
                ),
                new CountryDiseaseDTO(
                        country2.getCountryName(),
                        disease2.getDiseaseName(),
                        countryDisease2.getNote(),
                        countryDisease2.getStartDate(),
                        countryDisease2.getEndDate(),
                        null
                )
        );

        Mockito.when(donorTravelledToCountryRepository.findAll())
                .thenReturn(List.of(donorTravel1, donorTravel2));

        Mockito.when(countryDiseaseRepository.findRelevantDiseasesForDonor(donorId, oneYearAgo))
                .thenReturn(expectedDiseases);

        // When checking for relevant risks for the donor
        List<CountryDiseaseDTO> actualDiseases = donorService.getRelevantDiseasesForDonor(donorId);

        // Then country risks for both countries should be returned.
        assertEquals(2, actualDiseases.size());

        CountryDiseaseDTO actualCountry1 = actualDiseases.get(0);
        assertEquals("Country1", actualCountry1.countryName());
        assertNull(actualCountry1.riskMessage());

        CountryDiseaseDTO actualCountry2 = actualDiseases.get(1);
        assertEquals("Country2", actualCountry2.countryName());
        assertNull(actualCountry2.riskMessage());
    }


     /**
       * This test verifies the behavior when a donor has travelled to two countries in the last 12 months,
       * with one country's travel ending before the active dates of the disease and the other country's
       * travel overlapping with the active dates of the disease.
      */

    @Test
    public void testDonorWithTwoCountriesTravelAndMixedDiseaseActiveDates() {
        // Given a donor has travelled to 2 countries in the last 12 months,
        // and he has returned from the first country before the active dates
        // of  disease, and he has returned from the second country after the
        // active dates of the disease
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        LocalDate diseaseStartDate1 = LocalDate.now().minusMonths(6);

        // Before disease active dates
        LocalDate diseaseEndDate1 = LocalDate.now().minusMonths(3);
        LocalDate travelEndDate1 = LocalDate.now().minusMonths(7);

        LocalDate diseaseStartDate2 = LocalDate.now().minusMonths(8);

        // After disease active dates
        LocalDate diseaseEndDate2 = LocalDate.now().minusMonths(4);
        LocalDate travelEndDate2 = LocalDate.now().minusMonths(2);

        Country country1 = new Country();
        country1.setCountryName("Country1");

        Country country2 = new Country();
        country2.setCountryName("Country2");

        Disease disease1 = new Disease();
        disease1.setDiseaseName("Disease1");

        Disease disease2 = new Disease();
        disease2.setDiseaseName("Disease2");

        CountryDisease countryDisease1 = new CountryDisease();
        countryDisease1.setCountry(country1);
        countryDisease1.setDisease(disease1);
        countryDisease1.setStartDate(diseaseStartDate1);
        countryDisease1.setEndDate(diseaseEndDate1);
        countryDisease1.setNote("Note1");

        CountryDisease countryDisease2 = new CountryDisease();
        countryDisease2.setCountry(country2);
        countryDisease2.setDisease(disease2);
        countryDisease2.setStartDate(diseaseStartDate2);
        countryDisease2.setEndDate(diseaseEndDate2);
        countryDisease2.setNote("Note2");

        DonorTravelledToCountry donorTravel1 = new DonorTravelledToCountry();
        donorTravel1.setCountry(country1);
        donorTravel1.setEndDate(travelEndDate1);

        DonorTravelledToCountry donorTravel2 = new DonorTravelledToCountry();
        donorTravel2.setCountry(country2);
        donorTravel2.setEndDate(travelEndDate2);

        List<CountryDiseaseDTO> expectedDiseases = List.of(
                new CountryDiseaseDTO(
                        country1.getCountryName(),
                        disease1.getDiseaseName(),
                        countryDisease1.getNote(),
                        countryDisease1.getStartDate(),
                        countryDisease1.getEndDate(),
                        "No risk for donor"
                ),
                new CountryDiseaseDTO(
                        country2.getCountryName(),
                        disease2.getDiseaseName(),
                        countryDisease2.getNote(),
                        countryDisease2.getStartDate(),
                        countryDisease2.getEndDate(),
                        null
                )
        );

        Mockito.when(donorTravelledToCountryRepository.findAll())
                .thenReturn(List.of(donorTravel1, donorTravel2));

        Mockito.when(countryDiseaseRepository.findRelevantDiseasesForDonor(donorId, oneYearAgo))
                .thenReturn(expectedDiseases);

        // When checking for relevant risks for the donor
        List<CountryDiseaseDTO> actualDiseases = donorService.getRelevantDiseasesForDonor(donorId);

        // Then for one country should return its country risk, and the other country should return the country with no risks
        assertEquals(2, actualDiseases.size());

        // Assertions for first country (No risk case)
        CountryDiseaseDTO actualCountry1 = actualDiseases.get(0);
        assertEquals("Country1", actualCountry1.countryName());
        assertEquals("No risk for donor", actualCountry1.riskMessage());

        // Assertions for second country (True risk case)
        CountryDiseaseDTO actualCountry2 = actualDiseases.get(1);
        assertEquals("Country2", actualCountry2.countryName());
        assertNull(actualCountry2.riskMessage());
    }
}