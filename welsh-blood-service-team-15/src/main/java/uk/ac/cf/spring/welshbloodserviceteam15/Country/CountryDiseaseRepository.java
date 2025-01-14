package uk.ac.cf.spring.welshbloodserviceteam15.Country;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CountryDiseaseRepository extends JpaRepository<CountryDisease, Integer> {

    @Query("""
        SELECT DISTINCT new uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseDTO(
            cd.id,
            c.countryName,
            COALESCE(r.regionName, 'N/A - Disease Applies to the whole country'),
            d.diseaseName,
            cd.note,
            cd.startDate,
            cd.endDate,
            CASE
                WHEN dtc.donor.donorId IS NULL THEN 'No risk for donor as donor has not travelled to this region.'
                WHEN dtc.endDate < cd.startDate THEN 'No risk for donor as donor has returned before the disease start date.'
                WHEN d.diseaseId IS NULL THEN 'No linked diseases found for this country/region.'
                ELSE ''
            END,
            dp.periodDays,
            cd.deferralNote 
        )
        FROM Country c
        LEFT JOIN CountryDisease cd ON c.countryId = cd.country.countryId
        LEFT JOIN Region r ON r.regionId = cd.region.regionId
        LEFT JOIN Disease d ON d.diseaseId = cd.disease.diseaseId
        LEFT JOIN DonorTravelledToCountry dtc ON c.countryId = dtc.country.countryId
            AND (r.regionId IS NULL OR r.regionId = dtc.region.regionId)
            AND dtc.donor.donorId = :donorId
        LEFT JOIN DeferralPeriod dp ON cd.deferralPeriod.deferralPeriodId = dp.deferralPeriodId
        WHERE EXISTS (
            SELECT 1
            FROM DonorTravelledToCountry dtc
            WHERE dtc.donor.donorId = :donorId
            AND dtc.endDate > :oneYearAgo
            AND dtc.country.countryId = c.countryId
        )
    """)
    List<CountryDiseaseDTO> findAllRegionsAndDiseasesForDonor(@Param("donorId") Integer donorId, @Param("oneYearAgo") LocalDate oneYearAgo);

    List<CountryDisease> findAll();

    @Query("""
    SELECT cd
    FROM CountryDisease cd
    JOIN FETCH cd.country c
    LEFT JOIN FETCH cd.region r
    LEFT JOIN FETCH cd.disease d
    WHERE c.countryId = :countryId
    """)
    List<CountryDisease> findByCountryCountryId(@Param("countryId") Integer countryId);
}