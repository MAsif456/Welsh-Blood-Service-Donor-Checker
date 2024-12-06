package uk.ac.cf.spring.welshbloodserviceteam15.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorTravelledToCountryRepository extends JpaRepository<DonorTravelledToCountry, Integer> {
    List<DonorTravelledToCountry> findByDonor_DonorId(int donorId);
}
