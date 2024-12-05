package uk.ac.cf.spring.welshbloodserviceteam15.Utils;
import org.springframework.ui.Model;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questionnaire;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// This class contains a method that can be reused throughout the application to add common attributes to the model.
public class ModelUtils {
    public static void addCommonAttributesToModel(Model model, Donor donor, List<Questionnaire> donorsAnswers, List<Questions> questions) {
        model.addAttribute("donor", donor);
        model.addAttribute("donorsAnswers", donorsAnswers);

        // Check if questions is not null and contains data
        if (questions != null && !questions.isEmpty()) {
            Map<Integer, String> questionMap = questions.stream()
                    .filter(q -> q.getQuestion_id() != null && q.getQuestion() != null)
                    .collect(Collectors.toMap(Questions::getQuestion_id, Questions::getQuestion));
            model.addAttribute("questionMap", questionMap);
        } else {
            model.addAttribute("error", "Questions data is missing or invalid.");
        }
    }
}
