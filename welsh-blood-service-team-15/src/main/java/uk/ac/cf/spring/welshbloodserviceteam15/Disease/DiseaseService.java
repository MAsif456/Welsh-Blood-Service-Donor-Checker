package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseDTO;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiseaseService {
    public List<CountryDiseaseDTO> sortDiseasesByRisk(List<CountryDiseaseDTO> diseases) {
        return diseases.stream()
                .sorted(Comparator.comparing(disease ->
                        disease.getRiskMessage().startsWith("No risk") || disease.getRiskMessage().contains("No linked") ? 1 : 0))
                .collect(Collectors.toList());
    }
}
