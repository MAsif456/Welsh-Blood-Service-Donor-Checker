package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Validation.ValidEndDate;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Validation.ValidStartDate;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.Disease;

import java.time.LocalDate;
import java.util.Date;

@ValidEndDate
@Entity
@Table(name = "country_disease")
//At class level because it validates relationship between two fields, not a single field.
public class CountryDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @NotEmpty(message = "Note must not be empty")
    private String note;

    @NotNull(message = "Start Date must not be null")
    @ValidStartDate
    private LocalDate startDate;

    @NotNull(message = "End Date must not be null")
    private LocalDate endDate;


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
    public Region getRegion() {
        return region;
    }
    public void setRegion(Region region) {
        this.region = region;
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
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
