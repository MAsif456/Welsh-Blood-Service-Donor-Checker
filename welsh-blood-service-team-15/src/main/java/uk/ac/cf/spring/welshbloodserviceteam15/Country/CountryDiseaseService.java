package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Deferral.DeferralPeriod;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Deferral.DeferralPeriodRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CountryDiseaseService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryDiseaseRepository countryDiseaseRepository;

    @Autowired
    private DeferralPeriodRepository deferralPeriodRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public void save(CountryDisease countryDisease) {
        if (countryDisease.getDeferralPeriod() != null && countryDisease.getDeferralPeriod().getPeriodDays() != null) {
            Optional<DeferralPeriod> existingDeferralPeriod = deferralPeriodRepository.findByPeriodDays(countryDisease.getDeferralPeriod().getPeriodDays());
            if (existingDeferralPeriod.isPresent()) {
                countryDisease.setDeferralPeriod(existingDeferralPeriod.get());
            } else {
                DeferralPeriod deferralPeriod = new DeferralPeriod();
                deferralPeriod.setPeriodDays(countryDisease.getDeferralPeriod().getPeriodDays());
                deferralPeriod = deferralPeriodRepository.save(deferralPeriod);
                countryDisease.setDeferralPeriod(deferralPeriod);
            }
        }
        countryDiseaseRepository.save(countryDisease);
    }

    public CountryDisease findById(Integer id) {
        return countryDiseaseRepository.findById(id).orElse(null);
    }
}
