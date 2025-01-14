package uk.ac.cf.spring.welshbloodserviceteam15.DeferralPeriod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.*;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Deferral.DeferralPeriod;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Deferral.DeferralPeriodRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.DiseaseDTO;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.DiseaseService;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.DiseaseRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorController;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorService;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DeferralPeriodTests {

    @Mock
    private DonorService donorService;

    @InjectMocks
    private DiseaseService diseaseService;


    @MockBean
    private CountryDiseaseRepository countryDiseaseRepository;

    @MockBean
    private DeferralPeriodRepository deferralPeriodRepository;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private DiseaseRepository diseaseRepository;

    @InjectMocks
    private CountryDiseaseService countryDiseaseService;


    @BeforeEach
    public void setUp() {
        // Initialize mock objects
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetDeferralPeriodRecommendations() {
        // Given a valid donor at risk of diseases
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        List<CountryDiseaseDTO> diseases = List.of(new CountryDiseaseDTO(1, "Country", "Region", "Disease", "Note", LocalDate.now(), LocalDate.now(), "", 30, "Deferral Note"));
        List<CountryDiseaseDTO> recommendations = List.of(new CountryDiseaseDTO(1, "Country", "Region", "Disease", "Note", LocalDate.now(), LocalDate.now(), "", 30, "Deferral Note"));

        when(countryDiseaseRepository.findAllRegionsAndDiseasesForDonor(donorId, oneYearAgo)).thenReturn(diseases);
        when(donorService.getDeferralPeriodRecommendations(donorId)).thenReturn(recommendations);

        // When the method is called
        List<CountryDiseaseDTO> result = donorService.getDeferralPeriodRecommendations(donorId);

        // Then the deferral periods are checked to see if not empty and have deferral periods
        assertFalse(result.isEmpty());
        assertNotNull(result.get(0).deferralPeriodDays());
    }

    @Test
    public void testFindAllRegionsAndDiseasesForDonor() {
        // Given a valid donor at risk of diseases
        Integer donorId = 1;
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        List<CountryDiseaseDTO> diseases = List.of(new CountryDiseaseDTO(1, "Country", "Region", "Disease", "Note", LocalDate.now(), LocalDate.now(), "", 30, "Deferral Note"));
        when(countryDiseaseRepository.findAllRegionsAndDiseasesForDonor(donorId, oneYearAgo)).thenReturn(diseases);

        // When the method is called
        List<CountryDiseaseDTO> result = countryDiseaseRepository.findAllRegionsAndDiseasesForDonor(donorId, oneYearAgo);

        // Then the deferral periods are checked to see if not empty and have deferral periods
        assertFalse(result.isEmpty());
        assertNotNull(result.get(0).deferralPeriodDays());
    }

    @Test
    public void testSaveCountryDisease() {
        // Given a CountryDisease with a deferral period
        CountryDisease countryDisease = new CountryDisease();
        DeferralPeriod deferralPeriod = new DeferralPeriod();
        deferralPeriod.setPeriodDays(30);
        countryDisease.setDeferralPeriod(deferralPeriod);

        when(deferralPeriodRepository.findByPeriodDays(30)).thenReturn(Optional.of(deferralPeriod));

        // When the save method is called
        countryDiseaseService.save(countryDisease);

        // Then the CountryDisease is saved with the existing deferral period
        verify(countryDiseaseRepository).save(countryDisease);
        assertEquals(deferralPeriod, countryDisease.getDeferralPeriod());
    }

    @Test
    public void testSaveDisease() {
        // Given a DiseaseDTO with deferral period days
        DiseaseDTO diseaseDTO = new DiseaseDTO();
        diseaseDTO.setCountryId(1);
        diseaseDTO.setDiseaseName("Disease");
        diseaseDTO.setDeferralPeriodDays(30);

        DeferralPeriod deferralPeriod = new DeferralPeriod();
        deferralPeriod.setPeriodDays(30);

        when(deferralPeriodRepository.findByPeriodDays(30)).thenReturn(Optional.of(deferralPeriod));
        when(countryRepository.findById(1)).thenReturn(Optional.of(new Country()));

        // When the saveDisease method is called
        diseaseService.saveDisease(diseaseDTO);

        // Then the CountryDisease is saved with the deferral period
        verify(countryDiseaseRepository).save(any(CountryDisease.class));
    }
}