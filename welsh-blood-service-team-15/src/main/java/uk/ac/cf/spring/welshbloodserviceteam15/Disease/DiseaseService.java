package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseDTO;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private CountryDiseaseRepository countryDiseaseRepository;
    public List<CountryDiseaseDTO> sortDiseasesByRisk(List<CountryDiseaseDTO> diseases) {
        return diseases.stream()
                .sorted(Comparator.comparing(disease ->
                        disease.getRiskMessage().startsWith("No risk") || disease.getRiskMessage().contains("No linked") ? 1 : 0))
                .collect(Collectors.toList());
    }

    public void save(CountryDisease countryDisease) {
        System.out.println("Saving country disease: " + countryDisease);
        countryDiseaseRepository.save(countryDisease);
        System.out.println("Saved country disease: " + countryDisease);
    }

    public void save(Disease disease) {
        diseaseRepository.save(disease);
    }
}
