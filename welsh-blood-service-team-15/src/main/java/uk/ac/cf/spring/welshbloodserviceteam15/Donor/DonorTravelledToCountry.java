package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import jakarta.persistence.*;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;

import java.time.LocalDate;

@Entity
@Table(name = "donor_travelled_to_country")

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

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;


    private LocalDate endDate;

    // No-argument constructor
    public DonorTravelledToCountry() {
    }

    public DonorTravelledToCountry(int id, Donor donor, Country country, Region region, LocalDate endDate) {
        this.id = id;
        this.donor = donor;
        this.country = country;
        this.region = region;
        this.endDate = endDate;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Donor getDonor() {
        return donor;
    }
    public void setDonor(Donor donor) {
        this.donor = donor;
    }
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


}