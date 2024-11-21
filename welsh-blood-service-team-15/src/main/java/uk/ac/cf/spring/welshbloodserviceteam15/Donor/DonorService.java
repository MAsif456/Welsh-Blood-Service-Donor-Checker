package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseRepository;
import java.util.List;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private CountryDiseaseRepository countryDiseaseRepository;

    public Donor getDonorSummary(Integer donorId) {
        return donorRepository.findByDonorId(donorId);
    }
    public List<CountryDisease> getDiseasesForDonorTravel(Integer donorId) {
        Donor donor = donorRepository.findByDonorId(donorId);
        List<Country> traveledCountries = donor.getTravelHistory()
                .stream()
                .map(DonorTravelledToCountry::getCountry)
                .toList();
        return countryDiseaseRepository.findByCountryIn(traveledCountries);
    }
}