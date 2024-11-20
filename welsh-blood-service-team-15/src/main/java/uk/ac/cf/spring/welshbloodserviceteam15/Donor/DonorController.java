package uk.ac.cf.spring.welshbloodserviceteam15.Donor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;
import java.util.List;

@Controller
public class DonorController {

    @Autowired
    private DonorService donorService;

    @GetMapping("/donor/summary-risks/{donorId}")
    public String getDonorSummaryWthRisks(@PathVariable Integer donorId, Model model) {
        Donor donor = donorService.getDonorSummary(donorId);
        List<CountryDisease> diseases = donorService.getDiseasesForDonorTravel(donorId);

        model.addAttribute("donor", donor);
        model.addAttribute("diseases", diseases);

        return "Donor/donorSummaryAndRisks";
    }
}
