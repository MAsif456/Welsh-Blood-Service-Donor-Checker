package uk.ac.cf.spring.welshbloodserviceteam15.Country.Region;

import java.util.List;

public interface RegionService {
    List<Region> getAllRegions();

    List<Region> getRegionByCountry(Integer countryId);

    Region saveRegion(String regionName, Integer countryId);


}
