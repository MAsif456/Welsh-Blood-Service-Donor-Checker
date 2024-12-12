package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DonorRepository extends JpaRepository<Donor, Integer> {
    Donor findByDonorId(Integer donorId);

    List<Donor> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

}
