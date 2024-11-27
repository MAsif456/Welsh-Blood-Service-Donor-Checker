package uk.ac.cf.spring.welshbloodserviceteam15.Country;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryDiseaseRepository extends JpaRepository<CountryDisease, Integer> {
    List<CountryDisease> findByCountryIn(List<Country> countries);
    @Query("SELECT cd FROM CountryDisease cd JOIN DonorTravelledToCountry dtc ON cd.country = dtc.country " +
            "WHERE dtc.donor.donorId = :donorId " +
            "AND dtc.startDate <= cd.endDate AND dtc.endDate >= cd.startDate")
    List<CountryDisease> findRelevantDiseasesForDonor(@Param("donorId") Integer donorId);

    @Query("SELECT cd FROM CountryDisease cd JOIN VisitorTravelledToCountry vt ON cd.country = vt.country " +
            "WHERE vt.donor.donorId = :donorId " +
            "AND vt.startDate <= cd.endDate AND vt.endDate >= cd.startDate")
    List<CountryDisease> findVisitorRelatedRisksForDonor(@Param("donorId") Integer donorId);


}