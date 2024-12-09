package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.cf.spring.welshbloodserviceteam15.travelform.CountryService;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;

import java.util.List;

@Controller
public class CountryDiseaseController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryDiseaseRepository countryDiseaseRepository;


    @GetMapping("/edit-disease")
    public String editCountryDisease(Model model) {

        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        return "CountryDisease/EditCountryDisease";
    }

    @GetMapping("/edit-disease/view{countryId}")
    public String viewDiseaseRisks(@RequestParam("countryId") Integer countryId, Model model) {
        // Fetch disease risks for the selected country
        List<CountryDiseaseDTO> diseaseRisks = countryDiseaseRepository.findDiseasesByCountry(countryId);
        model.addAttribute("diseaseRisks", diseaseRisks);

        // Fetch countries again for the dropdown
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("selectedCountry", countryId);
        return "CountryDisease/EditCountryDisease";
    }


}
