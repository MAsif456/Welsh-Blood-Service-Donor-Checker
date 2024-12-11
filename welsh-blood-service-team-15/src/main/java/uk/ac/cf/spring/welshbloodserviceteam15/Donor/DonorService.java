package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseDTO;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseRepository;

import java.time.LocalDate;
import java.util.*;

@Service
public class DonorService {

    private final DonorRepository donorRepository;

    private final CountryDiseaseRepository countryDiseaseRepository;

    public DonorService(DonorRepository donorRepository, CountryDiseaseRepository countryDiseaseRepository) {
        this.donorRepository = donorRepository;
        this.countryDiseaseRepository = countryDiseaseRepository;
    }


    public Donor getDonorSummary(Integer donorId) {
        return donorRepository.findByDonorId(donorId);
    }
    public List<CountryDiseaseDTO> getRelevantDiseasesForDonor(Integer donorId) {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        return countryDiseaseRepository.findAllRegionsAndDiseasesForDonor(donorId, oneYearAgo);
    }
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Donor searchDonorById(Integer id) {
        Optional<Donor> donor = donorRepository.findById(id);
        return donor.orElse(null);
    }

    // Testing methods
    public List<CountryDiseaseDTO> getDonorDiseaseRisks(Integer donorId, LocalDate oneYearAgo) {
        return countryDiseaseRepository.findAllRegionsAndDiseasesForDonor(donorId, oneYearAgo);
    }

    public Donor saveDonor(DonorForm donorForm) {
        Donor donor = new Donor();
        donor.setFirstName(donorForm.getFirstName());
        donor.setLastName(donorForm.getLastName());
        donor.setDonorDob(donorForm.getDonorDob());
        donorRepository.save(donor);
        return donor;
    }
}
