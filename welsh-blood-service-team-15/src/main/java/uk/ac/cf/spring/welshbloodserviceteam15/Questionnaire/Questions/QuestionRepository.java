package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Questions, Integer> {

    // Custom query using @Query because its so broke
    @Query("SELECT q FROM Questions q WHERE q.question_id = :questionId")
    Questions findByQuestionId(Integer questionId);

}
