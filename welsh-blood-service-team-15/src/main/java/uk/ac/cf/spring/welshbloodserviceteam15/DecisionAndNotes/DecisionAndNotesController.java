package uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DecisionAndNotesController {

    @Autowired
    private DecisionAndNotesService decisionAndNotesService;


    @GetMapping("/decisionAndNotes/{donorId}")
    public ModelAndView decisionAndNotes(@PathVariable int donorId) {
        ModelAndView modelAndView = new ModelAndView("decisionAndNotesPage");
        try {
            DonorDetails donorDetails = decisionAndNotesService.getDonorInfo(donorId);
            modelAndView.addObject("donor", donorDetails);
            modelAndView.addObject("decision", donorDetails.getDecision());
            modelAndView.addObject("notes", donorDetails.getNotes());
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            modelAndView.addObject("donor", null);  // Set donor as null if not found
            modelAndView.addObject("error", "No donor found for ID: " + donorId);
        }
        return modelAndView;
    }

    @PostMapping("/submit-decisions")
    public String submitDecisions(@RequestParam("donorId") int donorId,
                                  @RequestParam("decision") String decision,
                                  @RequestParam("notes") String notes) {
        // Update the database with the decision and notes
        decisionAndNotesService.updateDonorDecisionAndNotes(donorId, decision, notes);

        // Redirect to confirmation page with donorId as a query parameter
        return "redirect:/confirmation?donorId=" + donorId;
    }

    @GetMapping("/confirmation")
    public String confirmationPage(@RequestParam("donorId") int donorId, Model model) {
        // Add donorId to the model
        model.addAttribute("donorId", donorId);
        return "confirmation";
    }
}





