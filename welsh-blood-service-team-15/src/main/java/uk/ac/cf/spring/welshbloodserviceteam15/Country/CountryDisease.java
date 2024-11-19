package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import jakarta.persistence.*;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Disease;

@Entity
@Table(name = "country_disease")

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


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    public Disease getDisease() {
        return disease;
    }
    public void setDisease(Disease disease) {
        this.disease = disease;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }



}
