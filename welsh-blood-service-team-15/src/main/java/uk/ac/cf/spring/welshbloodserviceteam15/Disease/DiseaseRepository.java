package uk.ac.cf.spring.welshbloodserviceteam15.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
    Optional<Disease> findByDiseaseName(String diseaseName);

}
