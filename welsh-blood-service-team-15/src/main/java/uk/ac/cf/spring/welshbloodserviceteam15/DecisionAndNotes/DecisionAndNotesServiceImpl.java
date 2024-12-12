package uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Transactional
@Service
public class DecisionAndNotesServiceImpl implements DecisionAndNotesService {

    @Autowired
    private DecisionAndNotesRepository decisionAndNotesRepository;

    @Override
    public DonorDetails getDonorInfo(int donorId) {
        Optional<DonorDetails> optionalDonor = decisionAndNotesRepository.getDonorRecord(donorId);
        return optionalDonor.orElseThrow(() -> new RuntimeException("No donor found for donorId=" + donorId));
    }

    @Override
    public void updateDonorDecisionAndNotes(int donorId, String decision, String notes, String username) {
        decisionAndNotesRepository.updateDonorRecord(donorId, decision, notes, username);
    }
}
