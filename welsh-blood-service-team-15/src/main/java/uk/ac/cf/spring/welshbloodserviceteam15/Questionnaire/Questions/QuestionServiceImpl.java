package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Questions> getQuestions() {
        return questionRepository.findAll();
    }

    public Questions getQuestion(Integer questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }
}
