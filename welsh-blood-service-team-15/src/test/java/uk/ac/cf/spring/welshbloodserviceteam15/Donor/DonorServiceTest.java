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
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class DonorServiceTest {

    @Autowired
    private DonorService donorService;

    @MockBean
    private CountryDiseaseRepository countryDiseaseRepository;

    @MockBean
    private DonorTravelledToCountryRepository donorTravelledToCountryRepository;

    /**
     * This method checks that donor disease risks are sorted such that entries with a "No risk for donor"
     * message appear last, while risks from region-specific and country-wide diseases are listed first.
     */

    @Test
    public void testDiseaseRisksSortedWithNoRiskMessageLast() {
        // Given a donor has traveled to a country with a region-specific disease and a country-wide disease
        // and another country without any linked diseases
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        Donor donor = new Donor();
        Country country1 = new Country();
        Region region1 = new Region();
        Country country2 = new Country();
        Region region2 = new Region();

        List<DonorTravelledToCountry> travelHistory = List.of(
                new DonorTravelledToCountry(1, donor, country1, region1, LocalDate.of(2024, 1, 1)),
                new DonorTravelledToCountry(2, donor, country2, region2, LocalDate.of(2024, 11, 11))
        );
        when(donorTravelledToCountryRepository.findAll()).thenReturn(travelHistory);

        List<CountryDiseaseDTO> diseaseRisks = List.of(
                new CountryDiseaseDTO("United States of America", "California", "Zika Virus",
                        "Zika virus detected in California.", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 9, 30), ""),
                new CountryDiseaseDTO("United States of America", "N/A - Disease Applies to the whole country", "West Nile Virus Risk",
                        "West Nile Virus applies to the entire United States.", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 11, 30), ""),
                new CountryDiseaseDTO("CountryWithoutDiseases", "RegionWithoutDiseases", null, null, null, null,
                        "No risk for donor as donor has returned before the disease start date.")
        );
        when(countryDiseaseRepository.findAllRegionsAndDiseasesForDonor(Mockito.eq(donorId), Mockito.any(LocalDate.class)))
                .thenReturn(diseaseRisks);

        // When the disease risks are returned
        List<CountryDiseaseDTO> result = donorService.getDonorDiseaseRisks(donorId, oneYearAgo);

        // Then there should be three disease risks, with the "no risk for donor" message sorter
        // and at the end of the list.
        assertEquals(3, result.size());
        assertTrue(result.get(0).getRiskMessage().isEmpty() || !result.get(0).getRiskMessage().startsWith("No risk"));
        assertTrue(result.get(1).getRiskMessage().isEmpty() || !result.get(1).getRiskMessage().startsWith("No risk"));
        assertTrue(result.get(2).getRiskMessage().startsWith("No risk") || result.get(2).getRiskMessage().contains("No linked"));
    }

    /**
     * This method ensures that a donor who has not traveled to a region with an active disease
     * receives a message indicating no risk.
     */
    @Test
    public void testNoRiskIfReturnedBeforeDiseaseStartDate() {
        // Given a donor has returned from a country region before a disease's start date in that region
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        Donor donor = new Donor();
        Country country1 = new Country();
        country1.setCountryName("United States of America");
        Region region1 = new Region();
        region1.setRegionName("California");

        List<DonorTravelledToCountry> travelHistory = List.of(
                new DonorTravelledToCountry(1, donor, country1, region1, LocalDate.of(2024, 1, 1))
        );

        when(donorTravelledToCountryRepository.findAll()).thenReturn(travelHistory);

        List<CountryDiseaseDTO> diseaseRisks = List.of(
                new CountryDiseaseDTO("United States of America", "California", "West Nile Virus Risk",
                        "Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.",
                        LocalDate.of(2024, 5, 1), LocalDate.of(2024, 11, 30),
                        "No risk for donor as donor has returned before the disease start date.")
        );
        when(countryDiseaseRepository.findAllRegionsAndDiseasesForDonor(Mockito.eq(donorId), Mockito.any(LocalDate.class)))
                .thenReturn(diseaseRisks);

        // When the disease risks are returned
        List<CountryDiseaseDTO> result = donorService.getDonorDiseaseRisks(donorId, oneYearAgo);

        // Then there should be no disease risk and a message should state there is no risk for the donor as they returned
        // before the disease start date
        assertEquals("No risk for donor as donor has returned before the disease start date.", result.get(0).getRiskMessage());
    }


    /**
     * This method ensures no disease risk is shown if a donor returned from a country or region
     * before the disease's start date, with an appropriate "No risk for donor" message.
     */
    @Test
    public void testDonorWithMultipleCountriesAndDiseaseRisks() {
        // Given a donor has returned from one country before the disease start date in that country
        // and has returned from another country during the disease active dates in that country
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        Donor donor = new Donor();
        Country country1 = new Country();
        Region region1 = new Region();
        Country country2 = new Country();
        Region region2 = new Region();

        List<DonorTravelledToCountry> travelHistory = List.of(
                new DonorTravelledToCountry(1, donor, country1, region1, LocalDate.of(2024, 1, 1)),
                new DonorTravelledToCountry(2, donor, country2, region2, LocalDate.of(2024, 11, 11))
        );
        when(donorTravelledToCountryRepository.findAll()).thenReturn(travelHistory);

        List<CountryDiseaseDTO> diseaseRisks = List.of(
                new CountryDiseaseDTO("United States of America", "N/A - Disease Applies to the whole country", "West Nile Virus Risk", "Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 11, 30), "No risk for donor as donor has returned before the disease start date."),
                new CountryDiseaseDTO("Bahrain", "Test-region", "Malaria", "Bad disease", LocalDate.of(1976, 11, 26), LocalDate.of(2024, 12, 6), "At risk because donor visited region with active disease.")
        );
        when(countryDiseaseRepository.findAllRegionsAndDiseasesForDonor(Mockito.eq(donorId), Mockito.any(LocalDate.class)))
                .thenReturn(diseaseRisks);

        // When the disease risks are returned
        List<CountryDiseaseDTO> result = donorService.getDonorDiseaseRisks(donorId, oneYearAgo);

        // Then disease risks for one country should be returned, and for the other country
        // a no risk message should be returned
        assertEquals("United States of America", result.get(0).countryName());
        assertEquals("N/A - Disease Applies to the whole country", result.get(0).regionName());
        assertEquals("West Nile Virus Risk", result.get(0).diseaseName());
        assertEquals("No risk for donor as donor has returned before the disease start date.", result.get(0).getRiskMessage());

        assertEquals("Bahrain", result.get(1).countryName());
        assertEquals("Test-region", result.get(1).regionName());
        assertEquals("At risk because donor visited region with active disease.", result.get(1).getRiskMessage());
    }

    /**
     * Verifies that both region-specific and country-wide diseases are correctly listed
     * when a donor travels to a region with its own disease and a country-wide disease applies.
     */
    @Test
    public void testDiseaseAppliesToEntireCountry() {
        // Given a donor has travelled to one region that has its own specific disease and that same country
        // has a country-wide disease
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        Donor donor = new Donor();
        Country country1 = new Country();
        Region region1 = new Region();

        List<DonorTravelledToCountry> travelHistory = List.of(
                new DonorTravelledToCountry(1, donor, country1, region1, LocalDate.of(2024, 8, 1))
        );

        when(donorTravelledToCountryRepository.findAll()).thenReturn(travelHistory);

        List<CountryDiseaseDTO> diseaseRisks = List.of(
                new CountryDiseaseDTO("United States of America", "California", "Zika Virus",
                        "Zika virus detected in California.", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 9, 30), ""),
                new CountryDiseaseDTO("United States of America", "N/A - Disease Applies to the whole country", "West Nile Virus Risk",
                        "West Nile Virus applies to the entire United States.", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 11, 30), "")
        );

        when(countryDiseaseRepository.findAllRegionsAndDiseasesForDonor(Mockito.eq(donorId), Mockito.any(LocalDate.class)))
                .thenReturn(diseaseRisks);

        // When the disease risks are returned
        List<CountryDiseaseDTO> result = donorService.getDonorDiseaseRisks(donorId, oneYearAgo);

        // Then there should be two disease risks, one for the whole country and one for the region.
        assertEquals(2, result.size());

        // Region-specific disease
        assertEquals("United States of America", result.get(0).countryName());
        assertEquals("California", result.get(0).regionName());
        assertEquals("Zika Virus", result.get(0).diseaseName());
        assertEquals("Zika virus detected in California.", result.get(0).diseaseNote());

        // Country-wide disease
        assertEquals("United States of America", result.get(1).countryName());
        assertEquals("N/A - Disease Applies to the whole country", result.get(1).regionName());
        assertEquals("West Nile Virus Risk", result.get(1).diseaseName());
        assertEquals("West Nile Virus applies to the entire United States.", result.get(1).diseaseNote());
    }


    /**
     * Verifies that a donor who travels to a country or region without linked diseases
     * receives a message indicating no associated disease risks.
     */
    @Test
    public void testDonorTravelledToCountryWithoutDiseases() {
        // Given a donor has travelled to a country without any linked diseases
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        Donor donor = new Donor();
        Country country1 = new Country();
        Region region1 = new Region();

        List<DonorTravelledToCountry> travelHistory = List.of(
                new DonorTravelledToCountry(1, donor, country1, region1, LocalDate.of(2024, 6, 15))
        );

        when(donorTravelledToCountryRepository.findAll()).thenReturn(travelHistory);

        List<CountryDiseaseDTO> diseaseRisks = List.of(
                new CountryDiseaseDTO("CountryWithoutDiseases", "RegionWithoutDiseases", null, null, null, null,
                        "No linked diseases found for this country/region.")
        );

        when(countryDiseaseRepository.findAllRegionsAndDiseasesForDonor(Mockito.eq(donorId), Mockito.any(LocalDate.class)))
                .thenReturn(diseaseRisks);

        // When the disease risks are returned
        List<CountryDiseaseDTO> result = donorService.getDonorDiseaseRisks(donorId, oneYearAgo);


        // Then there should be no disease risk and a message should state there are no
        // linked diseases for the country/region
        assertEquals("No linked diseases found for this country/region.", result.get(0).getRiskMessage());
    }


    /**
     * Validates that a donor who traveled to an unaffected region within a country
     * with an active disease in another region receives a message indicating no risk.
     */
    @Test
    public void testDonorTraveledToUnaffectedRegion() {
        // Given a donor has traveled to a country with an active disease in one region,
        // but the donor has not traveled to the affected region and has travelled to an unaffected region
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        Donor donor = new Donor();
        Country country = new Country();
        country.setCountryName("CountryX");
        Region affectedRegion = new Region();
        affectedRegion.setRegionName("AffectedRegion");
        Region unaffectedRegion = new Region();
        unaffectedRegion.setRegionName("UnaffectedRegion");

        List<DonorTravelledToCountry> travelHistory = List.of(
                new DonorTravelledToCountry(1, donor, country, unaffectedRegion, LocalDate.of(2024, 6, 15))
        );
        when(donorTravelledToCountryRepository.findAll()).thenReturn(travelHistory);

        List<CountryDiseaseDTO> diseaseRisks = List.of(
                new CountryDiseaseDTO("CountryX", "AffectedRegion", "DiseaseX", "DiseaseX is active in the affected region.", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31), "No risk for donor as donor did not travel to the affected region.")
        );
        when(countryDiseaseRepository.findAllRegionsAndDiseasesForDonor(Mockito.eq(donorId), Mockito.any(LocalDate.class)))
                .thenReturn(diseaseRisks);

        // When the disease risks are returned
        List<CountryDiseaseDTO> result = donorService.getDonorDiseaseRisks(donorId, oneYearAgo);

        // Then there should be no disease risk because the donor did not travel to the affected region
        assertEquals("No risk for donor as donor did not travel to the affected region.", result.get(0).getRiskMessage());
    }

}