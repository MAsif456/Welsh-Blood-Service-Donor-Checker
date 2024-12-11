package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import java.time.LocalDate;
import java.util.Map;

public record CountryDiseaseDTO(
        Integer id,
        String countryName,
        String regionName,
        String diseaseName,
        String diseaseNote,
        LocalDate diseaseStart,
        LocalDate diseaseEnd,
        String riskMessage
) {
    // This method returns the risk message if it is not null, otherwise it returns an empty string.
    public String getRiskMessage() {
        return riskMessage != null ? riskMessage : "";
    }
}
