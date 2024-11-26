package uk.ac.cf.spring.welshbloodserviceteam15.Donor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;
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

    @GetMapping("/donor/summary-risks/{donorId}")
    public String getDonorSummaryWthRisks(@PathVariable Integer donorId, Model model) {
        Donor donor = donorService.getDonorSummary(donorId);
        List<CountryDisease> diseases = donorService.getRelevantDiseasesForDonor(donorId);

        model.addAttribute("donor", donor);
        model.addAttribute("diseases", diseases);

        // Get the donor's answers
        List<Questionnaire> donorsAnswers = questionnaireService.getDonorsAnswers(donorId);
        model.addAttribute("donorsAnswers", donorsAnswers);

        // Get the list of all questions
        List<Questions> questions = questionService.getQuestions();

        // Map question ID to question text for easy lookup in Thymeleaf
        Map<Integer, String> questionMap = questions.stream()
                .collect(Collectors.toMap(Questions::getQuestion_id, Questions::getQuestion));

        // Map question ID to noRiskResponse for easy lookup in Thymeleaf
        Map<Integer, String> noRiskResponseMap = questions.stream()
                .collect(Collectors.toMap(Questions::getQuestion_id, question -> question.getNoRiskResponse()));

        model.addAttribute("noRiskResponseMap", noRiskResponseMap);
        model.addAttribute("questionMap", questionMap);

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

            // Map question ID to noRiskResponse for easy lookup in Thymeleaf
            Map<Integer, String> noRiskResponseMap = questions.stream()
                    .filter(q -> q.getQuestion_id() != null && q.getNoRiskResponse() != null)
                    .collect(Collectors.toMap(Questions::getQuestion_id, Questions::getNoRiskResponse));

            model.addAttribute("noRiskResponseMap", noRiskResponseMap);
            model.addAttribute("questionMap", questionMap);
        }

        return "Donor/donorSummary";
    }
}