package uk.ac.cf.spring.welshbloodserviceteam15.Donor;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseDTO;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.DiseaseService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questionnaire;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.QuestionnaireService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;
import uk.ac.cf.spring.welshbloodserviceteam15.Utils.ModelUtils;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class DonorController {

    @Autowired
    private DonorService donorService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping("/donor/summary-risks/{donorId}")
    public String getDonorSummaryWthRisks(@PathVariable Integer donorId, Model model) {
        Donor donor = donorService.getDonorSummary(donorId);

        List<CountryDiseaseDTO> diseases = donorService.getRelevantDiseasesForDonor(donorId);

        // Set to handle duplicates
        Set<CountryDiseaseDTO> uniqueDiseases = new HashSet<>(diseases);
        List<CountryDiseaseDTO> sortedDiseases = diseaseService.sortDiseasesByRisk(new ArrayList<>(uniqueDiseases));

        model.addAttribute("diseases", sortedDiseases);


        List<Questionnaire> donorsAnswers = questionnaireService.getDonorsAnswers(donorId);
        List<Questions> questions = questionService.getQuestions();

        ModelUtils.addCommonAttributesToModel(model, donor, donorsAnswers, questions);

        return "Donor/donorSummaryAndRisks";
    }


    @GetMapping("/donor/summary/{donorId}")
    public String getDonorSummary(@PathVariable Integer donorId, Model model) {
        // Get the donor summary
        Donor donor = donorService.getDonorSummary(donorId);
        //model.addAttribute("donor", donor);

        // Get the donor's answers
        List<Questionnaire> donorsAnswers = questionnaireService.getDonorsAnswers(donorId);

        // Get the list of all questions
        List<Questions> questions = questionService.getQuestions();

        // Ensure the questions list is not null
        ModelUtils.addCommonAttributesToModel(model, donor, donorsAnswers, questions);


        return "Donor/donorSummary";
    }
    @GetMapping("/donor-form")
    public String showDonorForm(Model model) {
        model.addAttribute("donorForm", new DonorForm());
        return "Donor/donor-form";
    }

    @PostMapping("/donor-form")
    public ModelAndView submitDonorForm(@Valid DonorForm donorForm, BindingResult bindingResult, Model model) {
        ModelAndView modelAndView;
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("Donor/donor-form", model.asMap());
            return modelAndView;
        } else {
            Donor savedDonor = donorService.saveDonor(donorForm);
            modelAndView = new ModelAndView("redirect:/travel-form/" + savedDonor.getDonorId());
            return modelAndView;
        }
    }

    @GetMapping("/donor-records")
    public ModelAndView showDonorRecords(@RequestParam(value = "search", required = false)String search) {
        ModelAndView modelAndView = new ModelAndView("Donor/donorRecords");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        boolean isSearchPerformed = search != null;

        if (role.equals("ROLE_CLINICIAN")) {
            List<Donor> pendingDonors = donorService.getAllDonors().stream()
                    .filter(donor -> donor.getDonorDecision() == null && donor.getDonorNote() == null)
                    .collect(Collectors.toList());
            modelAndView.addObject("donors", pendingDonors);
        } else if (role.equals("ROLE_ADMIN")) {
            modelAndView.addObject("donors", List.of()); // Empty list for admins
        }
        modelAndView.addObject("isSearchPerformed", isSearchPerformed);
        modelAndView.addObject("noRecordsFound", isSearchPerformed && donorService.getAllDonors().isEmpty());
        return modelAndView;
    }

    @GetMapping("/search-donor")
    public String searchDonor(@RequestParam(value = "id", required = false) Integer id, Model model) {
        boolean isSearchPerformed = id != null;
        if (id == null) {
            model.addAttribute("error", "An ID is required to search.");
        } else {
            Donor donor = donorService.searchDonorById(id);
            if (donor == null) {
                model.addAttribute("error", "No donor found with the provided ID.");
            } else {
                model.addAttribute("donors", List.of(donor));
            }
        }
        model.addAttribute("isSearchPerformed", isSearchPerformed);
        return "Donor/donorRecords";
    }

}
