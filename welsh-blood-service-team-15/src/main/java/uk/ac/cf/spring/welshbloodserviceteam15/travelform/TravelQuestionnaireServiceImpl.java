package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelQuestionnaireServiceImpl implements TravelQuestionnaireService {

    @Autowired
    private TravelQuestionnaireRepository repository;

    @Override
    public List<TravelQuestionnaire> getAllQuestionnaires() {
        return repository.findAll();
    }

    @Override
    public TravelQuestionnaire saveQuestionnaire(TravelQuestionnaire questionnaire) {
        return repository.save(questionnaire);
    }
}
