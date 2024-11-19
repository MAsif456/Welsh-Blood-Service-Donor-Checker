package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import jakarta.persistence.*;
import uk.ac.cf.spring.welshbloodserviceteam15.CountryDisease.CountryDisease;
import uk.ac.cf.spring.welshbloodserviceteam15.DonorTravelledToCountry.DonorTravelledToCountry;

import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;

    private String countryName;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<CountryDisease> diseases;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<DonorTravelledToCountry> donorVisits;

    // Gteters and settirs below
    private Integer getCountryId() {
        return countryId;
    }
    private void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
    private String getCountryName() {
        return countryName;
    }
    private void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    private List<CountryDisease> getDiseases() {
        return diseases;
    }
    private void setDiseases(List<CountryDisease> diseases) {
        this.diseases = diseases;
    }
    private List<DonorTravelledToCountry> getDonorVisits() {
        return donorVisits;
    }
    private void setDonorVisits(List<DonorTravelledToCountry> donorVisits) {
        this.donorVisits = donorVisits;
    }



}
