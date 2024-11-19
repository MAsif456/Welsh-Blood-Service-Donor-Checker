package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import jakarta.persistence.*;
import uk.ac.cf.spring.welshbloodserviceteam15.CountryDisease.CountryDisease;

import java.util.List;

@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diseaseId;

    private String diseaseName;

    @OneToMany(mappedBy = "disease", cascade = CascadeType.ALL)
    private List<CountryDisease> affectedCountries;

    private Integer getDiseaseId() {
        return diseaseId;
    }
    private void setDiseaseId(Integer diseaseId) {
        this.diseaseId = diseaseId;
    }
    private String getDiseaseName() {
        return diseaseName;
    }
    private void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
    private List<CountryDisease> getAffectedCountries() {
        return affectedCountries;
    }
    private void setAffectedCountries(List<CountryDisease> affectedCountries) {
        this.affectedCountries = affectedCountries;
    }

}