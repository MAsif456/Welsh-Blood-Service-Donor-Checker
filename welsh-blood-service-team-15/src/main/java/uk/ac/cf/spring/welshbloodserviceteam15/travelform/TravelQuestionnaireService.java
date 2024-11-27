package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import java.util.List;

public interface TravelQuestionnaireService {
    List<TravelQuestionnaire> getAllQuestionnaires();
    TravelQuestionnaire saveQuestionnaire(TravelQuestionnaire questionnaire);
}
