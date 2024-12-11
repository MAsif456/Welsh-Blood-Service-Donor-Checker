package uk.ac.cf.spring.welshbloodserviceteam15.RegionTests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.RegionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class RegionServiceTests {

    @MockBean
    RegionService regionService;

    private Region makeRegion() {
        Region region = new Region();
        region.setRegionId(1);
        region.setRegionName("Wales");
        return region;
    }

    private Country makeCountry() {
        Country country = new Country();
        country.setCountryId(1);
        country.setCountryName("United Kingdom");
        return country;
    }

    @Test
    public void testRegionsReturns() {
        // Given
        Region region = makeRegion();
        Country country = makeCountry();
        region.setCountry(country);

        // When
        when(regionService.getAllRegions()).thenReturn(List.of(region));
        List<Region> regions = regionService.getAllRegions();

        // Then
        assertEquals(1, regions.size());
        assertEquals("Wales", regions.get(0).getRegionName());
        assertEquals(1, regions.get(0).getCountry().getCountryId());
        assertEquals("United Kingdom", regions.get(0).getCountry().getCountryName());
    }

    @Test
    public void testRegionByCountryReturns() {
        // Given
        Region region = makeRegion();
        Country country = makeCountry();
        region.setCountry(country);

        // When
        when(regionService.getRegionByCountry(1)).thenReturn(List.of(region));
        List<Region> regions = regionService.getRegionByCountry(1);

        // Then
        assertEquals(1, regions.size());
        assertEquals("Wales", regions.get(0).getRegionName());
        assertEquals(1, regions.get(0).getCountry().getCountryId());
        assertEquals("United Kingdom", regions.get(0).getCountry().getCountryName());
    }
}
