package uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

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
            decisionAndNotesService.updateDonorDecisionAndNotes(donorId, decision, notes);
            System.out.println("Received: donorId=" + donorId + ", decision=" + decision + ", notes=" + notes);
            return "redirect:/confirmation";
        }

        //This page is here for now so that we can test the submit-decisions form
        //This page will be removed/altered later
    @GetMapping("/confirmation")
    public String confirmationPage() {
        return "confirmation";
    }

    }




