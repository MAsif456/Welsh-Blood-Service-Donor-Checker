package uk.ac.cf.spring.welshbloodserviceteam15.Donor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questionnaire;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.QuestionnaireService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionService;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class DonorController {

    @Autowired
    private DonorService donorService;
    @Autowired
    private QuestionnaireService QuestionnaireService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/donor/summary-risks/{donorId}")
    public String getDonorSummaryWthRisks(@PathVariable Integer donorId, Model model) {
        Donor donor = donorService.getDonorSummary(donorId);
        List<CountryDisease> diseases = donorService.getRelevantDiseasesForDonor(donorId);

        model.addAttribute("donor", donor);
        model.addAttribute("diseases", diseases);

        return "Donor/donorSummaryAndRisks";
    }


    @GetMapping("/donor/summary/{donorId}")
    public String getDonorSummary(@PathVariable Integer donorId, Model model) {
        // Get the donor summary
        Donor donor = donorService.getDonorSummary(donorId);
        model.addAttribute("donor", donor);

        // Get the donor's answers
        List<Questionnaire> donorsAnswers = QuestionnaireService.getDonorsAnswers(donorId);
        model.addAttribute("donorsAnswers", donorsAnswers);

        // Get the list of all questions
        List<Questions> questions = questionService.getQuestions();
        Map<Integer, Questions> questionMap = questions.stream()
                .collect(Collectors.toMap(Questions::getQuestion_id, Function.identity()));
        model.addAttribute("questionMap", questionMap);

        return "Donor/donorSummary";
    }
}
