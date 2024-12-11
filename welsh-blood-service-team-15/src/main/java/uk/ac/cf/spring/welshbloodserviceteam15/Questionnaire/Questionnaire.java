package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions.Questions;

@Setter
@Getter
@Entity
@Table(name = "travel_questionnaire")
public class Questionnaire {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionnaire_id")
    private Integer questionnaireId;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions question;

    @Column(name = "answer")
    private String answer;
}

