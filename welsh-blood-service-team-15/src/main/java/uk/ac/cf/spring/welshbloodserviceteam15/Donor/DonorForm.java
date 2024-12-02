package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
public class DonorForm {
    @NotEmpty(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only alphabetic characters")
    private String firstName;
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @NotEmpty(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only alphabetic characters")
    private String lastName;
    @NotNull(message = "Date of birth is required")
    //Custom validation for age as the built-in @Min annotation does not work with LocalDate
    @ValidDonorAge
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
