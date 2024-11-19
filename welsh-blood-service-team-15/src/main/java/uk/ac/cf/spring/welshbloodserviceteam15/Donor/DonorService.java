package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    public Donor getDonorSummary(Integer donorId) {
        return donorRepository.findByDonorId(donorId);
    }
}