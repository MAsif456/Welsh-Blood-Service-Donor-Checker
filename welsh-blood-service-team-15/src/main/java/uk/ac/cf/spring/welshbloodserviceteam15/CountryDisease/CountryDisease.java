package uk.ac.cf.spring.welshbloodserviceteam15.CountryDisease;

import jakarta.persistence.*;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Disease;

@Entity
public class CountryDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    private String note;


    private Integer getId() {
        return id;
    }
    private void setId(Integer id) {
        this.id = id;
    }

    private Country getCountry() {
        return country;
    }
    private void setCountry(Country country) {
        this.country = country;
    }

    private Disease getDisease() {
        return disease;
    }
    private void setDisease(Disease disease) {
        this.disease = disease;
    }
    private String getNote() {
        return note;
    }
    private void setNote(String note) {
        this.note = note;
    }



}
