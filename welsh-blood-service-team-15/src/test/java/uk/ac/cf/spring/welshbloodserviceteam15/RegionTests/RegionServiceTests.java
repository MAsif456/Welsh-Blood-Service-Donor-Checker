package uk.ac.cf.spring.welshbloodserviceteam15.RegionTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.RegionRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.RegionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class RegionServiceTests {

    @Autowired
    RegionService regionService;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegionRepository regionRepository;

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
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testRegionsReturns() throws Exception {
        // Given
        Region region = makeRegion();
        Country country = makeCountry();
        region.setCountry(country);

        // When
        when(regionService.getAllRegions()).thenReturn(List.of(region));

        // Then
        assertEquals(1, regionService.getAllRegions().size());
        assertEquals("Wales", regionService.getAllRegions().get(0).getRegionName());
        assertEquals(1, regionService.getAllRegions().get(0).getRegionId());
        assertEquals(1, regionService.getAllRegions().get(0).getCountry().getCountryId());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testRegionByCountryReturns() throws Exception {
        // Given
        Region region = makeRegion();
        Country country = makeCountry();
        region.setCountry(country);

        // When
        when(regionService.getRegionByCountry(1)).thenReturn(List.of(region));

        // Then
        assertEquals(1, regionService.getRegionByCountry(1).size());
        assertEquals("Wales", regionService.getRegionByCountry(1).get(0).getRegionName());
        assertEquals(1, regionService.getRegionByCountry(1).get(0).getCountry().getCountryId());
        assertEquals("United Kingdom", regionService.getRegionByCountry(1).get(0).getCountry().getCountryName());
    }
}
