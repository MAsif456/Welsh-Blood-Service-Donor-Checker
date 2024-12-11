package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.RegionRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DonorDonorTravelledToCountryServiceTest {

    @InjectMocks
    private DonorDonorTravelledToCountryServiceImpl donorDonorTravelledToCountryService;

    @Mock
    private DonorRepository donorRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private RegionRepository regionRepository;

    @Mock
    private DonorTravelledToCountryRepository donorTravelledToCountryRepository;


    private Donor mockDonor() {
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        return donor;
    }

    private Country mockCountry() {
        Country country = new Country();
        country.setCountryId(1);
        country.setCountryName("Wales");
        return country;
    }

    private Region mockRegion() {
        Region region = new Region();
        region.setRegionId(1);
        region.setRegionName("Cardiff");
        return region;
    }

    private DonorTravelledToCountry mockDonorTravelledToCountry() {
        DonorTravelledToCountry donorTravelledToCountry = new DonorTravelledToCountry();
        donorTravelledToCountry.setId(1);
        donorTravelledToCountry.setDonor(mockDonor());
        donorTravelledToCountry.setCountry(mockCountry());
        donorTravelledToCountry.setRegion(mockRegion());
        donorTravelledToCountry.setEndDate(LocalDate.now());
        return donorTravelledToCountry;
    }

    @Test
    public void testSaveFromFormWithRegion() {
        // Given
        int donorId = 1;
        int countryId = 1;
        int regionId = 1;

        Donor mockDonor = mockDonor();
        Country mockCountry = mockCountry();
        Region mockRegion = mockRegion();

        // When
        when(donorRepository.findByDonorId(donorId)).thenReturn(mockDonor);
        when(countryRepository.findByCountryId(countryId)).thenReturn(mockCountry);
        when(regionRepository.findByRegionId(regionId)).thenReturn(mockRegion);

        donorDonorTravelledToCountryService.saveFromForm(1, 1, 1, "2021-01-01");

        // Then
        assertEquals(1, mockDonor.getDonorId());
        assertEquals("John", mockDonor.getFirstName());
        assertEquals("Doe", mockDonor.getLastName());
        assertEquals(1, mockCountry.getCountryId());
        assertEquals("Wales", mockCountry.getCountryName());
        assertEquals(1, mockRegion.getRegionId());
        assertEquals("Cardiff", mockRegion.getRegionName());
    }

    @Test
    public void testSaveFormWithoutRegion() {
        // Given
        int donorId = 1;
        int countryId = 1;

        Donor mockDonor = mockDonor();
        Country mockCountry = mockCountry();

        // When
        when(donorRepository.findByDonorId(donorId)).thenReturn(mockDonor);
        when(countryRepository.findByCountryId(countryId)).thenReturn(mockCountry);

        donorDonorTravelledToCountryService.saveFromForm(1, 1, "2021-01-01");

        // Then
        assertEquals(1, mockDonor.getDonorId());
        assertEquals("John", mockDonor.getFirstName());
        assertEquals("Doe", mockDonor.getLastName());
        assertEquals(1, mockCountry.getCountryId());
        assertEquals("Wales", mockCountry.getCountryName());
    }

    @Test
    public void shouldGetTravelListByDonorId() {
        // Given
        DonorTravelledToCountry donorTravelledToCountry = mockDonorTravelledToCountry();
        // When
        when(donorDonorTravelledToCountryService.findByDonorId(1)).thenReturn(List.of(donorTravelledToCountry));
        List<DonorTravelledToCountry> donorTravels = donorDonorTravelledToCountryService.findByDonorId(1);
        // Then
        assertEquals(1, donorTravels.size());
        assertEquals(1, donorTravels.get(0).getRegion().getRegionId());
        assertEquals(1, donorTravels.get(0).getCountry().getCountryId());
        assertEquals(1, donorTravels.get(0).getDonor().getDonorId());
        assertEquals(donorTravelledToCountry, donorTravels.get(0));

    }
}
