package uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Please enter a decision")
    private String donorDecision;
    @NotBlank(message = "Please enter a note")
    @Size(min = 10, max = 500, message = "Please enter a note between 10 - 500 characters")
    private String donorNote;


    public String getNotes() {
        return this.donorNote;
    }

    public String getDecision() {
        return this.donorDecision;
    }
}
