package uk.ac.cf.spring.welshbloodserviceteam15.Disease.Deferral;

import jakarta.persistence.*;

@Entity
@Table(name = "deferral_period")
public class DeferralPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deferralPeriodId;

    @Column(name = "period_days", nullable = false)
    private Integer periodDays;

    public Integer getDeferralPeriodId() {
        return deferralPeriodId;
    }

    public void setDeferralPeriodId(Integer deferralPeriodId) {
        this.deferralPeriodId = deferralPeriodId;
    }

    public Integer getPeriodDays() {
        return periodDays;
    }

    public void setPeriodDays(Integer periodDays) {
        this.periodDays = periodDays;
    }
}




