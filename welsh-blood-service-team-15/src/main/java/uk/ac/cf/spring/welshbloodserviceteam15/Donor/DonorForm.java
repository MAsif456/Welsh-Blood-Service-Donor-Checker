package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import java.time.LocalDate;

public class DonorForm {
    private String firstName;
    private String lastName;

    private LocalDate donorDob;

    private int donorId;

    // Getters and Setters
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
    public int getDonorId() {return donorId;}
    public void setDonorId(int donorId) {this.donorId = donorId;}
}
