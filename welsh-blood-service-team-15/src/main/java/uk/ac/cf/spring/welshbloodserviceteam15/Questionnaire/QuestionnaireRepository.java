package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
    List<Questionnaire> findByDonorId(Integer donorId);
}
