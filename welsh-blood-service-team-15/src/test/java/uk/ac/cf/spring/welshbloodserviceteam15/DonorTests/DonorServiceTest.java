package uk.ac.cf.spring.welshbloodserviceteam15.DonorTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Disease;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.DiseaseRepository;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
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

    @Test
    public void testDonorTravelWithoutOverlap() {
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

        // Create and save a donor travel record without overlap
        DonorTravelledToCountry travel = new DonorTravelledToCountry();
        travel.setDonor(donor);
        travel.setCountry(country);
        travel.setStartDate(LocalDate.of(2021, 12, 1));
        travel.setEndDate(LocalDate.of(2021, 12, 31));
        donorTravelledToCountryRepository.save(travel);

        // Test for no overlap
        List<CountryDisease> diseases = donorService.getRelevantDiseasesForDonor(donor.getDonorId());
        assertTrue(diseases.isEmpty());
    }

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
}