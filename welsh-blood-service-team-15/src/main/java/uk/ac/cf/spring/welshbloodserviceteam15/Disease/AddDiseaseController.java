package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryService;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.RegionService;

@Controller
public class AddDiseaseController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping("/disease/add")
    public String showAddDiseaseForm(Model model) {
        model.addAttribute("disease", new DiseaseDTO());
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("regions", regionService.getAllRegions());
        return "Disease/add-disease";
    }

    @PostMapping("/disease/add")
    public String addDisease(@Valid @ModelAttribute("disease") DiseaseDTO diseaseDTO, BindingResult result, Model model) {
        // Checks if the region is new and insert it into the regions table and gives it a new regionId
        if (diseaseDTO.getRegionId() == null || "add-new".equals(diseaseDTO.getRegionId().toString())) {
            if (diseaseDTO.getNewRegionName() != null && !diseaseDTO.getNewRegionName().isEmpty()) {
                Region newRegion = regionService.saveRegion(diseaseDTO.getNewRegionName(), diseaseDTO.getCountryId());
                diseaseDTO.setRegionId(newRegion.getRegionId()); // Set as Integer
            } else {
                diseaseDTO.setRegionId(null); // Set regionId to null if no region is specified
            }
        }

        diseaseService.saveDisease(diseaseDTO);
        return "redirect:/Disease/submission-confirmation";
    }

    @GetMapping("/Disease/submission-confirmation")
    public String showSubmissionPage() {
        return "Disease/submission-confirmation";
    }
}