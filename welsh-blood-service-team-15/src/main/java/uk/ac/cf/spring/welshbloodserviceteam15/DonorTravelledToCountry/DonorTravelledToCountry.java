package uk.ac.cf.spring.welshbloodserviceteam15.DonorTravelledToCountry;

import jakarta.persistence.*;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;

import java.time.LocalDate;

@Entity
public class DonorTravelledToCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private LocalDate startDate;
    private LocalDate endDate;

    private Integer getId() {
        return id;
    }
    private void setId(Integer id) {
        this.id = id;
    }
    private Donor getDonor() {
        return donor;
    }
    private void setDonor(Donor donor) {
        this.donor = donor;
    }
    private Country getCountry() {
        return country;
    }
    private void setCountry(Country country) {
        this.country = country;
    }
    private LocalDate getStartDate() {
        return startDate;
    }
    private void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    private LocalDate getEndDate() {
        return endDate;
    }
    private void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


}