package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelFormQuestionServiceImpl implements TravelFormQuestionService {

    @Autowired
    private TravelFormQuestionRepository repository;

    @Override
    public List<TravelFormQuestion> getAllQuestions() {
        return repository.findAll();
    }

    @Override
    public TravelFormQuestion saveQuestion(TravelFormQuestion question) {
        return repository.save(question);
    }
}
