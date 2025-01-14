package uk.ac.cf.spring.welshbloodserviceteam15.Disease.Deferral;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeferralPeriodRepository extends JpaRepository<DeferralPeriod, Integer> {
    Optional<DeferralPeriod> findByPeriodDays(Integer periodDays);
}