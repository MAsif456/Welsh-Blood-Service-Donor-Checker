package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire;


import java.util.List;

public interface QuestionnaireService {

    List<Questionnaire> getDonorsAnswers(Integer donorId);

}
