package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseDTO;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiseaseService {

    // This method sorts the list of diseases such that risks containing "No risk for donor" or "No linked"
    // messages are placed at the end of the list, with  a value of 1.
    public List<CountryDiseaseDTO> sortDiseasesByRisk(List<CountryDiseaseDTO> diseases) {

        List<CountryDiseaseDTO> sortedDiseases = diseases.stream()
                .sorted(Comparator.comparing(disease ->
                        disease.getRiskMessage().contains("No risk for donor") || disease.getRiskMessage().contains("No linked") ? 1 : 0))
                .collect(Collectors.toList());

        return sortedDiseases;
    }
}
