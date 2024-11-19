package uk.ac.cf.spring.welshbloodserviceteam15.Donor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DonorController {

    @Autowired
    private DonorService donorService;

    @GetMapping("/donor/summary/{donorId}")
    public String getDonorSummary(@PathVariable Integer donorId, Model model) {
        Donor donor = donorService.getDonorSummary(donorId);

        model.addAttribute("donor", donor);

        return "Donor/donorSummary";
    }
}