package com.bookaholic.demo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "quiz")
public class QuizEntity {
    @Id
    @GeneratedValue
    @Column(name="quiz_id", columnDefinition="uuid")
    private UUID quizId;

    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "quiz_name")
    private String quizName;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "disabled")
    private Boolean disabled;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "average_scores")
    private Float averageScores;

    public QuizEntity() {
    }

    public QuizEntity(UUID quizId, UUID bookId, String quizName, Date dueDate, Boolean disabled, Date createdDate, Float averageScores) {
        this.quizId = quizId;
        this.bookId = bookId;
        this.quizName = quizName;
        this.dueDate = dueDate;
        this.disabled = disabled;
        this.createdDate = createdDate;
        this.averageScores = averageScores;
    }

    public UUID getQuizId() {
        return quizId;
    }

    public void setQuizId(UUID quizId) {
        this.quizId = quizId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Float getAverageScores() {
        return averageScores;
    }

    public void setAverageScores(Float averageScores) {
        this.averageScores = averageScores;
    }
}
