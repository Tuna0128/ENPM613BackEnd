package com.bookaholic.demo.entity;

import com.bookaholic.demo.model.EnrollmentPayload;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "enroll")
public class EnrollmentEntity {
    @Id
    @GeneratedValue
    @Column(name="enroll_id",columnDefinition="uuid")
    private UUID enrollId;

    @Column(name="student_id")
    private UUID studentId;

    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "enroll_date")
    private Date enrollDate;
    
    public EnrollmentEntity() {
    }

    public EnrollmentEntity(EnrollmentPayload enrollment) {
        this.studentId = enrollment.getStudentId();
        this.bookId = enrollment.getBookId();
        this.enrollDate = new Date();
    }

    public EnrollmentEntity(UUID enrollId, UUID studentId, UUID bookId, Date enrollDate) {
        this.enrollId = enrollId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.enrollDate = enrollDate;
    }

    public UUID getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(UUID enrollId) {
        this.enrollId = enrollId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }
}
