package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TravelFormController {

    private final TravelQuestionnaireService travelQuestionnaireService;
    private final TravelFormService travelFormService;
    private final CountryService countryService;

    @Autowired
    public TravelFormController(TravelQuestionnaireService travelQuestionnaireService,
                                TravelFormService travelFormService,
                                CountryService countryService) {
        this.travelQuestionnaireService = travelQuestionnaireService;
        this.travelFormService = travelFormService;
        this.countryService = countryService;
    }

    @GetMapping("/travel-form/{donorId}")
    public String showTravelForm(@PathVariable Long donorId, Model model) {
        List<String> questions = List.of(
                "Have you travelled abroad in the past 12 months?",
                "Where have you travelled in the past 12 months?",
                "When did you return from this country?",
                "Have you lived abroad for more than 6 months?",
                "Where have you lived abroad?",
                "When did you return from this country?"
        );

        List<Country> countries = countryService.getAllCountries(); // Fetch countries

        model.addAttribute("questions", questions);
        model.addAttribute("countries", countries);
        model.addAttribute("formData", new FormData());
        model.addAttribute("donorId", donorId);
        return "travelform/travelForm";
    }

    @PostMapping("/submit-travel-form")
    public String submitTravelForm(@RequestParam Map<String, String> formData, @RequestParam Long donorId, Model model) {
        // List of general question IDs
        List<Long> generalQuestionIds = List.of(1L, 4L);

        // List of region IDs
        List<Long> regionIds = new ArrayList<>();

        // Save general answers to travel_questionnaire
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.startsWith("question") && !key.contains("_")) {
                Long questionId = Long.parseLong(key.replace("question", ""));
                if (generalQuestionIds.contains(questionId)) {
                    TravelQuestionnaire questionnaire = new TravelQuestionnaire();
                    questionnaire.setDonorId(donorId);
                    questionnaire.setQuestionId(questionId);
                    questionnaire.setAnswer(value);
                    travelQuestionnaireService.saveQuestionnaire(questionnaire);
                }
            }

            if (key.startsWith("regions")) {
                // Remove the brackets and quotes
                value = value.substring(1, value.length() - 1) // Remove outer square brackets
                        .replace("\"", "");             // Remove double quotes

                // Split the string by commas
                String[] splitValues = value.split(",");

                // Iterate through the value array and add doubles to the list
                for (String val : splitValues) {
                    try {
                        long number = Long.parseLong(val);
                        regionIds.add(number);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number: " + val);
                    }
                }
            }
        }
        // Save conditional answers to donor_travelled_to_country for question 4
        if ("Yes".equals(formData.get("question4"))) {
            String countryPrefix = "question5";
            String datePrefix = "question6";
            String regionPrefix = "region_";
            travelFormService.saveDonorTravel(formData, donorId, countryPrefix, datePrefix, regionPrefix);
        }


        // loop through the regionIds and save them to the database
        if (regionIds.isEmpty()) {
            // Save conditional answers to donor_travelled_to_country for question 1
            if ("Yes".equals(formData.get("question1"))) {
                String countryPrefix = "question2_";
                String datePrefix = "question3_";
                String regionPrefix = "region_";
                travelFormService.saveDonorTravel(formData, donorId, countryPrefix, datePrefix, regionPrefix);
            }
        }
        else {
            // loop through the regionIds and save them to the database
            regionIds.forEach(regionId -> {
                System.out.println(regionId);
                String countryPrefix = "question2_";
                String datePrefix = "question3_";
                travelFormService.saveDonorTravelRegion(formData, donorId, countryPrefix, datePrefix, regionId);
            });
        }

        model.addAttribute("donorId", donorId);
        return "redirect:/donor/summary/" + donorId;
    }




}