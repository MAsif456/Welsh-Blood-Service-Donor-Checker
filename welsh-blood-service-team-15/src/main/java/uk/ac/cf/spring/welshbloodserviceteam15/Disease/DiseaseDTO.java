package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import java.time.LocalDate;

public class DiseaseDTO {
    private Integer countryId;
    private Integer regionId;
    private String newRegionName;
    private String diseaseName;
    private String note;
    private LocalDate startDate;
    private LocalDate endDate;

    // Getters and setters
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getNewRegionName() {
        return newRegionName;
    }

    public void setNewRegionName(String newRegionName) {
        this.newRegionName = newRegionName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
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