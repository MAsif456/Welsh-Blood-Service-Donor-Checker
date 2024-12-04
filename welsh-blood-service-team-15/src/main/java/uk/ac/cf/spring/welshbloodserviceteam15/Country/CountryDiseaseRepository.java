package uk.ac.cf.spring.welshbloodserviceteam15.Country;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CountryDiseaseRepository extends JpaRepository<CountryDisease, Integer> {

    @Query("""
SELECT new uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseDTO(
    c.countryName,
    r.regionName,
    d.diseaseName,
    cd.note,
    cd.startDate,
    cd.endDate,
    CASE
        WHEN dtc.donor.donorId IS NULL THEN 'No risk for donor as he has not travelled to this region'
        WHEN dtc.endDate < cd.startDate THEN 'No risk for donor as he has returned before the disease became active.'
        ELSE ''
    END
)
FROM Country c
LEFT JOIN CountryDisease cd ON c.countryId = cd.country.countryId
LEFT JOIN Region r ON r.regionId = cd.region.regionId
LEFT JOIN Disease d ON d.diseaseId = cd.disease.diseaseId
LEFT JOIN DonorTravelledToCountry dtc ON c.countryId = dtc.country.countryId AND (r.regionId IS NULL OR r.regionId = dtc.region.regionId AND dtc.donor.donorId = :donorId)
WHERE c.countryId IN (SELECT dtc.country.countryId FROM DonorTravelledToCountry dtc WHERE dtc.donor.donorId = :donorId AND dtc.endDate > :oneYearAgo)
""")
    List<CountryDiseaseDTO> findAllRegionsAndDiseasesForDonor(@Param("donorId") Integer donorId, @Param("oneYearAgo") LocalDate oneYearAgo);
    // find all basic method
    List<CountryDisease> findAll();

}