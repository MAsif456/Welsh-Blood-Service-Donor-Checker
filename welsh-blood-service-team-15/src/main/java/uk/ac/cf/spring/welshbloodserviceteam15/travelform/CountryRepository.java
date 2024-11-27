package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
