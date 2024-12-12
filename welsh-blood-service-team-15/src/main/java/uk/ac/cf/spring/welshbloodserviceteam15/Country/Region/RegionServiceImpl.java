package uk.ac.cf.spring.welshbloodserviceteam15.Country.Region;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryService;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    private final CountryService countryService;

    public RegionServiceImpl(RegionRepository regionRepository, CountryService countryService) {
        this.regionRepository = regionRepository;
        this.countryService = countryService;
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public List<Region> getRegionByCountry(Integer countryId) {
        return regionRepository.findByCountry_CountryId(countryId);
    }

    public Region saveRegion(String regionName, Integer countryId) {

        Region newRegion = new Region();
        newRegion.setRegionName(regionName);
        Country country = countryService.getCountryById(countryId);
        newRegion.setCountry(country);

        return regionRepository.save(newRegion);
    }

}
