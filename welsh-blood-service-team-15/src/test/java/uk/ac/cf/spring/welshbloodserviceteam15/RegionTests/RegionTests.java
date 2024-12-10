package uk.ac.cf.spring.welshbloodserviceteam15.RegionTests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class RegionTests {

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
    public void testRegion() {
        Region region = makeRegion();
        Country country = makeCountry();
        region.setCountry(country);
        assertEquals(1, region.getRegionId());
        assertEquals("Wales", region.getRegionName());
        assertEquals(country, region.getCountry());
    }
}
