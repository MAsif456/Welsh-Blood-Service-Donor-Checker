package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelQuestionnaireRepository extends JpaRepository<TravelQuestionnaire, Long> {
    Optional<TravelQuestionnaire> findByDonorIdAndQuestionId(Long donorId, Long questionId);
}
