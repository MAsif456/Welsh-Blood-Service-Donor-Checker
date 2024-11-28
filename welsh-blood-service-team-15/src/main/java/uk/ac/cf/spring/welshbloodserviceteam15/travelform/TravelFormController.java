package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountry;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class TravelFormController {

    @Autowired
    private TravelQuestionnaireService travelQuestionnaireService;

    @Autowired
    private DonorTravelledToCountryService donorTravelledToCountryService;

    @Autowired
    private CountryService countryService;

    private static final Logger logger = LoggerFactory.getLogger(TravelFormController.class);

    @GetMapping("/travel-form/{donorId}")
    public String showTravelForm(@PathVariable Long donorId, Model model) {
        List<String> questions = List.of(
                "Have you travelled to a country within the last 12 months?",
                "Where have you travelled in the last 12 months?",
                "When did you go to this country?",
                "When did you return from this country?",
                "Have you been in contact with anyone who has travelled to a country within the last 12 months?",
                "Where did they travel to?",
                "When did they go to this country?",
                "When did they return from this country?",
                "Have you lived abroad for more than 6 months?",
                "Where have you lived abroad?",
                "When did you go abroad?",
                "When did you return from abroad?",
                "Have you been in contact with anyone who has lived abroad for more than 6 months?",
                "Where did they live abroad?",
                "When did they live abroad?",
                "When did they return from abroad?",
                "Have you travelled through multiple countries whilst travelling abroad?",
                "Which countries have you travelled through?",
                "When did you go to these countries?",
                "When did you return from these countries?",
                "Have you been in contact with anyone who has travelled through multiple countries whilst travelling abroad?",
                "Which countries did they travel through?",
                "When did they go to these countries?",
                "When did they return from these countries?"
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
        List<Long> generalQuestionIds = List.of(1L, 5L, 9L, 13L, 17L, 21L);


        // Save general answers to travel_questionnaire
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.startsWith("question")) {
                Long questionId = Long.parseLong(key.replace("question", ""));
                if (generalQuestionIds.contains(questionId)) {
                    TravelQuestionnaire questionnaire = new TravelQuestionnaire();
                    questionnaire.setDonorId(donorId);
                    questionnaire.setQuestionId(questionId);
                    questionnaire.setAnswer(value);
                    travelQuestionnaireService.saveQuestionnaire(questionnaire);
                }
            }
        }

        // Save conditional answers to donor_travelled_to_country
        if ("Yes".equals(formData.get("question1"))) {
            saveDonorTravel(formData, donorId, "question2", "question3", "question4");
        }
        if ("Yes".equals(formData.get("question9"))) {
            saveDonorTravel(formData, donorId, "question10", "question11", "question12");
        }
        if ("Yes".equals(formData.get("question17"))) {
            saveDonorTravel(formData, donorId, "question18", "question19", "question20");
        }

        model.addAttribute("donorId", donorId);

        return "redirect:/donor/summary/" + donorId;
    }

    private void saveDonorTravel(Map<String, String> formData, Long donorId, String countryKey, String startDateKey, String endDateKey) {
        String countryIdStr = formData.get(countryKey);
        String startDateStr = formData.get(startDateKey);
        String endDateStr = formData.get(endDateKey);

        if (countryIdStr != null && startDateStr != null && endDateStr != null) {
            DonorTravelledToCountry donorTravel = new DonorTravelledToCountry();
            Donor donor = new Donor();
            donor.setDonorId(donorId.intValue()); // Convert Long to Integer
            donorTravel.setDonor(donor);
            Country country = new Country();
            country.setCountryId(Integer.parseInt(countryIdStr));
            donorTravel.setCountry(country);
            donorTravel.setStartDate(LocalDate.parse(startDateStr));
            donorTravel.setEndDate(LocalDate.parse(endDateStr));
            donorTravelledToCountryService.saveDonorTravel(donorTravel);
        } else {
            throw new IllegalArgumentException("Missing required fields for donor travel");
        }
    }


}


