package uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes;

import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DecisionAndNotesController {

    private final DecisionAndNotesService decisionAndNotesService;

    public DecisionAndNotesController(DecisionAndNotesService decisionAndNotesService) {
        this.decisionAndNotesService = decisionAndNotesService;
    }

    @GetMapping("/decisionAndNotes/{donorId}")
    public ModelAndView decisionAndNotes(@PathVariable int donorId) {
        ModelAndView modelAndView = new ModelAndView("DecisionAndNotes/decisionAndNotesPage");
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
    public ModelAndView submitDecisions(@Valid @ModelAttribute("donor") DonorDetails donorDetails,
                                        BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("DecisionAndNotes/decisionAndNotesPage");
            try {
                DonorDetails existingDonorDetails = decisionAndNotesService.getDonorInfo(donorDetails.getDonorId());
                modelAndView.addObject("donor", existingDonorDetails);
                modelAndView.addObject("decision", existingDonorDetails.getDecision());
                modelAndView.addObject("notes", existingDonorDetails.getNotes());
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                modelAndView.addObject("donor", null);  // Set donor as null if not found
                modelAndView.addObject("error", "No donor found for ID: " + donorDetails.getDonorId());
            }
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            return modelAndView;
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Update the database with the decision and notes
        decisionAndNotesService.updateDonorDecisionAndNotes(donorDetails.getDonorId(), donorDetails.getDecision(), donorDetails.getNotes(), username);

        // Redirect to confirmation page with donorId as a query parameter
        modelAndView.setViewName("redirect:/confirmation?donorId=" + donorDetails.getDonorId());
        return modelAndView;
    }


    @GetMapping("/confirmation")
    public String confirmationPage(@RequestParam("donorId") int donorId, Model model) {
        // Add donorId to the model
        model.addAttribute("donorId", donorId);
        return "DecisionAndNotes/confirmation";
    }
}





