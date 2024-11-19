package uk.ac.cf.spring.welshbloodserviceteam15;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DecisionAndNotesController {
    @GetMapping("/decisionAndNotes")
    public ModelAndView decisionAndNotes() {
        return new ModelAndView("decisionAndNotesPage");
    }
}
