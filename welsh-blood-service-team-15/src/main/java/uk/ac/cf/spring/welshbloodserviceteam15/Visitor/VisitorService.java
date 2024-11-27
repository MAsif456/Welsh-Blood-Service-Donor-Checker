package uk.ac.cf.spring.welshbloodserviceteam15.Visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseRepository;

import java.util.List;

@Service
public class VisitorService {


    @Autowired
    private CountryDiseaseRepository countryDiseaseRepository;

    public List<CountryDisease> getVisitorRisks(Integer donorId) {
        return countryDiseaseRepository.findVisitorRelatedRisksForDonor(donorId);
    }
}
