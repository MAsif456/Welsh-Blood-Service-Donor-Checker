package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountry;

public interface DonorTravelledToCountryRepository extends JpaRepository<DonorTravelledToCountry, Long> {
}