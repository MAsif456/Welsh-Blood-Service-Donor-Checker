package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Disease;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.DiseaseService;
import java.util.List;



@Controller
@RequestMapping("/edit-disease")
public class CountryDiseaseController {

    private static final Logger logger = LoggerFactory.getLogger(CountryDiseaseController.class);

    @Autowired
    private CountryDiseaseRepository countryDiseaseRepository;

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private CountryDiseaseService countryDiseaseService;


    @GetMapping
    public String editCountryDisease(Model model) {
        List<Country> countries = countryDiseaseService.getAllCountries();
        model.addAttribute("countries", countries);
        return "CountryDisease/EditCountryDisease";
    }

    @GetMapping("/view")
    public String viewDiseaseRisks(@RequestParam("countryId") Integer countryId, Model model) {
        List<CountryDisease> diseaseRisks = countryDiseaseRepository.findByCountryCountryId(countryId);
        model.addAttribute("diseaseRisks", diseaseRisks);
        List<Country> countries = countryDiseaseService.getAllCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("selectedCountry", countryId);
        return "CountryDisease/EditCountryDisease";
    }


    @GetMapping("/form")
    public String editDiseaseForm(@RequestParam("id") Integer id, Model model) {
        CountryDisease countryDisease = countryDiseaseService.findById(id);
        if (countryDisease == null) {
            countryDisease = new CountryDisease();
            countryDisease.setDisease(new Disease());
        } else if (countryDisease.getDisease() == null) {
            countryDisease.setDisease(new Disease());
        }
        model.addAttribute("countryDisease", countryDisease);
        return "CountryDisease/EditDiseaseForm";
    }


    @PostMapping("/save")
    public String saveDisease(@ModelAttribute @Valid CountryDisease countryDisease, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("countryDisease", countryDisease);
            return "CountryDisease/EditDiseaseForm";  // Return the form view with errors
        }

        if (countryDisease.getCountry() == null) {
            bindingResult.rejectValue("country", "error.countryDisease", "Country must not be null");
            logger.error("Country is null for CountryDisease: {}", countryDisease);
            model.addAttribute("countryDisease", countryDisease);
            return "CountryDisease/EditDiseaseForm";
        }

        Disease disease = countryDisease.getDisease();
        if (disease != null && disease.getDiseaseId() == null) {
            diseaseService.save(disease);
        }

        countryDiseaseService.save(countryDisease);

        return "redirect:/edit-disease/SuccessfulEdit";  // Ensure this matches the test    }
    }

    @GetMapping("/SuccessfulEdit")
    public String showSuccessPage() {
        return "CountryDisease/SuccessfulEdit"; // View template name
    }
}