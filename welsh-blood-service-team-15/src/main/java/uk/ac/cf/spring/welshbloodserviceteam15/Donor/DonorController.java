package uk.ac.cf.spring.welshbloodserviceteam15.Donor;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseDTO;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questionnaire;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.QuestionnaireService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;

import java.util.List;
import java.util.Map;
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

    @GetMapping("/donor/summary-risks/{donorId}")
    public String getDonorSummaryWthRisks(@PathVariable Integer donorId, Model model) {
        Donor donor = donorService.getDonorSummary(donorId);
        List<CountryDiseaseDTO> diseases = donorService.getRelevantDiseasesForDonor(donorId);
        model.addAttribute("donor", donor);
        model.addAttribute("diseases", diseases);

        // Get the donor's answers
        List<Questionnaire> donorsAnswers = questionnaireService.getDonorsAnswers(donorId);
        model.addAttribute("donorsAnswers", donorsAnswers);

        // Get the list of all questions
        List<Questions> questions = questionService.getQuestions();

        // Ensure the questions list is not null
        if (questions != null) {
            // Map question ID to question text for easy lookup in Thymeleaf
            Map<Integer, String> questionMap = questions.stream()
                    .filter(q -> q.getQuestion_id() != null && q.getQuestion() != null)
                    .collect(Collectors.toMap(Questions::getQuestion_id, Questions::getQuestion));

            model.addAttribute("questionMap", questionMap);
        }

        return "Donor/donorSummaryAndRisks";
    }

    @GetMapping("/donor/summary/{donorId}")
    public String getDonorSummary(@PathVariable Integer donorId, Model model) {
        // Get the donor summary
        Donor donor = donorService.getDonorSummary(donorId);
        model.addAttribute("donor", donor);

        // Get the donor's answers
        List<Questionnaire> donorsAnswers = questionnaireService.getDonorsAnswers(donorId);
        model.addAttribute("donorsAnswers", donorsAnswers);

        // Get the list of all questions
        List<Questions> questions = questionService.getQuestions();

        // Ensure the questions list is not null
        if (questions != null) {
            // Map question ID to question text for easy lookup in Thymeleaf
            Map<Integer, String> questionMap = questions.stream()
                    .filter(q -> q.getQuestion_id() != null && q.getQuestion() != null)
                    .collect(Collectors.toMap(Questions::getQuestion_id, Questions::getQuestion));

            model.addAttribute("questionMap", questionMap);
        }

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
            modelAndView = new ModelAndView("Donor/donor-form",model.asMap());
            return modelAndView;
        }
        else {
            Donor donor = new Donor();
            donor.setFirstName(donorForm.getFirstName());
            donor.setLastName(donorForm.getLastName());
            donor.setDonorDob(donorForm.getDonorDob());
            Donor savedDonor = donorRepository.save(donor);
            modelAndView = new ModelAndView("redirect:/travel-form/" + savedDonor.getDonorId());
            return modelAndView;
        }
    }
}
