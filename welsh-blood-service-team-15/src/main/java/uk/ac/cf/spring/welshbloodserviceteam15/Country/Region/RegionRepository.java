package uk.ac.cf.spring.welshbloodserviceteam15.Country.Region;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    List<Region> findByCountry_CountryId(Integer countryId);}
