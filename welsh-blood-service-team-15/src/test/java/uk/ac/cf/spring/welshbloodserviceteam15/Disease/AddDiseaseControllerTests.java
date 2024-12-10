package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

public class AddDiseaseControllerTests {

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
     * Test to see if the disease form is displayed correctly
     */

    @Test
    public void testShowAddDiseaseForm() {
        when(countryService.getAllCountries()).thenReturn(Collections.emptyList());
        when(regionService.getAllRegions()).thenReturn(Collections.emptyList());

        String viewName = addDiseaseController.showAddDiseaseForm(model);

        verify(model).addAttribute(eq("disease"), any(DiseaseDTO.class));
        verify(model).addAttribute("countries", Collections.emptyList());
        verify(model).addAttribute("regions", Collections.emptyList());
        assertEquals("Disease/add-disease", viewName);
    }


    /**
     * Test to see if a new region is added correctly
     */

    @Test
    public void testAddDisease_NewRegion() {
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

        String viewName = addDiseaseController.addDisease(diseaseDTO, bindingResult, model);

        verify(regionService).saveRegion(any(Region.class));
        verify(diseaseService).saveDisease(diseaseDTO);
        assertEquals("redirect:/Disease/submission-confirmation", viewName);
    }

    /**
     * Test to see if a disease is added correctly
     */

    @Test
    public void testAddDisease_ExistingRegion() {
        DiseaseDTO diseaseDTO = new DiseaseDTO();
        diseaseDTO.setCountryId(1);
        diseaseDTO.setRegionId(1);

        String viewName = addDiseaseController.addDisease(diseaseDTO, bindingResult, model);

        verify(regionService, never()).saveRegion(any(Region.class));
        verify(diseaseService).saveDisease(diseaseDTO);
        assertEquals("redirect:/Disease/submission-confirmation", viewName);
    }

    /**
     * Test to see if the submission page is displayed correctly
     */

    @Test
    public void testShowSubmissionPage() {
        String viewName = addDiseaseController.showSubmissionPage();
        assertEquals("Disease/submission-confirmation", viewName);
    }
}