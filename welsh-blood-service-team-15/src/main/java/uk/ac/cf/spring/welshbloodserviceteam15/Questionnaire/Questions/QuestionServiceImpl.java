package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Questions> getQuestions() {
        return questionRepository.findAll();
    }
}
