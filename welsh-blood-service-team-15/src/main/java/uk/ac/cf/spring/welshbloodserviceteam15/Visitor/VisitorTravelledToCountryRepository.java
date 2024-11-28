package uk.ac.cf.spring.welshbloodserviceteam15.Visitor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VisitorTravelledToCountryRepository extends JpaRepository<VisitorTravelledToCountry, Integer> {
    List<VisitorTravelledToCountry> findByDonor_DonorId(Integer donorId);
}
