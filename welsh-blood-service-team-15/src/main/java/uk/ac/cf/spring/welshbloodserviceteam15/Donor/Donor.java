package uk.ac.cf.spring.welshbloodserviceteam15.Donor;
import jakarta.persistence.*;
import java.util.List;
import uk.ac.cf.spring.welshbloodserviceteam15.DonorTravelledToCountry.DonorTravelledToCountry;

@Entity
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donorId;

    private String firstName;
    private String lastName;
    private String donorDob; // You can use LocalDate for date handling
    private String donorRisk;
    private String donorDecision;
    private String donorNote;

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
    private List<DonorTravelledToCountry> travelHistory;

    private Integer getDonorId() {
        return donorId;
    }
    private void setDonorId(Integer donorId) {
        this.donorId = donorId;
    }
    private String getFirstName() {
        return firstName;
    }
    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    private String getLastName() {
        return lastName;
    }
    private void setLastName(String lastName) {
        this.lastName = lastName;
    }
    private String getDonorDob() {
        return donorDob;
    }
    private void setDonorDob(String donorDob) {
        this.donorDob = donorDob;
    }
    private String getDonorRisk() {
        return donorRisk;
    }
    private void setDonorRisk(String donorRisk) {
        this.donorRisk = donorRisk;
    }
    private String getDonorDecision() {
        return donorDecision;
    }
    private void setDonorDecision(String donorDecision) {
        this.donorDecision = donorDecision;
    }
    private String getDonorNote() {
        return donorNote;
    }
    private void setDonorNote(String donorNote) {
        this.donorNote = donorNote;
    }


}
