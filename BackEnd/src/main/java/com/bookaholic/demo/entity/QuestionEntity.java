package com.bookaholic.demo.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue
    @Column(name="question_id", columnDefinition="uuid")
    private UUID questionId;

    @Column(name = "quiz_id")
    private UUID quizId;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "answer", columnDefinition = "TEXT")
    private String answer;

    public QuestionEntity() {
    }

    public QuestionEntity(UUID questionId, UUID quizId, String description, String answer) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.description = description;
        this.answer = answer;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public UUID getQuizId() {
        return quizId;
    }

    public void setQuizId(UUID quizId) {
        this.quizId = quizId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
