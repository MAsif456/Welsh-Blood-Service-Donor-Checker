package uk.ac.cf.spring.welshbloodserviceteam15;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorDonorTravelledToCountryService;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountry;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountryRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;
import uk.ac.cf.spring.welshbloodserviceteam15.travelform.CountryService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    private final QuestionService questionService;
    private final CountryService countryService;
    private final DonorDonorTravelledToCountryService donorTravelledToCountryService;

    public TestController(QuestionService questionService, CountryService countryService, DonorDonorTravelledToCountryService donorTravelledToCountryService) {
        this.questionService = questionService;
        this.countryService = countryService;
        this.donorTravelledToCountryService = donorTravelledToCountryService;
    }

    @RequestMapping("/new-travel-form/{donorId}")
    public ModelAndView showTravelForm(@PathVariable int donorId) {
        ModelAndView mv =  new ModelAndView("travelform/newTravelForm");

        List<Questions> questions = questionService.getQuestions();
        List<Country> countries = countryService.getAllCountries();
        List<DonorTravelledToCountry> donorTravelledToCountries = donorTravelledToCountryService.findByDonorId(donorId);

        mv.addObject("questions", questions);
        mv.addObject("countries", countries);
        mv.addObject("donorId", donorId);
        mv.addObject("donorTravelledToCountries", donorTravelledToCountries);
        return mv;
    }

    @RequestMapping("/new-travel-form/submit")
    public String submitNewTravelForm(
            @RequestParam("donorId") String donorId,
            @RequestParam("question1") String question1,
            @RequestParam("question4") String question4,
            @RequestParam("country") String countryId,
            @RequestParam(value = "region", required = false) List<String> regionIds, // Optional parameter
            @RequestParam("date") String date,
            Model model,
            RedirectAttributes redirectAttributes) {

        int donorIdInt = 0;
        int countryIdInt = 0;
        List<Integer> regionIdInts = new ArrayList<>();

        try {
            donorIdInt = Integer.parseInt(donorId); // Manually parse donorId
            countryIdInt = Integer.parseInt(countryId); // Manually parse countryId

            // If regionIds isn't sent in the form, it will be null. Initialize it if necessary.
            if (regionIds != null) {
                for (String regionId : regionIds) {
                    regionIdInts.add(Integer.parseInt(regionId)); // Manually parse regionId
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid donorId format: " + donorId);
            // Handle the error (e.g., return an error message or redirect)
        }

        if (question1.equals("Yes")) {
            // has travelled in the last 12 months
            if (regionIdInts.isEmpty()) {
                // No regions selected
                donorTravelledToCountryService.saveFromForm(donorIdInt, countryIdInt, date);
            }
            for (int regionIdInt : regionIdInts) {
                donorTravelledToCountryService.saveFromForm(donorIdInt, countryIdInt, regionIdInt, date);
            }

        } else if (question4.equals("Yes")) {
            // has lived abroad for more than 6 months
            for (int regionIdInt : regionIdInts) {
                donorTravelledToCountryService.saveFromForm(donorIdInt, countryIdInt, regionIdInt, date);
            }
        }

        redirectAttributes.addAttribute("donorId", donorIdInt);
        return "redirect:/new-travel-form/{donorId}";
    }


}
