package uk.ac.cf.spring.welshbloodserviceteam15.Country;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryDiseaseRepository extends JpaRepository<CountryDisease, Integer> {
    List<CountryDisease> findByCountryIn(List<Country> countries);
}
