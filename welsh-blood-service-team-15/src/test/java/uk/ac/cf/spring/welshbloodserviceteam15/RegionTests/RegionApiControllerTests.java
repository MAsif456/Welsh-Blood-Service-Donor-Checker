package uk.ac.cf.spring.welshbloodserviceteam15.RegionTests;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.RegionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegionApiControllerTests {

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

        Region region2 = new Region();
        region2.setRegionId(2);
        region2.setRegionName("England");
        region2.setCountry(country);

        // When
        when(regionRepository.findAll()).thenReturn(List.of(region, region2));
        MvcResult result = mvc
                .perform(get("/api/region/get-regions"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // Then
        JSONArray regions = new JSONArray(result.getResponse().getContentAsString());
        assertEquals(2, regions.length());
        assertEquals(1, regions.getJSONObject(0).getInt("regionId"));
        assertEquals("Wales", regions.getJSONObject(0).getString("regionName"));
        assertEquals(1, regions.getJSONObject(0).getJSONObject("country").getInt("countryId"));
        assertEquals("United Kingdom", regions.getJSONObject(0).getJSONObject("country").getString("countryName"));
        assertEquals(2, regions.getJSONObject(1).getInt("regionId"));
        assertEquals("England", regions.getJSONObject(1).getString("regionName"));
        assertEquals(1, regions.getJSONObject(1).getJSONObject("country").getInt("countryId"));
        assertEquals("United Kingdom", regions.getJSONObject(1).getJSONObject("country").getString("countryName"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldGetSpecificRegion() throws Exception {
        // Given
        Region region = makeRegion();
        Country country = makeCountry();
        region.setCountry(country);

        // When
        when(regionRepository.findByCountry_CountryId(1)).thenReturn(List.of(region));
        MvcResult result = mvc
                .perform(get("/api/region/get-region?countryId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // Then
        JSONArray regions = new JSONArray(result.getResponse().getContentAsString());
        assertEquals(1, regions.length());
        assertEquals(1, regions.getJSONObject(0).getInt("regionId"));
        assertEquals("Wales", regions.getJSONObject(0).getString("regionName"));
        assertEquals(1, regions.getJSONObject(0).getJSONObject("country").getInt("countryId"));
        assertEquals("United Kingdom", regions.getJSONObject(0).getJSONObject("country").getString("countryName"));
    }

}
