package uk.ac.cf.spring.welshbloodserviceteam15.Country.Region;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public List<Region> getRegionByCountry(Integer countryId) {
        return regionRepository.findByCountry_CountryId(countryId);
    }

}
