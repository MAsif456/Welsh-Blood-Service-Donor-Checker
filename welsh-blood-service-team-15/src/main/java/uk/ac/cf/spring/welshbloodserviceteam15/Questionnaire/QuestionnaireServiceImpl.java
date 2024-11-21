package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{

    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    // Questionnaire table has the answers in it

    public List<Questionnaire> getDonorsAnswers(Integer donorId) {
        return questionnaireRepository.findByDonorId(donorId);
    }

}
