package uk.ac.cf.spring.welshbloodserviceteam15.Country;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CountryDiseaseRepository extends JpaRepository<CountryDisease, Integer> {
    List<CountryDisease> findByCountryIn(List<Country> countries);

    @Query("""
    SELECT new uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseDTO(
        c.countryName,
        d.diseaseName,
        cd.note,
        cd.startDate,
        cd.endDate,
        CASE WHEN dtc.endDate < cd.startDate THEN 'No risk for donor' ELSE '' END
    )
    FROM CountryDisease cd
    JOIN DonorTravelledToCountry dtc ON cd.country.countryId = dtc.country.countryId
    JOIN Country c ON c.countryId = cd.country.countryId
    LEFT JOIN Disease d ON d.diseaseId = cd.disease.diseaseId
    WHERE dtc.donor.donorId = :donorId AND dtc.endDate > :oneYearAgo""")
    List<CountryDiseaseDTO> findRelevantDiseasesForDonor(@Param("donorId") Integer donorId, @Param("oneYearAgo") LocalDate oneYearAgo);


}