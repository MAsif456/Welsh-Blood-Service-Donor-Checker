package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import jakarta.persistence.*;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountry;

import java.util.List;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;

    private String countryName;

    public Integer getCountryId() {
        return countryId;
    }
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }



}
