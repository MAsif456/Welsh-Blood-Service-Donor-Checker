package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryService;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Integer countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid country ID: " + countryId));
}
}
