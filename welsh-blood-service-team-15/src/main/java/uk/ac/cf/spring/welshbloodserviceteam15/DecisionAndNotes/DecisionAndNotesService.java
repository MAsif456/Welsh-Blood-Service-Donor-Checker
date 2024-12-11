package uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes;

import org.springframework.stereotype.Service;

@Service
public interface DecisionAndNotesService {
    DonorDetails getDonorInfo(int donorId);
    void updateDonorDecisionAndNotes(int donorId, String decision, String notes, String username);

}
