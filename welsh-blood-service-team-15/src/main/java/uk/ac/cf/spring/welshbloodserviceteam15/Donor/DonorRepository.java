package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DonorRepository extends JpaRepository<Donor, Integer> {
    Donor findByDonorId(Integer donorId);
}
