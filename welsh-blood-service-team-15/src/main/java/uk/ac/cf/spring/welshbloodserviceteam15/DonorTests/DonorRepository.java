package uk.ac.cf.spring.welshbloodserviceteam15.DonorTests;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DonorRepository extends JpaRepository<Donor, Integer> {
    Donor findByDonorId(Integer donorId);
}
