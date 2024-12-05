package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.RegionRepository;

import java.time.LocalDate;

@Service
public class DonorDonorTravelledToCountryServiceImpl implements DonorDonorTravelledToCountryService {

    private final DonorTravelledToCountryRepository donorTravelledToCountryRepository;
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final DonorRepository donorRepository;

    public DonorDonorTravelledToCountryServiceImpl(DonorTravelledToCountryRepository donorTravelledToCountryRepository, CountryRepository countryRepository, RegionRepository regionRepository, DonorRepository donorRepository) {
        this.donorTravelledToCountryRepository = donorTravelledToCountryRepository;
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
        this.donorRepository = donorRepository;
    }

    public void saveFromForm(int donorId, int countryId, int regionId, String Date) {
        DonorTravelledToCountry donorTravelledToCountry = new DonorTravelledToCountry();
        Donor donor = donorRepository.findByDonorId(donorId);
        donorTravelledToCountry.setDonor(donor);

        Country country = countryRepository.findByCountryId(countryId);
        Region region = regionRepository.findByRegionId(regionId);
        donorTravelledToCountry.setRegion(region);
        donorTravelledToCountry.setCountry(country);
        donorTravelledToCountry.setEndDate(LocalDate.parse(Date));
        donorTravelledToCountryRepository.save(donorTravelledToCountry);
    }

    public void saveFromForm(int donorId, int countryId, String Date) {
        DonorTravelledToCountry donorTravelledToCountry = new DonorTravelledToCountry();
        Donor donor = donorRepository.findByDonorId(donorId);
        donorTravelledToCountry.setDonor(donor);

        Country country = countryRepository.findByCountryId(countryId);
        donorTravelledToCountry.setCountry(country);
        donorTravelledToCountry.setEndDate(LocalDate.parse(Date));
        donorTravelledToCountryRepository.save(donorTravelledToCountry);
    }
}
