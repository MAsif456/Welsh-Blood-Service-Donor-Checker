package uk.ac.cf.spring.welshbloodserviceteam15.Donor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "donor")
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donorId;

    private String firstName;
    private String lastName;
    private LocalDate donorDob;
    private String donorDecision;
    private String donorNote;
    @OneToMany(mappedBy = "donor", fetch = FetchType.LAZY)
    private List<DonorTravelledToCountry> travelHistory;

    public Integer getDonorId() {
        return donorId;
    }
    public void setDonorId(Integer donorId) {
        this.donorId = donorId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDate getDonorDob() {
        return donorDob;
    }
    public void setDonorDob(LocalDate donorDob) {
        this.donorDob = donorDob;
    }
    public String getDonorDecision() {
        return donorDecision;
    }
    public void setDonorDecision(String donorDecision) {
        this.donorDecision = donorDecision;
    }
    public String getDonorNote() {
        return donorNote;
    }
    public void setDonorNote(String donorNote) {
        this.donorNote = donorNote;
    }

    public List<DonorTravelledToCountry> getTravelHistory() {
        return travelHistory;
    }
    public void setTravelHistory(List<DonorTravelledToCountry> travelHistory) {
        this.travelHistory = travelHistory;
    }


}
