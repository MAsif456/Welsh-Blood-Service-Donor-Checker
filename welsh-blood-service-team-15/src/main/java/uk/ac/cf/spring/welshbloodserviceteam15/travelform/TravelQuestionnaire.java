package uk.ac.cf.spring.welshbloodserviceteam15.travelform;


import jakarta.persistence.*;

@Entity
@Table(name = "travel_questionnaire")
public class TravelQuestionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionnaire_id")
    private Long questionnaireId;

    @Column(name = "donor_id")
    private Long donorId;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "answer")
    private String answer;

    // Getters and Setters
    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}