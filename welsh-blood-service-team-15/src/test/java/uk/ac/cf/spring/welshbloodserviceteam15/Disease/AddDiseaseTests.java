package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryService;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.RegionService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddDiseaseTests {

    @Mock
    private CountryService countryService;

    @Mock
    private RegionService regionService;

    @Mock
    private DiseaseService diseaseService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AddDiseaseController addDiseaseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test to see if the Add Disease form is displayed correctly
     */

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testShowAddDiseaseForm() {
        // Given
        when(countryService.getAllCountries()).thenReturn(Collections.emptyList());
        when(regionService.getAllRegions()).thenReturn(Collections.emptyList());

        // When
        String viewName = addDiseaseController.showAddDiseaseForm(model);

        // Then
        verify(model).addAttribute(eq("disease"), any(DiseaseDTO.class));
        verify(model).addAttribute("countries", Collections.emptyList());
        verify(model).addAttribute("regions", Collections.emptyList());
        assertEquals("Disease/add-disease", viewName);
    }

    /**
     * Test to see if a disease added to a
     * new region is added correctly
     */

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAddDisease_NewRegion() {
        // Given
        DiseaseDTO diseaseDTO = new DiseaseDTO();
        diseaseDTO.setCountryId(1);
        diseaseDTO.setRegionId(null);
        diseaseDTO.setNewRegionName("New Region");

        Country country = new Country();
        country.setCountryId(1);

        Region newRegion = new Region();
        newRegion.setRegionId(1);

        when(countryService.getCountryById(1)).thenReturn(country);
        when(regionService.saveRegion(any(Region.class))).thenReturn(newRegion);

        // When
        String viewName = addDiseaseController.addDisease(diseaseDTO, bindingResult, model);

        // Then
        verify(regionService).saveRegion(any(Region.class));
        verify(diseaseService).saveDisease(diseaseDTO);
        assertEquals("redirect:/Disease/submission-confirmation", viewName);
    }

    /**
     * Test to see if a disease added to an existing
     * region is added correctly
     */

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAddDisease_ExistingRegion() {
        // Given
        DiseaseDTO diseaseDTO = new DiseaseDTO();
        diseaseDTO.setCountryId(1);
        diseaseDTO.setRegionId(1);

        // When
        String viewName = addDiseaseController.addDisease(diseaseDTO, bindingResult, model);

        // Then
        verify(regionService, never()).saveRegion(any(Region.class));
        verify(diseaseService).saveDisease(diseaseDTO);
        assertEquals("redirect:/Disease/submission-confirmation", viewName);
    }
}