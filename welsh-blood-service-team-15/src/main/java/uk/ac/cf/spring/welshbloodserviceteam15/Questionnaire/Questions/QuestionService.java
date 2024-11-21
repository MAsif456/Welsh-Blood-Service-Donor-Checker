package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions;

import java.util.List;

public interface QuestionService {

    List<Questions> getQuestions();

    Questions getQuestion(Integer questionId);
}
