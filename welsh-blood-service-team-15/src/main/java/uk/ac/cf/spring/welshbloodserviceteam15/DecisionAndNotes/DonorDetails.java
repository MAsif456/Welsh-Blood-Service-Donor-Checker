package uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor  // Add this annotation for the default constructor
public class DonorDetails {
    private int donorId;
    private String firstName;
    private String lastName;
    private LocalDate donorDob;
    private String donorRisk;
    private String donorDecision;
    private String donorNote;



}
