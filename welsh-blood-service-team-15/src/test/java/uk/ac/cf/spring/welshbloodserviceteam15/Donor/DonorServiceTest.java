package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.*;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
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


    @Test
    public void testDonorTravelToMultipleRegionsWithMixedRisk() {
        // Given: A donor travels to one region and doesn't travel to another in the same country
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        Country country = new Country();
        country.setCountryName("India");

        Region region1 = new Region();
        region1.setRegionName("Mumbai");
        region1.setCountry(country);

        Region region2 = new Region();
        region2.setRegionName("Delhi");
        region2.setCountry(country);

        Disease disease1 = new Disease();
        disease1.setDiseaseName("Disease in Mumbai");

        Disease disease2 = new Disease();
        disease2.setDiseaseName("Disease in Delhi");

        CountryDisease countryDisease1 = new CountryDisease();
        countryDisease1.setCountry(country);
        countryDisease1.setRegion(region1);
        countryDisease1.setDisease(disease1);
        countryDisease1.setStartDate(LocalDate.now().minusMonths(6));
        countryDisease1.setEndDate(LocalDate.now().minusMonths(3));
        countryDisease1.setNote("Mumbai Disease Note");

        CountryDisease countryDisease2 = new CountryDisease();
        countryDisease2.setCountry(country);
        countryDisease2.setRegion(region2);
        countryDisease2.setDisease(disease2);
        countryDisease2.setStartDate(LocalDate.now().minusMonths(6));
        countryDisease2.setEndDate(LocalDate.now().minusMonths(3));
        countryDisease2.setNote("Delhi Disease Note");

        DonorTravelledToCountry donorTravel = new DonorTravelledToCountry();
        donorTravel.setCountry(country);
        donorTravel.setRegion(region1);
        donorTravel.setEndDate(LocalDate.now().minusMonths(2));

//        Mockito.when(countryDiseaseRepository.findRelevantDiseasesForDonorAndRegions(donorId, oneYearAgo))
//                .thenReturn(List.of(
//                        new CountryDiseaseDTO(
//                                "India",
//                                "Mumbai",
//                                "Disease in Mumbai",
//                                "Mumbai Disease Note",
//                                LocalDate.now().minusMonths(6),
//                                LocalDate.now().minusMonths(3),
//                                null
//                        ),
//                        new CountryDiseaseDTO(
//                                "India",
//                                "Delhi",
//                                "Disease in Delhi",
//                                "Delhi Disease Note",
//                                LocalDate.now().minusMonths(6),
//                                LocalDate.now().minusMonths(3),
//                                "No risk for donor in this region"
//                        )
//                ));

        // When
        List<CountryDiseaseDTO> actualDiseases = donorService.getRelevantDiseasesForDonor(donorId);

        // Then
        assertEquals(2, actualDiseases.size());
        assertEquals("Mumbai", actualDiseases.get(0).regionName());
        assertNull(actualDiseases.get(0).riskMessage());
        assertEquals("Delhi", actualDiseases.get(1).regionName());
        assertEquals("No risk for donor in this region", actualDiseases.get(1).riskMessage());
    }

}