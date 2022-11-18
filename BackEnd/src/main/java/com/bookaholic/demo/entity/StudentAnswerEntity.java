package com.bookaholic.demo.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "student_answer")
public class StudentAnswerEntity {
    @Id
    @GeneratedValue
    @Column(name="student_answer_id", columnDefinition="uuid")
    private UUID studentAnswerId;

    @Column(name = "student_id")
    private UUID studentId;

    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "answer")
    private String answer;

    public StudentAnswerEntity() {
    }

    public StudentAnswerEntity(UUID studentAnswerId, UUID studentId, UUID questionId, String answer) {
        this.studentAnswerId = studentAnswerId;
        this.studentId = studentId;
        this.questionId = questionId;
        this.answer = answer;
    }

    public UUID getStudentAnswerId() {
        return studentAnswerId;
    }

    public void setStudentAnswerId(UUID studentAnswerId) {
        this.studentAnswerId = studentAnswerId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
