package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire;

import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.QuestionRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{

    private final QuestionnaireRepository questionnaireRepository;
    private final DonorRepository donorRepository;
    private final QuestionRepository questionRepository;

    public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository, DonorRepository donorRepository, QuestionRepository questionRepository) {
        this.questionnaireRepository = questionnaireRepository;
        this.donorRepository = donorRepository;
        this.questionRepository = questionRepository;
    }
    // Questionnaire table has the answers in it

    public List<Questionnaire> getDonorsAnswers(Integer donorId) {
        return questionnaireRepository.findByDonor_DonorId(donorId);
    }

    public void saveFromForm(Integer donorId, Integer questionId, String answer) {
        Questionnaire questionnaire = new Questionnaire();
        Donor donor = donorRepository.findByDonorId(donorId);
        questionnaire.setDonor(donor);

        Questions question = questionRepository.findByQuestionId(questionId);
        questionnaire.setQuestion(question);

        questionnaire.setAnswer(answer);
        questionnaireRepository.save(questionnaire);
    }

}
