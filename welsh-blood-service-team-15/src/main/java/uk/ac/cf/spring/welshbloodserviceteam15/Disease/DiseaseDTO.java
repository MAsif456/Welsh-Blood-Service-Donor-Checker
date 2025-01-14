package uk.ac.cf.spring.welshbloodserviceteam15.Disease;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class DiseaseDTO {

    @NotNull
    private Integer countryId;

    private Integer regionId;

    private String newRegionName;

    @NotNull
    private String diseaseName;

    private String note;

    @NotNull
    @PastOrPresent
    private LocalDate startDate;

    @NotNull
    @PastOrPresent
    private LocalDate endDate;

    private Integer deferralPeriodDays;

    private String deferralNote;

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

    public Integer getDeferralPeriodDays() {
        return deferralPeriodDays;
    }

    public void setDeferralPeriodDays(Integer deferralPeriodDays) {
        this.deferralPeriodDays = deferralPeriodDays;
    }

    public String getDeferralNote() {
        return deferralNote;
    }

    public void setDeferralNote(String deferralNote) {
        this.deferralNote = deferralNote;
    }
}