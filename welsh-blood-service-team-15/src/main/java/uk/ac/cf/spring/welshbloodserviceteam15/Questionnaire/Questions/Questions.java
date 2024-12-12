package uk.ac.cf.spring.welshbloodserviceteam15.Questionnaire.Questions;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="questions")
public class Questions {

    @Id
    @GeneratedValue
    private Integer question_id;

    private String question;

}

