package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import java.time.LocalDate;

public record CountryDiseaseDTO(
        String countryName,
        String regionName, // Added region
        String diseaseName,
        String diseaseNote,
        LocalDate diseaseStart,
        LocalDate diseaseEnd,
        String riskMessage
) {}
