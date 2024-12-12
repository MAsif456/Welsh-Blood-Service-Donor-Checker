package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import jakarta.persistence.*;


@Entity
@Table(name = "diseases")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diseaseId;

    private String diseaseName;


    public Integer getDiseaseId() {
        return diseaseId;
    }
    public void setDiseaseId(Integer diseaseId) {
        this.diseaseId = diseaseId;
    }
    public String getDiseaseName() {
        return diseaseName;
    }
    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    }

