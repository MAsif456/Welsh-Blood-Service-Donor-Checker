package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import java.util.List;

public interface TravelFormQuestionService {
    List<TravelFormQuestion> getAllQuestions();
    TravelFormQuestion saveQuestion(TravelFormQuestion question);
}
