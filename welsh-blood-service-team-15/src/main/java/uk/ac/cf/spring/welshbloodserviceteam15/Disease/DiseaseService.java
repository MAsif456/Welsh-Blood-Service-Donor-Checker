package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseDTO;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.RegionRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiseaseService {

    @Autowired
    private CountryDiseaseRepository countryDiseaseRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;

    public List<CountryDiseaseDTO> sortDiseasesByRisk(List<CountryDiseaseDTO> diseases) {
        return diseases.stream()
                .sorted(Comparator.comparing(disease ->
                        disease.getRiskMessage().startsWith("No risk") || disease.getRiskMessage().contains("No linked") ? 1 : 0))
                .collect(Collectors.toList());
    }

    public void saveDisease(DiseaseDTO diseaseDTO) {
        if (diseaseDTO.getCountryId() == null) {
            throw new IllegalArgumentException("Country ID must not be null");
        }

        CountryDisease countryDisease = new CountryDisease();

        // Check if the disease already exists
        Optional<Disease> existingDisease = diseaseRepository.findByDiseaseName(diseaseDTO.getDiseaseName());
        Disease disease;
        if (existingDisease.isPresent()) {
            disease = existingDisease.get();
        } else {
            disease = new Disease();
            disease.setDiseaseName(diseaseDTO.getDiseaseName());
            disease = diseaseRepository.save(disease);
        }

        Country country = countryRepository.findById(diseaseDTO.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid country ID: " + diseaseDTO.getCountryId()));

        Region region = null;
        if (diseaseDTO.getRegionId() != null) {
            region = regionRepository.findById(diseaseDTO.getRegionId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid region ID: " + diseaseDTO.getRegionId()));
        }

        countryDisease.setDisease(disease);
        countryDisease.setCountry(country);
        countryDisease.setRegion(region);
        countryDisease.setNote(diseaseDTO.getNote());
        countryDisease.setStartDate(diseaseDTO.getStartDate());
        countryDisease.setEndDate(diseaseDTO.getEndDate());

        countryDiseaseRepository.save(countryDisease);
    }
}