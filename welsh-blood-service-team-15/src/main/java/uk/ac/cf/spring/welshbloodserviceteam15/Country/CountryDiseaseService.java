package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryDiseaseService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryDiseaseRepository countryDiseaseRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public void save(CountryDisease countryDisease) {
        countryDiseaseRepository.save(countryDisease);
    }

    public CountryDisease findById(Integer id) {
        return countryDiseaseRepository.findById(id).orElse(null);
    }
}
