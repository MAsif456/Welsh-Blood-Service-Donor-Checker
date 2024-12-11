package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{

    private final QuestionnaireRepository questionnaireRepository;

    public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }
    // Questionnaire table has the answers in it

    public List<Questionnaire> getDonorsAnswers(Integer donorId) {
        return questionnaireRepository.findByDonor_DonorId(donorId);
    }

}
