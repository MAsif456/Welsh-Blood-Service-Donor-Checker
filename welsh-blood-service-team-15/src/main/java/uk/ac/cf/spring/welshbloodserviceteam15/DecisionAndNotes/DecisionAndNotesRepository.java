package uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes;

import java.util.Optional;


public interface DecisionAndNotesRepository {
    Optional<DonorDetails> getDonorRecord(int donorId);
    void updateDonorRecord(int donorId, String decision, String notes, String username);

}
